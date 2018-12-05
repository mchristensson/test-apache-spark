package org.mac.spark;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.StreamSupport;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mac.etl.demo.model.Account;
import org.mac.etl.demo.model.LegacyAccount;
import org.mac.etl.demo.service.AccountService;
import org.mac.etl.demo.service.LegacyAccountService;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.internal.MockitoCore;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.OngoingStubbing;

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
	public void testETLMapping() {
		
		LegacyAccount legacyAccount = new LegacyAccount();
		Account account = new Account();
		ETLTransform accountNumberTransform = new ETLTransform() {};
		
		ETLContext ctx = new ETLContext();
		try {

			ctx.addMapping(legacyAccount.getAccountNumber(), Long.class).ttt(v -> account.setAccountNumber(v));
			//.trx(v -> account.setAccountNumber(v));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
}

class ETLTransform {
	
}

class ETLCore {

	public <T,A> ETLMapping<T, A> addMapping(T inputValue, A outputType) {
		ETLMapping<T,A> mapping = new ETLMapping<>();
		mapping.setInputValue(inputValue);
		mapping.setOutputType(outputType);
		return mapping;
	}

}

// TODO: Make this class abstract
class ETLMapping<T,A> {

	private T inputValue;
	private A outputType;

	public void setInputValue(T inputValue) {
		this.inputValue = inputValue;
	}

	public void setOutputType(A outputType) {
		this.outputType = outputType;
	}

	public <X> ETLMapping<T, A> trns(Function<X, T> f) {
		return this;
	}

	public <A> A ttt(Consumer<A> f) {
		f.andThen();
	}

	
	
}

class ETLContext {
	
 
	static final ETLCore CORE = new ETLCore();


	public <T,A> ETLMapping<T,A> addMapping(T methodCall, A outputType) {
		return CORE.addMapping(methodCall, outputType);
	}
}