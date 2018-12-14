package org.mac.etl;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.mac.etl.demo.model.Account;
import org.mac.etl.demo.model.LegacyAccount;
import org.mac.etl.example.provider.SourceObjectProviderImpl;
import org.mac.etl.example.provider.TargetObjectProviderImpl;
import org.mac.etl.example.transform.AccountNumberTransform;
import org.mac.etl.example.transform.FirstNameTransform;
import org.mac.etl.example.transform.LastNameTransform;
import org.mac.etl.provider.ObjectProvider;
import org.mac.etl.provider.TargetObjectProvider;

public class ETLCoreTest {

	@Test
	public void testCreate() {
		@SuppressWarnings("unused")
		ETLCore core = new ETLCore();
	}

	@Test
	public void testAddMap() {
		ETLCore core = new ETLCore();
		Segment<?, ?> segment = core.appendSegment("foo");
		core.addTransform(segment, null);
	}

	@Test
	public void testAddSegment() {
		ETLCore core = new ETLCore();
		Segment<?, ?> segment = core.appendSegment("foo");
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

		Segment<?, ?> segment = core.getSegment("testsegmentB");
		assertNotNull(segment);
	}

	@Test
	public void testETLMapping() {

		// Mock some data
		ObjectProvider<LegacyAccount, Account> objectProvider = new SourceObjectProviderImpl();
		TargetObjectProvider<Account> targetObjectProvider = new TargetObjectProviderImpl();
		objectProvider.setTargetObjectProvider(targetObjectProvider);
		List<LegacyAccount> source = new ArrayList<>();
		LegacyAccount l0 = new LegacyAccount();
		l0.setAccountNumber("123");
		l0.setName("William O'Malley");
		LegacyAccount l1 = new LegacyAccount();
		l1.setAccountNumber("456");
		l1.setName("Ingrid Harding");
		LegacyAccount l2 = new LegacyAccount();
		l2.setAccountNumber("789");
		l2.setName("Sylvia Goldman");
		source.add(l0);
		source.add(l1);
		source.add(l2);
		objectProvider.setSourceData(source);

		// Setup ctx
		ETLContext ctx = new ETLContext();
		Segment<LegacyAccount, Account> segment = ctx.addSegment("foo");

		segment.setObjectProvider(objectProvider);

		// Define recipe
		ctx.addMap(segment, new AccountNumberTransform());
		ctx.addMap(segment, new LastNameTransform());
		ctx.addMap(segment, new FirstNameTransform());

		// Run playbook
		ctx.run();
		// map.applyTransform(legacyAccount, account);

	}

}