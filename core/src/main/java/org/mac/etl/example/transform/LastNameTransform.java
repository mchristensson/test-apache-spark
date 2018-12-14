package org.mac.etl.example.transform;

import org.mac.etl.demo.model.Account;
import org.mac.etl.demo.model.LegacyAccount;
import org.mac.etl.transform.Transformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LastNameTransform implements Transformer<LegacyAccount, Account> {
	
	private static final Logger logger = LoggerFactory.getLogger(LastNameTransform.class);
	
	@Override
	public void transform(LegacyAccount source, Account target) {
		String lastName = source.getName().substring(source.getName().indexOf(' ')).trim();
		target.setLastName(lastName);
		logger.info("'{}' -> '{}'", source.getName(), target.getLastName());
	}

	

}
