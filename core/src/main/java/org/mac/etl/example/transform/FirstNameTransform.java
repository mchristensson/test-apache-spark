package org.mac.etl.example.transform;

import org.mac.etl.demo.model.Account;
import org.mac.etl.demo.model.LegacyAccount;
import org.mac.etl.transform.Transformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FirstNameTransform implements Transformer<LegacyAccount, Account> {
	
	private static final Logger logger = LoggerFactory.getLogger(FirstNameTransform.class);
	
	@Override
	public void transform(LegacyAccount source, Account target) {
		String lastName = source.getName().substring(0, source.getName().indexOf(' ')).trim();
		target.setLastName(lastName);
		logger.info("'{}' -> '{}'", source.getName(), target.getLastName());
	}

}
