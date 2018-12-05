package org.mac.spark.test.db;


import java.io.File;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mac.etl.demo.model.Account;
import org.mac.etl.demo.model.LegacyAccount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DerbyConnectionTest {

	private static final Logger logger = LoggerFactory.getLogger(DerbyConnectionTest.class);
	
	private static final String PERSISTENCE_UNIT_NAME = "spark-legacy";
	private static final String PERSISTENCE_UNIT_NAME_B = "spark-target";
	
	@Test
	public void testPU_A() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factory.createEntityManager();

		logger.info("Creating a new entity...");
		em.getTransaction().begin();
		LegacyAccount account = new LegacyAccount();
		account.setAccountNumber("ABC-123");
		em.persist(account);
		em.getTransaction().commit();
		logger.info("Entity created.");

		List<LegacyAccount> entities = em.createQuery("select r from LegacyAccount r", LegacyAccount.class).getResultList();
		for (LegacyAccount entity : entities) {
			logger.info(entity.toString());
		}
		Assert.assertEquals("Unexpected number of entries", 1, entities.size());
		
		em.close();
		
	}
	
	@Test
	public void testPU_B() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME_B);
		EntityManager em = factory.createEntityManager();

		logger.info("Creating a new entity");
		em.getTransaction().begin();
		Account account = new Account();
		account.setAccountNumber(1L);
		em.persist(account);
		em.getTransaction().commit();
		logger.info("Entity created.");
		
		List<Account> entities = em.createQuery("select r from Account r", Account.class).getResultList();
		for (Account entity : entities) {
			logger.info(entity.toString());
		}
		Assert.assertEquals("Unexpected number of entries", 1, entities.size());
		
		em.close();
		
	}
}
