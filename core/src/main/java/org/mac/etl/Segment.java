package org.mac.etl;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

public class Segment<S, O> {
	private final List<ETLMapping<S, O>> maps = new LinkedList<>();
	private ObjectProvider<S, O> objectProvider;

	public void addMap(ETLMapping<S, O> map) {
		this.maps.add(map);
	}

	public Stream<ETLMapping<S, O>> getMaps() {
		return this.maps.stream();
	}
	
	public void setObjectProvider(ObjectProvider<S, O> objectProvider) {
		this.objectProvider = objectProvider;
	}
	
	public ObjectProvider<S, O> getObjectProvider() {
		return this.objectProvider;
	}

}
