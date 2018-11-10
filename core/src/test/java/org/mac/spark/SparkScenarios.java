package org.mac.spark;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.SparkSession;
import org.junit.Assert;
import org.junit.Test;
import org.mac.spark.test.model.LegacyAccount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SparkScenarios {
	private static final Logger logger = LoggerFactory.getLogger(SparkScenarios.class);
	
	private static final String PERSISTENCE_UNIT_NAME = "spark-legacy";
	private static final String PERSISTENCE_UNIT_NAME_B = "spark-target";
	
	@Test
	public void test() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factory.createEntityManager();

		logger.info("Creating a new entity...");
		em.getTransaction().begin();
		LegacyAccount account = new LegacyAccount();
		account.setAccountNumber("ABC-123");
		em.persist(account);
		em.getTransaction().commit();
		logger.info("Entity created.");
		
		
		
		SparkConf config = new SparkConf().setAppName("Simple Application").set("spark.master", "local");
		
	    SparkContext ctx = new SparkContext(config);
		//JavaSparkContext ctx = new JavaSparkContext(conf);
	    SparkSession session = new SparkSession(ctx );
	    SQLContext sqlCtx = new SQLContext(session);
		
		Dataset<Row> rows = sqlCtx.sql("select * from LegacyAccount");
		
		getDataSetResult(rows);
	    // TODO: Continue from here
		//Assert.assertEquals(2, dataSetResult.getColumnNames().size());
	    //Assert.assertEquals(2, dataSetResult.getRows().size());
		
		em.close();
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
