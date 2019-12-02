package com.zq.spring;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyQueue<T>{
    private Object[] elements;
    private Lock lock=new ReentrantLock();
    private Condition notEmpty=lock.newCondition();
    private Condition notFull=lock.newCondition();
    private int length=0,addIndex=0,removeIndex=0;
    public MyQueue(int size){
        elements=new Object[size];
    }
    public void add(T object) {
        lock.lock();
        try {
            while (length==elements.length){
                notFull.await();
            }
            elements[addIndex]=object;
            if(addIndex++ ==elements.length){
                addIndex=0;
            }
            length++;
            notEmpty.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public T remove() throws InterruptedException{
        lock.lock();
        try {
            while (0 == length){
                notEmpty.await();
            }
            Object element=elements[removeIndex];
            if(removeIndex++ == elements.length){
                removeIndex=0;
            }
            length--;
            notFull.signal();
            return (T)element;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return null;
    }
}
