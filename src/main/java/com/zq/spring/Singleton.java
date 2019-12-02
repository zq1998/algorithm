package com.zq.spring;

public class Singleton {
    private static volatile Singleton singleton1;
    private Singleton(){

    }
    public Singleton getInstance(){
        if(singleton1==null){
            synchronized (this){
                if(singleton1==null){
                    singleton1=new Singleton();
                }
            }
        }
        return singleton1;
    }
}
