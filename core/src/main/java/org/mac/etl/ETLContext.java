package org.mac.etl;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class ETLContext {

	static final ETLCore CORE = new ETLCore();

	
	public Segment addSegment() {
		return CORE.addSegment();
	}
	
	public <S, O> ETLMapping<S, O> addMap(Segment segment, Supplier<S> supplier, Function<S, O> transformer, Consumer<O> consumer) {
		return CORE.addMap(segment, supplier, transformer, consumer);

	}

	

}