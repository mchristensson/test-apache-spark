package org.mac.etl.provider;

import java.util.Iterator;

public interface TargetObjectProvider<O> extends Iterator<O> {

	void resetIter();

	
}