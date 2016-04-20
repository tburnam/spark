package com.robinhood.spark;

public class TestAdapter extends SparkAdapter {
    private float[] yData, xData;
    private Bounds dataBounds;

    public void setYData(float[] yData) {
        this.yData = yData;
    }

    public void setXData(float[] xData) {
        this.xData = xData;
    }

    public void setDataBounds(Bounds dataBounds) {
        this.dataBounds = dataBounds;
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

    @Override
    public Bounds getDataBounds() {
        return dataBounds == null
                ? super.getDataBounds()
                : dataBounds;
    }
}
