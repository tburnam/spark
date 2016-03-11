package com.robinhood.spark;

/**
 *
 */

import android.database.DataSetObservable;
import android.database.DataSetObserver;

/**
 * A simplified adapter class - evenly distributes your points along the x axis, does not
 * draw a baseline, and has support for registering/notifying {@link DataSetObserver}s when
 * data is changed.
 */
public abstract class SparkAdapter {
    private DataSetObservable observable = new DataSetObservable();

    /**
     * returns the number of points to be drawn
     */
    public abstract int getCount();

    /**
     * returns the object at the given index
     */
    public abstract Object getItem(int index);

    /**
     * Gets the float representation of the X value of the point at the given index.
     */
    public float getX(int index) {
        return index;
    }

    /**
     * Gets the float representation of the Y value of the point at the given index.
     */
    public abstract float getY(int index);

    /**
     * Return true if you wish to draw a "baseLine" - a horizontal line across the graph used
     * to compare the rest of the graph's points against.
     */
    public boolean hasBaseLine() {
        return false;
    }

    /**
     * Gets the float representation of the Y value of the desired baseLine.
     */
    public float getBaseLine() {
        return 0;
    }

    /**
     * Notifies the attached observers that the underlying data has been changed and any View
     * reflecting the data set should refresh itself.
     */
    public final void notifyDataSetChanged() {
        observable.notifyChanged();
    }

    /**
     * Notifies the attached observers that the underlying data is no longer valid or available.
     * Once invoked this adapter is no longer valid and should not report further data set
     * changes.
     */
    public final void notifyDataSetInvalidated() {
        observable.notifyInvalidated();
    }

    /**
     * Register a {@link DataSetObserver} to listen for updates to this adapter's data.
     * @param observer    the observer to register
     */
    public final void registerDataSetObserver(DataSetObserver observer) {
        observable.registerObserver(observer);
    }

    /**
     * Unregister a {@link DataSetObserver} from updates to this adapter's data.
     * @param observer    the observer to unregister
     */
    public final void unregisterDataSetObserver(DataSetObserver observer) {
        observable.unregisterObserver(observer);
    }
}
