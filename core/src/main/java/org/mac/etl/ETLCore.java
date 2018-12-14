package org.mac.etl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.mac.etl.provider.ObjectProvider;
import org.mac.etl.transform.Transformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ETLCore {

	private static final Logger logger = LoggerFactory.getLogger(ETLCore.class);
	
	private final Map<String, Segment<?,?>> segmentIndex = new HashMap<>();
	private final List<Segment<?,?>> segmentList = new LinkedList<>();
	
	public <S, T> void addTransform(Segment<S, T> segment, Transformer<S, T> transform) {
		segment.addTransform(transform);
	}

	public <S, O> Segment<S, O> appendSegment(String label) {
		Segment<S, O> segment = new Segment<>();
		segment.setLabel(label);
		this.segmentIndex.put(label, segment);
		this.segmentList.add(segment);
		return segment;
	}
	
	public Segment<?,?> getSegment(String label) {
		return segmentIndex.get(label);
	}

	public void run() {
		segmentList.stream()
			.peek(s -> logger.info("Processing segment '{}'...", s.getLabel()))
			.forEachOrdered(s -> processMaps(s));
	}

	private <S, O> void processMaps(Segment<S, O> s) {
		ObjectProvider<S, O> objectProvider = s.getObjectProvider();
		if (objectProvider != null) {
			while (objectProvider.hasNext()) {
				final S input = objectProvider.next();
				final O output = objectProvider.nextOutput();
				s.getTransforms()
					.peek(m -> logger.info("Processing transform '{}'...", m.getClass().getSimpleName()))
					.forEach(m -> {
						m.transform(input, output);
					});
			}
		}
	}

	
	

}