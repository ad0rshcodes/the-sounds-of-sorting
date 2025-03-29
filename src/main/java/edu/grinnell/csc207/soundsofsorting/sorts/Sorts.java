package edu.grinnell.csc207.soundsofsorting.sorts;

import java.util.ArrayList;
import java.util.List;

import edu.grinnell.csc207.soundsofsorting.events.CompareEvent;
import edu.grinnell.csc207.soundsofsorting.events.CopyEvent;
import edu.grinnell.csc207.soundsofsorting.events.SortEvent;
import edu.grinnell.csc207.soundsofsorting.events.SwapEvent;

/**
 * A collection of sorting algorithms.
 */
public class Sorts {
    /**
     * swap elements of an array
     * 
     * @param <T> the type of the array
     * @param arr the array to swap
     * @param i   the first index to swap
     * @param j   the second index to swap
     */
    public static <T> void swap(T[] arr, int i, int j) {
        T tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    /**
     * Selection sort
     * 
     * @param <T> the type of elements in the array
     * @param arr the array to sort
     * @return list of sorting events generated during the sort
     */
    public static <T extends Comparable<T>> List<SortEvent<T>> selectionSort(T[] arr) {
        List<SortEvent<T>> events = new ArrayList<>();

        for (int i = 0; i < arr.length - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < arr.length; j++) {
                events.add(new CompareEvent<>(j, minIdx));
                if (arr[j].compareTo(arr[minIdx]) < 0) {
                    minIdx = j;
                }
            }
            if (minIdx != i) {
                T temp = arr[minIdx];
                arr[minIdx] = arr[i];
                arr[i] = temp;
                events.add(new SwapEvent<>(minIdx, i));
            }
        }

        return events;
    }

    /**
     * Insertion sort
     * 
     * @param <T> the type of the array
     * @param arr the array to sort
     * @return list of sorting events generated during the sort
     */
    public static <T extends Comparable<T>> List<SortEvent<T>> insertionSort(T[] arr) {
        List<SortEvent<T>> events = new ArrayList<>();

        for (int i = 1; i < arr.length; i++) {
            T key = arr[i];
            int j = i - 1;

            // Move elements that are greater than key
            // one position ahead of their current position
            while (j >= 0) {
                events.add(new CompareEvent<>(j, i));
                if (arr[j].compareTo(key) > 0) {
                    events.add(new CopyEvent<>(j, j + 1, arr[j]));
                    arr[j + 1] = arr[j];
                    j--;
                } else {
                    break;
                }
            }
            events.add(new CopyEvent<>(i, j + 1, key));
            arr[j + 1] = key;
        }

        return events;
    }

