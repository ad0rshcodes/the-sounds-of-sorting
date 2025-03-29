package edu.grinnell.csc207.soundsofsorting.events;

import java.util.Arrays;
import java.util.List;

/**
 * Logs a comparison a sort makes between two
 * indices in the array.
 */
public class CompareEvent<T> implements SortEvent<T> {
    private final int index1;
    private final int index2;

    /**
     * Creates a new CompareEvent comparing elements at the given indices
     * 
     * @param index1 first index being compared
     * @param index2 second index being compared
     */
    public CompareEvent(int index1, int index2) {
        this.index1 = index1;
        this.index2 = index2;
    }

    @Override
    public void apply(T[] arr) {
        // overriding `apply` from parent class
        // Compare events don't modify the array
    }

    @Override
    public List<Integer> getAffectedIndices() {
        return Arrays.asList(index1, index2);
    }

    @Override
    public boolean isEmphasized() {
        return false;
    }
}
