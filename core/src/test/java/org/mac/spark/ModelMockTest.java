package org.mac.spark;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.function.Function;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mac.etl.ETLContext;
import org.mac.etl.ETLMapping;
import org.mac.etl.Segment;
import org.mac.etl.demo.model.Account;
import org.mac.etl.demo.model.LegacyAccount;
import org.mac.etl.demo.service.AccountService;
import org.mac.etl.demo.service.LegacyAccountService;
import org.mac.etl.function.ParseLong;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ModelMockTest {

	@Mock
	private LegacyAccountService legacyAccountService;

	@Mock
	private AccountService accountService;

	@Test
	public void testLegacyAccount_simpleReturn() {
		LegacyAccount person = new LegacyAccount();
		person.setAccountNumber("A-123");

		when(legacyAccountService.findById(any(Long.class))).thenReturn(person);

		LegacyAccount result = legacyAccountService.findById(1L);
		assertEquals(result.getAccountNumber(), person.getAccountNumber());
		assertEquals(result.getAccountNumber(), "A-123");

		verify(legacyAccountService, times(1)).findById(any(Long.class));
		verifyNoMoreInteractions(legacyAccountService);
	}

	@Test
	public void testAccount_simpleReturn() {
		Account person = new Account();
		person.setAccountNumber(123L);

		when(accountService.findById(any(Long.class))).thenReturn(person);

		Account result = accountService.findById(1L);
		assertEquals(result.getAccountNumber(), person.getAccountNumber());
		assertTrue(result.getAccountNumber() == 123L);

		verify(accountService, times(1)).findById(any(Long.class));
		verifyNoMoreInteractions(accountService);
	}

	@Test
	public void testETL_createContext() {
		ETLContext ctx = new ETLContext();
	}
	
	@Test
	public void testETL_addSegment() {
		ETLContext ctx = new ETLContext();
		Segment segment = ctx.addSegment();
		Assert.assertNotNull(segment);
	}
	
	@Test
	public void testETLMapping() {

		LegacyAccount legacyAccount = new LegacyAccount();
		legacyAccount.setAccountNumber("123");
		Account account = new Account();

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
