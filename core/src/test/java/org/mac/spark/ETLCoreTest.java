package org.mac.spark;

import static org.junit.Assert.*;

import org.junit.Test;
import org.mac.etl.ETLCore;
import org.mac.etl.ETLMapping;
import org.mac.etl.Segment;

public class ETLCoreTest {

	@Test
	public void testAddMap() {
		ETLCore core = new ETLCore();
		ETLMapping<?, ?> mapping = core.addMap(null, null, null, null);
		assertNotNull(mapping);
	}

	@Test
	public void testAddSegment() {
		ETLCore core = new ETLCore();
		Segment segment = core.addSegment();
		assertNotNull(segment);
	}

}
