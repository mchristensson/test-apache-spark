package org.mac.etl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class ETLCore {

	private final Map<String, Segment<?,?>> segmentIndex = new HashMap<>();
	private final List<Segment<?,?>> segmentList = new LinkedList<>();
	
	public <S, O> void addMap(Segment<S, O> segment, Supplier<S> supplier, Function<S, O> transformer, Consumer<O> consumer) {
		ETLMapping<S, O> map = new ETLMapping<S, O>();
		map.setSupplier(supplier);
		map.setTransformer(transformer);
		map.setConsumer(consumer);
		segment.addMap(map);
	}

	public <S, O> Segment<S, O> appendSegment(String label) {
		Segment<S, O> segment = new Segment<>();
		this.segmentIndex.put(label, segment);
		this.segmentList.add(segment);
		return segment;
	}
	
	public Segment<?,?> getSegment(String label) {
		return segmentIndex.get(label);
	}

	public void run() {
		segmentList.stream().forEachOrdered(s -> processMaps(s));
	}

	private <S, O> void processMaps(Segment<S, O> s) {
		ObjectProvider<S, O> objectProvider = s.getObjectProvider();
		while (objectProvider.hasNext()) {
			final S input = objectProvider.next();
			final O output = objectProvider.nextOutput();
			System.out.println("Input: " + input + " Output: " + output);
			s.getMaps().forEach(m -> {
				System.out.println("hej: " + input);
				m.applyTransform(input, output);
			}
			);
		}
	}

}