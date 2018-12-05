package org.mac.spark;

import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.HibernateException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mac.etl.demo.model.LegacyAccount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EntityManagerTest {
	private static final Logger logger = LoggerFactory.getLogger(EntityManagerTest.class);

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

	

	@Test
	public void test() throws HibernateException, SQLException {

		

		// getDataSetResult(rows);
		// TODO: Continue from here
		// Assert.assertEquals(2, dataSetResult.getColumnNames().size());
		// Assert.assertEquals(2, dataSetResult.getRows().size());

	}

	
}
