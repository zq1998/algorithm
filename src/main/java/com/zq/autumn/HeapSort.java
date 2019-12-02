package com.zq.autumn;

/**
 * <p>Description: </p>
 * <p>Copyright: Copyright(c) 2019</p>
 * <p>Email: 1012872209@qq.com</p>
 * <p>
 * 1 堆化
 * 2 一个个去掉堆头
 *
 * @author ZQ
 * @date 2019/6/20:17:15
 */
public class HeapSort {

    public static void buildHeap(int[] a, int n) {

        for (int i = n / 2; i >=1; i--) {
            heapify(a, n, i);
        }
    }

    private static void heapify(int[] a, int n, int i) {

        while (true) {
            int maxPos = i;

            if (i * 2 <= n && a[i] < a[i * 2]) {
                maxPos = i * 2;
            }
            if (i * 2 + 1 <= n && a[maxPos] < a[i * 2 + 1]) {
                maxPos = i * 2 + 1;
            }
            if (maxPos == i) break;
            swap(a, i, maxPos);
            i = maxPos;
        }
    }

    public static void sort(int[] a, int n) {

        buildHeap(a, n);
        int k = n;
        while (k > 1) {
            swap(a, 1, k);
            --k;
            heapify(a, k, 1);
        }
    }

    public static void swap(int[] a, int i, int maxPos) {

        int temp = a[i];
        a[i] = a[maxPos];
        a[maxPos] = temp;
    }

    public static void main(String[] args) {
        int[] nums=new int[]{4,5,1,2,5,9};
        sort(nums,nums.length-1);
        for (int num : nums) {
            System.out.println(num);
        }
    }

}
