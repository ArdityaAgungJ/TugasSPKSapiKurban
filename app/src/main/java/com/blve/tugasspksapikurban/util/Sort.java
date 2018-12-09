package com.blve.tugasspksapikurban.util;

public class Sort {

    private double temp_array_num[];
    private String temp_array_label[];
    private int len;

    public double[] getTemp_array_num() {
        return temp_array_num;
    }

    public String[] getTemp_array_label() {
        return temp_array_label;
    }

    public Sort(double[] nums, String[] label) {

        if (nums == null || nums.length == 0) {
            return;
        }
        this.temp_array_num = nums;
        this.temp_array_label= label;
        len = nums.length;
        quickSort(0, len - 1);
    }

    private void quickSort(int low_index, int high_index) {

        int i = low_index;
        int j = high_index;
        // calculate pivot number
        double pivot = temp_array_num[low_index+(high_index-low_index)/2];
        // Divide into two arrays
        while (i <= j) {
            while (temp_array_num[i] < pivot) {
                i++;
            }
            while (temp_array_num[j] > pivot) {
                j--;
            }
            if (i <= j) {
                exchange(i, j);
                //move index to next position on both sides
                i++;
                j--;
            }
        }
        // call quickSort() method recursively
        if (low_index < j)
            quickSort(low_index, j);
        if (i < high_index)
            quickSort(i, high_index);
    }

    private void exchange(int i, int j) {
        double temp = temp_array_num[i];
        String temp_str = temp_array_label[i];
        temp_array_num[i] = temp_array_num[j];
        temp_array_num[j] = temp;
        temp_array_label[i] = temp_array_label[j];
        temp_array_label[j] = temp_str;
    }

}