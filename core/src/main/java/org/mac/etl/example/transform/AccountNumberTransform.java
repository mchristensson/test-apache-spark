package org.mac.etl.example.transform;

import org.mac.etl.demo.model.Account;
import org.mac.etl.demo.model.LegacyAccount;
import org.mac.etl.function.ParseLong;
import org.mac.etl.transform.Transformer;

public class AccountNumberTransform extends ParseLong implements Transformer<LegacyAccount, Account> {

	@Override
	public void transform(LegacyAccount source, Account target) {
		target.setAccountNumber(this.apply(source.getAccountNumber()));
	}

}