package com.robinhood.spark;

import android.graphics.RectF;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ScaleHelperUnitTest {
    private RectF contentRect;
    private TestAdapter testAdapter;

    @Before
    public void setup() {
        // by default, all tests are on a canvas of 100 x 100
        contentRect = getRectF(0, 0, 100, 100);
        testAdapter = new TestAdapter();
    }

    @Test
    public void testScaleTwoPoints() {
        testAdapter.setYData(new float[] {0, 1});
        SparkView.ScaleHelper scaleHelper = new SparkView.ScaleHelper(testAdapter, contentRect, 0);

        // assert point 0 is bottom left (0, 100)
        float x0 = scaleHelper.getX(testAdapter.getX(0));
        float y0 = scaleHelper.getY(testAdapter.getY(0));
        assertEquals(0f, x0);
        assertEquals(100f, y0);

        // assert point 1 is top right (100, 0)
        float x1 = scaleHelper.getX(testAdapter.getX(1));
        float y1 = scaleHelper.getY(testAdapter.getY(1));
        assertEquals(100f, x1);
        assertEquals(0f, y1);
    }

    @Test
    public void testScaleThreePoints() {
        testAdapter.setYData(new float[] {0, 1, 0});
        SparkView.ScaleHelper scaleHelper = new SparkView.ScaleHelper(testAdapter, contentRect, 0);

        // assert point 0 is bottom left (0, 100)
        float x0 = scaleHelper.getX(testAdapter.getX(0));
        float y0 = scaleHelper.getY(testAdapter.getY(0));
        assertEquals(0f, x0);
        assertEquals(100f, y0);

        // assert point 1 is top middle (50, 0)
        float x1 = scaleHelper.getX(testAdapter.getX(1));
        float y1 = scaleHelper.getY(testAdapter.getY(1));
        assertEquals(50f, x1);
        assertEquals(0f, y1);

        // assert point 2 is bottom right (100, 100)
        float x2 = scaleHelper.getX(testAdapter.getX(2));
        float y2 = scaleHelper.getY(testAdapter.getY(2));
        assertEquals(100f, x2);
        assertEquals(100f, y2);
    }

    @Test
    public void testLineWidthPadding() {
        testAdapter.setYData(new float[] {0, 1});
        float lineWidth = 10;
        SparkView.ScaleHelper scaleHelper = new SparkView.ScaleHelper(testAdapter, contentRect,
                lineWidth);

        // point 0 should be bottom-left, but offset for the supplied line-width
        float x0 = scaleHelper.getX(testAdapter.getX(0));
        float y0 = scaleHelper.getY(testAdapter.getY(0));
        assertEquals(lineWidth/2, x0);
        assertEquals(100f - (lineWidth/2), y0);

        // point 1 should be top-right, but offset for the supplied line-width
        float x1 = scaleHelper.getX(testAdapter.getX(1));
        float y1 = scaleHelper.getY(testAdapter.getY(1));
        assertEquals(100f - (lineWidth/2), x1);
        assertEquals(lineWidth/2, y1);
    }

    @Test
    public void testNonuniformXPoints() {
        testAdapter.setYData(new float[] {0, 1, 2, 3, 4});
        testAdapter.setxData(new float[] {0, 1, 2, 3, 100});
        SparkView.ScaleHelper scaleHelper = new SparkView.ScaleHelper(testAdapter, contentRect, 0);

        // point 0 is bottom left
        float x0 = scaleHelper.getX(testAdapter.getX(0));
        float y0 = scaleHelper.getY(testAdapter.getY(0));
        assertEquals(0f, x0);
        assertEquals(100f, y0);

        // point 1 is 1 px to the right and 1/4 of the way up
        float x1 = scaleHelper.getX(testAdapter.getX(1));
        float y1 = scaleHelper.getY(testAdapter.getY(1));
        assertEquals(1f, x1);
        assertEquals(75f, y1);

        // point 2 is 1 px to the right and another 1/4 of the way up
        float x2 = scaleHelper.getX(testAdapter.getX(2));
        float y2 = scaleHelper.getY(testAdapter.getY(2));
        assertEquals(2f, x2);
        assertEquals(50f, y2);

        // point 3 is 1 px to the right and another 1/4 of the way up
        float x3 = scaleHelper.getX(testAdapter.getX(3));
        float y3 = scaleHelper.getY(testAdapter.getY(3));
        assertEquals(3f, x3);
        assertEquals(25f, y3);

        // point 4 is top-right
        float x4 = scaleHelper.getX(testAdapter.getX(4));
        float y4 = scaleHelper.getY(testAdapter.getY(4));
        assertEquals(100f, x4);
        assertEquals(0f, y4);
    }

    private RectF getRectF(float left, float top, float right, float bottom) {
        RectF rectF = mock(RectF.class);
        rectF.left = left;
        rectF.top = top;
        rectF.right = right;
        rectF.bottom = bottom;
        when(rectF.width()).thenReturn(right - left);
        when(rectF.height()).thenReturn(bottom - top);
        return rectF;
    }

    private static class TestAdapter extends SparkAdapter {
        private float[] yData, xData;
        private boolean evenlyDistributeXPoints = true;

        public void setYData(float[] yData) {
            this.yData = yData;
        }

        public void setxData(float[] xData) {
            this.xData = xData;
        }

        @Override
        public int getCount() {
            return yData.length;
        }

        @Override
        public Object getItem(int index) {
            return yData[index];
        }

        @Override
        public float getY(int index) {
            return yData[index];
        }

        @Override
        public float getX(int index) {
            return  xData == null
                    ? super.getX(index)
                    : xData[index];
        }
    }
}
