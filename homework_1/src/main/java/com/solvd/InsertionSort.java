package com.solvd;
//Insertion Sort implementation in Java

public class InsertionSort {
    void sort(int[] arr) {
        for (int i = 1; i < arr.length; ++i) {
            int key = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }

            arr[j + 1] = key;
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{10, 4, 2, 7, 5, 13, 1, 6, 0, 23, 6, 15, 9, 17};
        System.out.println("Array before insertion sort: ");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }

        InsertionSort insertionSort = new InsertionSort();
        insertionSort.sort(arr);

        System.out.println("\nSorted array: ");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }


    }
}