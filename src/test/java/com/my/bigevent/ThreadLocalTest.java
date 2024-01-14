package com.my.bigevent;

import org.junit.jupiter.api.Test;

public class ThreadLocalTest
{
    @Test
    public void testThreadLocalSetAndGet()
    {
        // 提供一个 ThreadLocal 对象
        ThreadLocal tl=new ThreadLocal();

        // 开启两个线程，lambda写法
        new Thread(()->{
            tl.set("消炎");
            System.out.println(Thread.currentThread().getName()+":"+tl.get());
            System.out.println(Thread.currentThread().getName()+":"+tl.get());
            System.out.println(Thread.currentThread().getName()+":"+tl.get());
        },"蓝色").start();

        new Thread(()->{
            tl.set("药老");
            System.out.println(Thread.currentThread().getName()+":"+tl.get());
            System.out.println(Thread.currentThread().getName()+":"+tl.get());
            System.out.println(Thread.currentThread().getName()+":"+tl.get());
        },"红色").start();

    }
}
