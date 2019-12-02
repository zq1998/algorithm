package com.zq.spring;

/**
 * 快速排序
 */
public class QuickSort {
    public static void quickSort(int[] arr,int low,int high){
        int i,j,temp,t;
        if(low>high){
            return;
        }
        i=low;
        j=high;
        temp=arr[low];
        while (i<j){
            while (temp <= arr[j] && i<j){
                j--;
            }
            while (temp >=arr[i] && i<j){
                i++;
            }
            if(i<j){
                t = arr[j];
                arr[j] = arr[i];
                arr[i] = t;
            }
        }
        arr[low]=arr[i];
        arr[i]=temp;
        quickSort(arr,low,j-1);
        quickSort(arr,j+1,high);
    }

    public static void main(String[] args) {
        int[] nums=new int[]{3,7,8,2};
        quickSort(nums,0,3);
        for (int num : nums) {
            System.out.println(num);
        }
    }
}
