package org.mac.etl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mac.etl.demo.model.Account;
import org.mac.etl.demo.model.LegacyAccount;
import org.mac.etl.demo.service.AccountService;
import org.mac.etl.demo.service.LegacyAccountService;
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

}
