package org.mac.etl.function;

import java.util.function.Function;

public class ParseLong implements Function<String, Long> {

	private static ParseLong instance;
	
	public static ParseLong getInstance() {
		if (null == instance) {
			instance = new ParseLong();
		}
		return instance;
	}
	
	@Override
	public Long apply(String s) {
		return Long.parseLong(s);
	}
	
}