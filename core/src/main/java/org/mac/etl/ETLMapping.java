package org.mac.etl;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import org.mac.etl.demo.model.Account;
import org.mac.etl.demo.model.LegacyAccount;

public class ETLMapping<I, O> {

	private Supplier<I> supplier;
	private Function<I, O> transformer;
	private Consumer<O> consumer;

	public void setSupplier(Supplier<I> supplier) {
		this.supplier = supplier;
	}

	public void applyTransform(I legacyAccount, O account) {
		I input = supplier.get();
		System.out.println("INPUT: " + input);
		O output = transformer.apply(input);
		consumer.accept(output);
	}

	public void setConsumer(Consumer<O> consumer) {
		this.consumer = consumer;
	}

	public void setTransformer(Function<I, O> transformer) {
		this.transformer = transformer;
	}

}