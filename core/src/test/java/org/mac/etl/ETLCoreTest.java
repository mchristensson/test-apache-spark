package org.mac.etl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.mac.etl.ETLContext;
import org.mac.etl.ETLCore;
import org.mac.etl.ETLMapping;
import org.mac.etl.Segment;
import org.mac.etl.demo.model.Account;
import org.mac.etl.demo.model.LegacyAccount;
import org.mac.etl.function.ParseLong;

public class ETLCoreTest {
	
	@Test
	public void testCreate() {
		@SuppressWarnings("unused")
		ETLCore core = new ETLCore();
	}
	
	@Test
	public void testAddMap() {
		ETLCore core = new ETLCore();
		ETLMapping<?, ?> mapping = core.addMap(null, null, null, null);
		assertNotNull(mapping);
	}

	@Test
	public void testAddSegment() {
		ETLCore core = new ETLCore();
		Segment segment = core.addSegment();
		assertNotNull(segment);
	}
	
	@Test
	public void testETLMapping() {

		//Prepare mocked data
		LegacyAccount legacyAccount = new LegacyAccount();
		legacyAccount.setAccountNumber("123");
		Account account = new Account();

		//Setup ctx
		ETLContext ctx = new ETLContext();
		Segment segment = ctx.addSegment();

		// Define recipe
		ETLMapping<String, Long> map = ctx.addMap(segment, legacyAccount::getAccountNumber, ParseLong.getInstance(),
				account::setAccountNumber);
		
		// Run playbook
		map.applyTransform(legacyAccount, account);

		assertEquals((long) account.getAccountNumber(), 123L);
	}

}
