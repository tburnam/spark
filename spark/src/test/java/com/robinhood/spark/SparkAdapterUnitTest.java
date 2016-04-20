package com.robinhood.spark;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class SparkAdapterUnitTest {

    @Test
    public void testGetDataBounds() {
        TestAdapter testAdapter = new TestAdapter();
        testAdapter.setYData(new float[] {0, 500, 100});
        testAdapter.setXData(new float[] {50, 99, 100});
        Bounds bounds = testAdapter.getDataBounds();

        assertEquals(50f, bounds.minX);
        assertEquals(100f, bounds.maxX);
        assertEquals(0f, bounds.minY);
        assertEquals(500f, bounds.maxY);
    }
}
