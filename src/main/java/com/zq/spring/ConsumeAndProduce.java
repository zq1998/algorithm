package com.zq.spring;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ConsumeAndProduce {
    static class Producer implements Runnable{
        private BlockingQueue<String> blockingQueue;
        private AtomicInteger count=new AtomicInteger();
        private volatile boolean flag=true;
        Producer(BlockingQueue<String> blockingQueue){
            this.blockingQueue=blockingQueue;
        }
        @Override
        public void run() {
            while (flag){
                String data=count.incrementAndGet()+"thread1";
                try {
                    boolean offer=blockingQueue.offer(data,2, TimeUnit.SECONDS);
//                    if(offer){
//                        System.out.println("success");
//                    }else {
//                        System.out.println("fail");
//                    }
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        private void stop(){
            this.flag=false;
        }
    }
    static class Consumer implements Runnable{
        private BlockingQueue<String> blockingQueue;
        private volatile boolean flag=true;

        Consumer(BlockingQueue<String> blockingQueue){
            this.blockingQueue=blockingQueue;
        }
        @Override
        public void run() {
            while (flag){
                try {
                    String data=blockingQueue.poll(2,TimeUnit.SECONDS);
                    System.out.println(data);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        BlockingQueue<String> blockingQueue=new LinkedBlockingDeque<>(3);
        Producer producer=new Producer(blockingQueue);
        Consumer consumer=new Consumer(blockingQueue);
        Thread t1=new Thread(producer);
        Thread t2=new Thread(consumer);
        t1.start();
        t2.start();
    }
}
