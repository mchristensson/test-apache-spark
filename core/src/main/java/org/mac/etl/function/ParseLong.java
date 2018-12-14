package org.mac.etl.function;

import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ParseLong implements Function<String, Long> {

	private static final Logger logger = LoggerFactory.getLogger(ParseLong.class);

	private static ParseLong instance;

	public static ParseLong getInstance() {
		if (null == instance) {
			instance = new ParseLong();
		}
		return instance;
	}

	@Override
	public Long apply(String s) {
		long l = Long.parseLong(s);
		logger.info("'{}' -> '{}'", s, l);
		return l;
	}

}
