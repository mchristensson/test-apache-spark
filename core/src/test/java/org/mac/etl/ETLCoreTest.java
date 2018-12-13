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
		Segment segment = core.appendSegment("foo");
		core.addMap(segment, null, null, null);
	}

	@Test
	public void testAddSegment() {
		ETLCore core = new ETLCore();
		Segment segment = core.appendSegment("foo");
		assertNotNull(segment);
	}
	
	@Test
	public void testAddSegments() {
		ETLCore core = new ETLCore();
		core.appendSegment("testsegmentA");
		core.appendSegment("testsegmentB");
		core.appendSegment("testsegmentC");
	}
	
	@Test
	public void testGetSegment() {
		ETLCore core = new ETLCore();
		core.appendSegment("testsegmentA");
		core.appendSegment("testsegmentB");
		core.appendSegment("testsegmentC");
		
		Segment segment = core.getSegment("testsegmentB");
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
		Segment<LegacyAccount, Account> segment = ctx.addSegment("foo");

		ObjectProvider<LegacyAccount, Account> objectProvider = new ObjectProviderImpl();
		segment.setObjectProvider(objectProvider);

		// Define recipe
		ctx.addMap(segment, legacyAccount::getAccountNumber, ParseLong.getInstance(),
				account::setAccountNumber);
		
		// Run playbook
		ctx.run();
		//map.applyTransform(legacyAccount, account);

		
	}

}