    /**
     * Bubble sort
     * 
     * @param <T> the type of the array
     * @param arr the array to sort
     * @return list of sorting events generated during the sort
     */
    public static <T extends Comparable<T>> List<SortEvent<T>> bubbleSort(T[] arr) {
        List<SortEvent<T>> events = new ArrayList<>();
        int n = arr.length;

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                events.add(new CompareEvent<>(j, j + 1));
                if (arr[j].compareTo(arr[j + 1]) > 0) {
                    T temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    events.add(new SwapEvent<>(j, j + 1));
                }
            }
        }

        return events;
    }

    /**
     * Merge sort
     * 
     * @param <T> the type of the array
     * @param arr the array to sort
     * @return list of sorting events generated during the sort
     */
    public static <T extends Comparable<T>> List<SortEvent<T>> mergeSort(T[] arr) {
        List<SortEvent<T>> events = new ArrayList<>();
        mergeSortHelper(arr, 0, arr.length - 1, events);
        return events;
    }

    private static <T extends Comparable<T>> void mergeSortHelper(T[] arr, int left, int right,
            List<SortEvent<T>> events) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSortHelper(arr, left, mid, events);
            mergeSortHelper(arr, mid + 1, right, events);
            merge(arr, left, mid, right, events);
        }
    }

    @SuppressWarnings("unchecked")
    private static <T extends Comparable<T>> void merge(T[] arr, int left, int mid, int right,
            List<SortEvent<T>> events) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        // Create temporary arrays
        T[] leftArr = (T[]) new Comparable[n1];
        T[] rightArr = (T[]) new Comparable[n2];

        // Copy data to temporary arrays
        for (int i = 0; i < n1; i++) {
            leftArr[i] = arr[left + i];
        }
        for (int j = 0; j < n2; j++) {
            rightArr[j] = arr[mid + 1 + j];
        }

        // Merge the temporary arrays back into arr
        int i = 0, j = 0;
        int k = left;
        while (i < n1 && j < n2) {
            events.add(new CompareEvent<>(left + i, mid + 1 + j));
            if (leftArr[i].compareTo(rightArr[j]) <= 0) {
                events.add(new CopyEvent<>(left + i, k, leftArr[i]));
                arr[k] = leftArr[i];
                i++;
            } else {
                events.add(new CopyEvent<>(mid + 1 + j, k, rightArr[j]));
                arr[k] = rightArr[j];
                j++;
            }
            k++;
        }

        // Copy remaining elements of leftArr if any
        while (i < n1) {
            events.add(new CopyEvent<>(left + i, k, leftArr[i]));
            arr[k] = leftArr[i];
            i++;
            k++;
        }

        // Copy remaining elements of rightArr if any
        while (j < n2) {
            events.add(new CopyEvent<>(mid + 1 + j, k, rightArr[j]));
            arr[k] = rightArr[j];
            j++;
            k++;
        }
    }

    /**
     * Quick sort
     * 
     * @param <T> the type of the array
     * @param arr the array to sort
     * @return list of sorting events generated during the sort
     */
    public static <T extends Comparable<T>> List<SortEvent<T>> quickSort(T[] arr) {
        List<SortEvent<T>> events = new ArrayList<>();
        quickSortHelper(arr, 0, arr.length - 1, events);
        return events;
    }

    private static <T extends Comparable<T>> void quickSortHelper(T[] arr, int low, int high,
            List<SortEvent<T>> events) {
        if (low < high) {
            int pi = partition(arr, low, high, events);
            quickSortHelper(arr, low, pi - 1, events);
            quickSortHelper(arr, pi + 1, high, events);
        }
    }

    private static <T extends Comparable<T>> int partition(T[] arr, int low, int high, List<SortEvent<T>> events) {
        T pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            events.add(new CompareEvent<>(j, high));
            if (arr[j].compareTo(pivot) <= 0) {
                i++;
                if (i != j) {
                    T temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                    events.add(new SwapEvent<>(i, j));
                }
            }
        }

        // Place pivot in correct position
        if (i + 1 != high) {
            T temp = arr[i + 1];
            arr[i + 1] = arr[high];
            arr[high] = temp;
            events.add(new SwapEvent<>(i + 1, high));
        }

        return i + 1;
    }

    /**
     * Heap sort
     * ref: https://www.geeksforgeeks.org/heap-sort/
     * 
     * @param <T> the type of the array
     * @param arr the array to sort
     * @return list of sorting events generated during the sort
     */
    public static <T extends Comparable<T>> List<SortEvent<T>> heapSort(T[] arr) {
        List<SortEvent<T>> events = new ArrayList<>();
        int n = arr.length;

        // Build heap (rearrange array)
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i, events);
        }

        // One by one extract an element from heap
        for (int i = n - 1; i > 0; i--) {
            // Move current root to end
            T temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            events.add(new SwapEvent<>(0, i));

            // Call max heapify on the reduced heap
            heapify(arr, i, 0, events);
        }

        return events;
    }

    private static <T extends Comparable<T>> void heapify(T[] arr, int n, int i, List<SortEvent<T>> events) {
        int largest = i; // Initialize largest as root
        int left = 2 * i + 1; // left = 2*i + 1
        int right = 2 * i + 2; // right = 2*i + 2

        // If left child is larger than root
        if (left < n) {
            events.add(new CompareEvent<>(left, largest));
            if (arr[left].compareTo(arr[largest]) > 0) {
                largest = left;
            }
        }

        // If right child is larger than largest so far
        if (right < n) {
            events.add(new CompareEvent<>(right, largest));
            if (arr[right].compareTo(arr[largest]) > 0) {
                largest = right;
            }
        }

        // If largest is not root
        if (largest != i) {
            T swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;
            events.add(new SwapEvent<>(i, largest));

            // Recursively heapify the affected sub-tree
            heapify(arr, n, largest, events);
        }
    }

    /**
     * Applies a sequence of sorting events to an array
     * 
     * @param <T>    the type of the array
     * @param arr    the array to modify
     * @param events the sequence of sorting events to apply
     */
    public static <T> void eventSort(T[] arr, List<SortEvent<T>> events) {
        // Apply each event in sequence
        for (SortEvent<T> event : events) {
            event.apply(arr);
        }
    }
}
