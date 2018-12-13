package org.mac.etl;

import java.util.Iterator;

public interface ObjectProvider<S, O> extends Iterator<S> {

	O nextOutput();

}
