package org.mac.etl;

import org.mac.etl.transform.Transformer;

public class ETLContext {

	private static final ETLCore CORE = new ETLCore();
	
	
	public <S, T> Segment<S, T> addSegment(String label) {
		return CORE.appendSegment(label);
	}

	public <S,T> void addMap(Segment<S, T> segment, Transformer<S,T> accountNumberTransform) {
		CORE.addTransform(segment, accountNumberTransform);
	}

	public void run() {
		CORE.run();
	}

}