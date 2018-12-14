package org.mac.etl.transform;

public interface Transformer<S,T> {
	void transform(S source, T target);
}
