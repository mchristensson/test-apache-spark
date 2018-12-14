package org.mac.etl;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

import org.mac.etl.provider.ObjectProvider;
import org.mac.etl.transform.Transformer;

public class Segment<S, T> {
	
	private final List<Transformer<S, T>> transforms = new LinkedList<>();
	private ObjectProvider<S, T> objectProvider;
	private String label;

	public Stream<Transformer<S, T>> getTransforms() {
		return this.transforms.stream();
	}

	public void addTransform(Transformer<S, T> transform) {
		this.transforms.add(transform);
	}
	
	public void setObjectProvider(ObjectProvider<S, T> objectProvider) {
		this.objectProvider = objectProvider;
	}
	
	public ObjectProvider<S, T> getObjectProvider() {
		return this.objectProvider;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}
	
}
