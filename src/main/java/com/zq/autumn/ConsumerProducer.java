package com.zq.autumn;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * <p>Description: </p>
 * <p>Copyright: Copyright(c) 2019</p>
 * <p>Email: 1012872209@qq.com</p>
 *
 * @author ZQ
 * @date 2019/6/27:13:36
 */
public class ConsumerProducer {
    public static final String EXIT_MSG = "bye";

    public static void main(String[] args) {

        BlockingQueue<String> queue=new ArrayBlockingQueue<>(3);
        Producer producer=new Producer(queue);
        Consumer consumer=new Consumer(queue);
        new Thread(producer).start();
        new Thread(consumer).start();

    }

    static class Consumer implements Runnable{

        private BlockingQueue<String> queue;

        public Consumer(BlockingQueue<String> queue){
            this.queue=queue;
        }

        @Override
        public void run() {

            String msg;
                try {
                    while (!EXIT_MSG.equalsIgnoreCase((msg=queue.take()))){
                        System.out.println("consume"+msg);
                        Thread.sleep(1L);
                    }
                    System.out.println(EXIT_MSG);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

        }
    }

    static class Producer implements Runnable {
        private BlockingQueue<String> queue;
        public Producer(BlockingQueue<String> q) {
            this.queue = q;
        }
        @Override
        public void run() {
            for (int i = 0; i < 20; i++) {
                try{
                    Thread.sleep(1L);
                    String msg = "Message" + i;
                    System.out.println("Produced new item: " + msg);
                    queue.put(msg);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            try {
                System.out.println("Time to say good bye!");
                queue.put(EXIT_MSG);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
