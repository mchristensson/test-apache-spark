package org.mac.etl;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class ETLContext {

	private static final ETLCore CORE = new ETLCore();
	
	
	public Segment addSegment(String label) {
		return CORE.appendSegment(label);
	}
	
	public <S, O> void addMap(Segment segment, Supplier<S> supplier, Function<S, O> transformer, Consumer<O> consumer) {
		CORE.addMap(segment, supplier, transformer, consumer);

	}

	public void run() {
		CORE.run();
	}

	

}