package com.zq.autumn;

import java.util.Date;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <p>Description: </p>
 * <p>Copyright: Copyright(c) 2019</p>
 * <p>Email: 1012872209@qq.com</p>
 *
 * @author ZQ
 * @date 2019/6/27:13:54
 */
public class AlternatePrint {

    private static Lock lock = new ReentrantLock();
    private static Condition a = lock.newCondition();
    private static Condition b = lock.newCondition();
    private static Condition c = lock.newCondition();

    private static String MSG = "ASDFGH";
    private static volatile int count = 0;

    static class ThreadA extends Thread {
        @Override
        public void run() {
            try {
                lock.lock();
                while (count < MSG.length()) {
                    if (count % 3 != 0) {
                        a.await();
                    }
                    if(count == MSG.length()) continue;
                    System.out.println("thread a->" + MSG.charAt(count));
                    count++;
                    b.signal();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    static class ThreadB extends Thread {
        @Override
        public void run() {
            try {
                lock.lock();
                while (count < MSG.length()) {
                    if (count % 3 != 1) {
                        b.await();
                    }
                    if(count == MSG.length()) continue;
                    System.out.println("thread b->" + MSG.charAt(count));
                    count++;
                    c.signal();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    static class ThreadC extends Thread {
        @Override
        public void run() {
            try {
                lock.lock();
                while (count < MSG.length()) {
                    if (count % 3 != 2) {
                        c.await();
                    }
                    if(count == MSG.length()) continue;
                    System.out.println("thread c->" + MSG.charAt(count));
                    count++;
                    a.signal();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        new ThreadA().start();
        new ThreadB().start();
        new ThreadC().start();
    }
}
