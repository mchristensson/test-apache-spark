package org.mac.etl;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class ETLCore {

	public <S, O> ETLMapping<S, O> addMap(Segment segment, Supplier<S> supplier, Function<S, O> transformer, Consumer<O> consumer) {
		ETLMapping<S, O> map = new ETLMapping<S, O>();
		map.setSupplier(supplier);
		map.setTransformer(transformer);
		map.setConsumer(consumer);
		return map;
	}

	public Segment addSegment() {
		Segment segment = new Segment();
		return segment;
	}

}