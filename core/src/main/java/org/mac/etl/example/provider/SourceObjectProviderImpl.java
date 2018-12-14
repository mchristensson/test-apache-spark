package org.mac.etl.example.provider;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.mac.etl.demo.model.Account;
import org.mac.etl.demo.model.LegacyAccount;
import org.mac.etl.provider.ObjectProvider;
import org.mac.etl.provider.TargetObjectProvider;

//TODO: Testklass
public class SourceObjectProviderImpl implements ObjectProvider<LegacyAccount, Account> {

	private List<LegacyAccount> legacyObjects = new ArrayList<>();
	
	private Iterator<LegacyAccount> sourceIter;
	private TargetObjectProvider<Account> targetDataProvider;
	
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
		return this.targetDataProvider.next();
	}
	
	@Override
	public void setSourceData(List<LegacyAccount> legacyObjects) {
		if (this.legacyObjects.isEmpty()) {
			this.legacyObjects.addAll(legacyObjects);			
		} else {
			throw new RuntimeException("Objects already added");
		}
		sourceIter = legacyObjects.iterator();
	}
	
	@Override
	public void setTargetObjectProvider(TargetObjectProvider<Account> targetDataProvider) {
		this.targetDataProvider = targetDataProvider;
		this.targetDataProvider.resetIter();
	}

}
