package com.zq.autumn;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * <p>Description: </p>
 * <p>Copyright: Copyright(c) 2019</p>
 * <p>Email: 1012872209@qq.com</p>
 *
 * @author ZQ
 * @date 2019/6/18:21:56
 */
public class MyThreadPool {

    private BlockingQueue<Runnable> workQueue;

    private List<WorkerThread> threads = new ArrayList<>();

    private MyThreadPool(int poolSize, BlockingQueue<Runnable> workQueue) {
        this.workQueue = workQueue;
        for (int i = 0; i < poolSize; i++) {
            WorkerThread work = new WorkerThread();
            work.start();
            threads.add(work);
        }
    }

    private void execute(Runnable command) throws InterruptedException {
        workQueue.put(command);
    }

    class WorkerThread extends Thread {
        @Override
        public void run() {
            while (true) {
                Runnable task = null;
                try {
                    task = workQueue.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                task.run();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Runnable> workQueue=new LinkedBlockingQueue<>(2);
        MyThreadPool pool=new MyThreadPool(10,workQueue);
        while (true){
            pool.execute(()-> System.out.println("hello"));
        }
    }
}

