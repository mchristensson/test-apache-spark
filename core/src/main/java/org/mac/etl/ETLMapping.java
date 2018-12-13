package org.mac.etl;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import org.mac.etl.demo.model.Account;
import org.mac.etl.demo.model.LegacyAccount;

public class ETLMapping<S, O> {

	private Supplier<S> supplier;
	private Function<S, O> transformer;
	private Consumer<O> consumer;

	public void setSupplier(Supplier<S> supplier) {
		this.supplier = supplier;
	}

	public void applyTransform(LegacyAccount legacyAccount, Account account) {
		S input = supplier.get();
		O output = transformer.apply(input);
		consumer.accept(output);
	}

	public void setConsumer(Consumer<O> consumer) {
		this.consumer = consumer;
	}

	public void setTransformer(Function<S, O> transformer) {
		this.transformer = transformer;
	}

}