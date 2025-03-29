package edu.grinnell.csc207.soundsofsorting.events;

import java.util.Arrays;
import java.util.List;

/**
 * Logs a copy of a value into an index of the array.
 */
public class CopyEvent<T> implements SortEvent<T> {
    private final int sourceIndex;
    private final int destIndex;
    private final T value;

    /**
     * Creates a new CopyEvent copying a value to a destination index
     * 
     * @param sourceIndex the index where the value comes from
     * @param destIndex   the index where the value is being copied to
     * @param value       the value being copied
     */
    public CopyEvent(int sourceIndex, int destIndex, T value) {
        this.sourceIndex = sourceIndex;
        this.destIndex = destIndex;
        this.value = value;
    }

    @Override
    public void apply(T[] arr) {
        arr[destIndex] = value;
    }

    @Override
    public List<Integer> getAffectedIndices() {
        return Arrays.asList(destIndex);
    }

    @Override
    public boolean isEmphasized() {
        return true;
    }
}
