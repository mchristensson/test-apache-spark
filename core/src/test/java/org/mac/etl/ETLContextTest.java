package org.mac.etl;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class ETLContextTest {
	
	@Test
	public void testCreate() {
		@SuppressWarnings("unused")
		ETLContext ctx = new ETLContext();
	}
	
	@Test
	public void testAddMap() {
		ETLContext ctx = new ETLContext();
		Segment<?,?> segment = ctx.addSegment("foo");
		ctx.addMap(segment, null);
	}

	@Test
	public void testAddSegment() {
		ETLContext ctx = new ETLContext();
		Segment<?,?> segment = ctx.addSegment("foo");
		assertNotNull(segment);
	}

}
