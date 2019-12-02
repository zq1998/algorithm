package com.zq.spring;



public class ThreadPrint {
    static class Print{
        private int flag=1;
        private int count=1;
        synchronized void printNum(){
            while (flag!=1){
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            count++;
            System.out.println(count);

            flag=2;
            notify();
        }
        synchronized void printChar(){
            while (flag!=2){
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println((char) count+'a');
            count++;
            flag=1;
            notify();
        }

    }
    public static void main(String[] args){
        Print print=new Print();
        new Thread(() -> {
            for (int i = 0;i < 26;i++) {
                print.printNum();
            }
        }).start();
        new Thread(() -> {
            for (int i = 0;i < 26;i++) {
                print.printChar();
            }
        }).start();
    }
}

