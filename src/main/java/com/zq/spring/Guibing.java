package com.zq.spring;

public class Guibing {

    public static int[] sort(int[] nums,int start,int end){
        int mid=(start+end)/2;
        if(start<end){
            sort(nums,start,mid);
            sort(nums,mid+1,end);
            merge(nums,start,mid,end);
        }
        return nums;
    }
    private static void merge(int[] nums,int start,int mid,int end){
        int[] temp=new int[end-start+1];
        int i=start,j=mid+1,k=0;
        while (i<=mid && j<=end){
            if(nums[i]<nums[j]){
                temp[k++]=nums[i++];
            }else{
                temp[k++]=nums[j++];
            }
        }
        while (i<=mid){
            temp[k++]=nums[i++];
        }
        while (j<=end){
            temp[k++]=nums[j++];
        }
        if(temp.length>0){
            System.arraycopy(temp,0,nums,start,temp.length);
        }
    }

    public static void main(String[] args) {
        int[] nums=new int[]{4,5,1,2,5,9};
        sort(nums,0,nums.length-1);
        for (int num : nums) {
            System.out.println(num);
        }
    }
}
