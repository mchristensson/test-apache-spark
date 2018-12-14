package org.mac.etl.example.provider;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.mac.etl.demo.model.Account;
import org.mac.etl.provider.TargetObjectProvider;

public class TargetObjectProviderImpl implements TargetObjectProvider<Account> {
	
	private List<Account> objects = new ArrayList<>();
	private Iterator<Account> iterator;
	boolean iteratorMode = false;

	@Override
	public boolean hasNext() {
		if (!iteratorMode) {
			return true;
		} else {
			return iterator.hasNext();
		}
	}

	@Override
	public Account next() {
		Account account;
		if (!iteratorMode) {
			account = new Account();
			objects.add(account);
		} else {
			account = iterator.next();
		}
		return account;
	}

	@Override
	public void resetIter() {
		this.iteratorMode = !objects.isEmpty();
		if (iteratorMode) {
			iterator = objects.iterator();
		}

	}

}
