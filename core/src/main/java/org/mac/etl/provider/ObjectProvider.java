package org.mac.etl.provider;

import java.util.Iterator;
import java.util.List;

public interface ObjectProvider<S, O> extends Iterator<S> {

	O nextOutput();

	void setTargetObjectProvider(TargetObjectProvider<O> targetObjectProvider);

	void setSourceData(List<S> source);

}
