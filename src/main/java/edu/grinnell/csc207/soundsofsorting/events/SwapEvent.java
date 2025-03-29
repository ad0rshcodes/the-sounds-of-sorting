package edu.grinnell.csc207.soundsofsorting.events;

import java.util.Arrays;
import java.util.List;

/**
 * Logs a swap between two indices of the array.
 */
public class SwapEvent<T> implements SortEvent<T> {
    private final int index1;
    private final int index2;

    /**
     * Creates a new SwapEvent swapping elements at the given indices
     * 
     * @param index1 first index being swapped
     * @param index2 second index being swapped
     */
    public SwapEvent(int index1, int index2) {
        this.index1 = index1;
        this.index2 = index2;
    }

    @Override
    public void apply(T[] arr) {
        T temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }

    @Override
    public List<Integer> getAffectedIndices() {
        return Arrays.asList(index1, index2);
    }

    @Override
    public boolean isEmphasized() {
        return true;
    }
}
