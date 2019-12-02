package com.zq.spring;

public class HeapSort {
    public static void main(String[] args) {
        int[] a=new int[]{0,9,8};
        Sort(a,a.length-1);
        for (int i:a){
            System.out.println(i);
        }
    }
    private static void Sort(int[] a, int size){
        for(int i=size/2;i>=1;i--){
            MaxHeapify(a,i,size);
        }
        for(int i=size;i>=2;i--){
            int temp=a[i];
            a[i]=a[1];
            a[1]=temp;
            MaxHeapify(a,1,i-1);
        }
    }
    private static void MaxHeapify(int[] a, int index, int size){
        int l=2*index;
        int r=2*index+1;
        int largest=index;
        if(l<=size && a[l]>a[index]){
            largest=l;
        }
        if(r<=size && a[r]>a[largest]){
            largest=r;
        }
        if(largest!=index){
            int temp=a[largest];
            a[largest]=a[index];
            a[index]=temp;
            MaxHeapify(a,largest,size);
        }
    }
}
