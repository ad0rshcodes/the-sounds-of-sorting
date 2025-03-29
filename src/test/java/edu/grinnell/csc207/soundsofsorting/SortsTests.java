package edu.grinnell.csc207.soundsofsorting;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import org.junit.jupiter.api.Test;

import edu.grinnell.csc207.soundsofsorting.events.SortEvent;
import edu.grinnell.csc207.soundsofsorting.sorts.Sorts;

public class SortsTests {
    /**
     * @param <T> the carrier type of the array
     * @param arr the array to check
     * @return true iff <code>arr</code> is sorted.
     */
    public static <T extends Comparable<? super T>> boolean sorted(T[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i].compareTo(arr[i + 1]) > 0) {
                return false;
            }
        }
        return true;
    }

    public static Integer[] makeTestArray() {
        return new Integer[] {
                3, 7, 9, 1, 2,
                18, 16, 15, 19, 8,
                14, 12, 5, 13, 4,
                6, 0, 17, 11, 10
        };
    }

    public void testSort(Consumer<Integer[]> func) {
        Integer[] arr = makeTestArray();
        func.accept(arr);
        assertTrue(sorted(arr));
    }

    public void testSortWithEvents(Consumer<Integer[]> func) {
        Integer[] arr = makeTestArray();
        Integer[] arrCopy = Arrays.copyOf(arr, arr.length);
        func.accept(arr);
        assertTrue(sorted(arr));
    }

    public void testSortEvents(Integer[] arr, List<SortEvent<Integer>> events) {

        Integer[] arrCopy = Arrays.copyOf(arr, arr.length);

        Integer[] sortedArr = Arrays.copyOf(arr, arr.length);

        Arrays.sort(sortedArr);

        Sorts.eventSort(arrCopy, events);

        assertArrayEquals(sortedArr, arrCopy);
    }

    @Test
    public void testBubbleSort() {
        // random array
        testSort(Sorts::bubbleSort);

        // already sorted array
        Integer[] sortedArr = new Integer[] { 1, 2, 3, 4, 5 };
        Sorts.bubbleSort(sortedArr);
        assertTrue(sorted(sortedArr));

        // reverse sorted array
        Integer[] reverseArr = new Integer[] { 5, 4, 3, 2, 1 };
        Sorts.bubbleSort(reverseArr);
        assertTrue(sorted(reverseArr));

        // containing duplicates
        Integer[] duplicateArr = new Integer[] { 3, 1, 3, 2, 3, 4, 3 };
        Sorts.bubbleSort(duplicateArr);
        assertTrue(sorted(duplicateArr));
    }

    @Test
    public void testBubbleSortEvents() {
        Integer[] arr = makeTestArray();
        Integer[] arrCopy = Arrays.copyOf(arr, arr.length);
        List<SortEvent<Integer>> events = Sorts.bubbleSort(arrCopy);
        testSortEvents(arr, events);
    }

    @Test
    public void testInsertionSort() {
        // Test with random array
        testSort(Sorts::insertionSort);

        // Test with already sorted array
        Integer[] sortedArr = new Integer[] { 1, 2, 3, 4, 5 };
        Sorts.insertionSort(sortedArr);
        assertTrue(sorted(sortedArr));

        // Test with reverse sorted array
        Integer[] reverseArr = new Integer[] { 5, 4, 3, 2, 1 };
        Sorts.insertionSort(reverseArr);
        assertTrue(sorted(reverseArr));

        // Test with containing duplicates
        Integer[] duplicateArr = new Integer[] { 3, 1, 3, 2, 3, 4, 3 };
        Sorts.insertionSort(duplicateArr);
        assertTrue(sorted(duplicateArr));
    }

    @Test
    public void testInsertionSortEvents() {
        Integer[] arr = makeTestArray();
        List<SortEvent<Integer>> events = Sorts.insertionSort(arr);
        assertTrue(sorted(arr));
        testSortEvents(arr, events);
    }

    @Test
    public void testSelectionSort() {
        // Test with random array
        testSort(Sorts::selectionSort);

        // Test with already sorted array
        Integer[] sortedArr = new Integer[] { 1, 2, 3, 4, 5 };
        Sorts.selectionSort(sortedArr);
        assertTrue(sorted(sortedArr));

        // Test with reverse sorted array
        Integer[] reverseArr = new Integer[] { 5, 4, 3, 2, 1 };
        Sorts.selectionSort(reverseArr);
        assertTrue(sorted(reverseArr));

        // Test with array containing duplicates
        Integer[] duplicateArr = new Integer[] { 3, 1, 3, 2, 3, 4, 3 };
        Sorts.selectionSort(duplicateArr);
        assertTrue(sorted(duplicateArr));
    }

    @Test
    public void testSelectionSortEvents() {
        Integer[] arr = makeTestArray();
        Integer[] arrCopy = Arrays.copyOf(arr, arr.length);
        List<SortEvent<Integer>> events = Sorts.selectionSort(arrCopy);
        testSortEvents(arr, events);
    }

    @Test
    public void testMergeSort() {
        // Test with random array
        testSort(Sorts::mergeSort);

        // Test with already sorted array
        Integer[] sortedArr = new Integer[] { 1, 2, 3, 4, 5 };
        Sorts.mergeSort(sortedArr);
        assertTrue(sorted(sortedArr));

        // Test with reverse sorted array
        Integer[] reverseArr = new Integer[] { 5, 4, 3, 2, 1 };
        Sorts.mergeSort(reverseArr);
        assertTrue(sorted(reverseArr));

        // Test with array containing duplicates
        Integer[] duplicateArr = new Integer[] { 3, 1, 3, 2, 3, 4, 3 };
        Sorts.mergeSort(duplicateArr);
        assertTrue(sorted(duplicateArr));
    }

    @Test
    public void testMergeSortEvents() {
        Integer[] arr = makeTestArray();
        List<SortEvent<Integer>> events = Sorts.mergeSort(arr);
        assertTrue(sorted(arr));
        testSortEvents(arr, events);
    }

    @Test
    public void testQuickSort() {
        // Test with random array
        testSort(Sorts::quickSort);

        // Test with already sorted array
        Integer[] sortedArr = new Integer[] { 1, 2, 3, 4, 5 };
        Sorts.quickSort(sortedArr);
        assertTrue(sorted(sortedArr));

        // Test with reverse sorted array
        Integer[] reverseArr = new Integer[] { 5, 4, 3, 2, 1 };
        Sorts.quickSort(reverseArr);
        assertTrue(sorted(reverseArr));

        // Test with array containing duplicates
        Integer[] duplicateArr = new Integer[] { 3, 1, 3, 2, 3, 4, 3 };
        Sorts.quickSort(duplicateArr);
        assertTrue(sorted(duplicateArr));
    }

    @Test
    public void testQuickSortEvents() {
        Integer[] arr = makeTestArray();
        Integer[] arrCopy = Arrays.copyOf(arr, arr.length);
        List<SortEvent<Integer>> events = Sorts.quickSort(arrCopy);
        testSortEvents(arr, events);
    }

    @Test
    public void testHeapSort() {
        // Test with random array
        testSort(Sorts::heapSort);

        // Test with already sorted array
        Integer[] sortedArr = new Integer[] { 1, 2, 3, 4, 5 };
        Sorts.heapSort(sortedArr);
        assertTrue(sorted(sortedArr));

        // Test with reverse sorted array
        Integer[] reverseArr = new Integer[] { 5, 4, 3, 2, 1 };
        Sorts.heapSort(reverseArr);
        assertTrue(sorted(reverseArr));

        // Test with array containing duplicates
        Integer[] duplicateArr = new Integer[] { 3, 1, 3, 2, 3, 4, 3 };
        Sorts.heapSort(duplicateArr);
        assertTrue(sorted(duplicateArr));
    }

    @Test
    public void testHeapSortEvents() {
        Integer[] arr = makeTestArray();
        Integer[] arrCopy = Arrays.copyOf(arr, arr.length);
        List<SortEvent<Integer>> events = Sorts.heapSort(arrCopy);
        testSortEvents(arr, events);
    }
}