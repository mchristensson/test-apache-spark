package org.mac.etl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.mac.etl.demo.model.Account;
import org.mac.etl.demo.model.LegacyAccount;

//TODO: Testklass
public class ObjectProviderImpl implements ObjectProvider<LegacyAccount, Account> {

	private List<LegacyAccount> legacyObjects = new ArrayList<>();
	private List<Account> objects = new ArrayList<>();
	private Iterator<LegacyAccount> sourceIter;
	private Iterator<Account> targetIter;
	
	public ObjectProviderImpl(){
		LegacyAccount l0 = new LegacyAccount();
		l0.setAccountNumber("123");
		LegacyAccount l1 = new LegacyAccount();
		l1.setAccountNumber("456");
		LegacyAccount l2 = new LegacyAccount();
		l2.setAccountNumber("789");
		legacyObjects.add(l0);
		legacyObjects.add(l1);
		legacyObjects.add(l2);
		
		objects.add(new Account());
		objects.add(new Account());
		objects.add(new Account());
		
		sourceIter = legacyObjects.iterator();
		targetIter = objects.iterator();
	}
	
	@Override
	public boolean hasNext() {
		return this.sourceIter.hasNext();
	}

	@Override
	public LegacyAccount next() {
		return this.sourceIter.next();
	}

	@Override
	public Account nextOutput() {
		return this.targetIter.next();
	}

}
