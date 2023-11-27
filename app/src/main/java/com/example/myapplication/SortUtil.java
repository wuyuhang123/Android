package com.example.myapplication;

import java.util.Arrays;
import java.util.Random;

/**
 * @Author wuyuhang
 * @Date 2023/7/4 17:04
 * @Describe
 */
public class SortUtil {

    public static final Random RANDOM = new Random();

    private SortUtil() {

    }

    /**
     * 归并排序
     */
    public static void mergeSort(int[] nums) {
        int[] temp = new int[nums.length];
        int left = 0;
        int right = nums.length - 1;
        mergeSort(nums, temp, left, right);
    }

    private static void mergeSort(int[] nums, int[] temp, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(nums, temp, left, mid);
            mergeSort(nums, temp, mid + 1, right);
            mergeSort(nums, temp, left, mid, right);
        }
    }

    private static void mergeSort(int[] nums, int[] temp, int left, int mid, int right) {
        System.arraycopy(nums, left, temp, left, right - left + 1);
        int p1 = left;
        int p2 = mid + 1;
        for (int i = left; i <= right; i++) {
            if (p1 == mid + 1) {
                nums[i] = temp[p2];
                p2++;
            } else if (p2 == right + 1) {
                nums[i] = temp[p1];
                p1++;
            } else if (temp[p1] < temp[p2]) {
                nums[i] = temp[p1];
                p1++;
            } else {
                nums[i] = temp[p2];
                p2++;
            }
        }
    }

    /**
     * 快速排序
     */
    public static void quickSort(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
    }

    private static void quickSort(int[] nums, int left, int right) {
        if (right > left) {
            int partition = partition(nums, left, right);
            quickSort(nums, left, partition - 1);
            quickSort(nums, partition + 1, right);
        }
    }

    private static int partition(int[] nums, int left, int right) {
        int randomIndex = RANDOM.nextInt(right - left + 1) + left;
        swap(nums, left, randomIndex);
        int pivot = nums[left];
        int lt = left;
        for (int i = left + 1; i <= right; i++) {
            if (pivot > nums[i]) {
                lt++;
                swap(nums, i, lt);
            }
        }
        swap(nums, left, lt);
        return lt;
    }

    private static void swap(int[] nums, int left, int right) {
        int tem = nums[left];
        nums[left] = nums[right];
        nums[right] = tem;
    }

}
