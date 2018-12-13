package org.mac.spark;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.mac.etl.ETLContext;
import org.mac.etl.ETLMapping;
import org.mac.etl.Segment;

public class ETLContextTest {
	
	@Test
	public void testCreate() {
		@SuppressWarnings("unused")
		ETLContext ctx = new ETLContext();
	}
	
	@Test
	public void testAddMap() {
		ETLContext ctx = new ETLContext();
		ETLMapping<?, ?> mapping = ctx.addMap(null, null, null, null);
		assertNotNull(mapping);
	}

	@Test
	public void testAddSegment() {
		ETLContext ctx = new ETLContext();
		Segment segment = ctx.addSegment();
		assertNotNull(segment);
	}

}
