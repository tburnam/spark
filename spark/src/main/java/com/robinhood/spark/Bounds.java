package com.robinhood.spark;

/**
 * Value class that holds the min and max x and y values for the data set to be graphed.
 */
public final class Bounds {
    float minX, maxX, minY, maxY;

    public Bounds() {
    }

    public Bounds(float minX, float maxX, float minY, float maxY) {
        this.minX = minX;
        this.maxX = maxX;
        this.minY = minY;
        this.maxY = maxY;
    }

    public void set(float minX, float maxX, float minY, float maxY) {
        this.minX = minX;
        this.maxX = maxX;
        this.minY = minY;
        this.maxY = maxY;
    }
}
