package org.mac.spark;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.SparkSession;
import org.h2.jdbc.JdbcConnection;
import org.h2.jdbc.JdbcDatabaseMetaData;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.internal.SessionImpl;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mac.spark.test.model.LegacyAccount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SparkScenarios {
	private static final Logger logger = LoggerFactory.getLogger(SparkScenarios.class);

	private static final String PERSISTENCE_UNIT_NAME = "spark-legacy";
	private static final String PERSISTENCE_UNIT_NAME_B = "spark-target";

	private EntityManager em;

	@Before
	public void initLegacyData() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		em = factory.createEntityManager();

		logger.info("Creating a new entity...");
		em.getTransaction().begin();
		LegacyAccount account = new LegacyAccount();
		account.setAccountNumber("ABC-123");
		em.persist(account);
		em.getTransaction().commit();
		logger.info("Entity created.");

	}

	@After
	public void closeEntityManager() {
		em.close();
	}

	private Dataset<Row> readFromJdbc(SQLContext sqlContext, String schema, String tableName)
			throws HibernateException, SQLException {
		SessionImpl delgate = (SessionImpl) em.getDelegate();
		Connection conn = delgate.connection();
		DatabaseMetaData md = conn.getMetaData();
		Map<String, Object> emfProperties = em.getEntityManagerFactory().getProperties();
		String emfDriver = (String) emfProperties.get("javax.persistence.jdbc.driver");
		Map<String, String> options = new HashMap<>();
		options.put("url", md.getURL());
		options.put("user", "sa");
		options.put("password", "");
		options.put("driver", emfDriver);
		options.put("dbtable", schema + "." + tableName);
		
		options.put("spark.sql.inMemoryColumnarStorage.batchSize", "20");
		
		return sqlContext.read().format("jdbc").options(options).load();
	}

	@Test
	public void test() throws HibernateException, SQLException {

		SessionImplementor sess = (SessionImplementor) em.getDelegate();
		Object meta = sess.getMetamodel().getEntities();

		SparkConf config = new SparkConf().setAppName("My legacy loader app").set("spark.master", "local");
		SparkContext ctx = new SparkContext(config);
		SparkSession session = new SparkSession(ctx);
		SQLContext sqlCtx = new SQLContext(session);

		Dataset<Row> rows = readFromJdbc(sqlCtx, "PUBLIC", "LEGACYACCOUNT");

		

		// getDataSetResult(rows);
		// TODO: Continue from here
		// Assert.assertEquals(2, dataSetResult.getColumnNames().size());
		// Assert.assertEquals(2, dataSetResult.getRows().size());

	}

	public static void getDataSetResult(Dataset<Row> df) {
		String[] fieldNames = df.schema().fieldNames();

		Row[] rows = (Row[]) df.collect();
		for (Row row : rows) {
			List<Object> values = new ArrayList<>();
			for (int i = 0; i < fieldNames.length; i++) {
				Object obj = row.get(i);
				values.add(obj);
			}

		}

	}
}
