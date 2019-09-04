package com.example.springboot.thread;

/**
 * Created by ZhangPei on 2019/4/1.
 * 以下内容会存在数据刷新不一致的情况，具体原因是每个线程都有自己的工作缓存，而真是的数据是存放在
 * 主存中的，每次线程会去主存读取数据到工作缓存，然后在利用工作缓存中的数据进行运算，运算完毕需要再将数据
 * 更新到主存，而出现下述情况就是因为数据更新不及时导致的。
 * 解决方式：使用volatile变量即可
 */
public class ThreadDemo1 {
    public static void main(String[] args) {
        //以下这种方式达不到变量共享的目的，因为创建的线程对象是两份，因此票数为各线程各拿100张
        /*Thread thread = new Thread(new MyThread(), "线程1");
        thread.start();
        Thread thread1 = new Thread(new MyThread(), "线程2");
        thread1.start();*/

        //如果在使用runnable接口时达到变量共享的目的，则使用同一个线程对象，多次使用thread类进行包装，如下：
        Thread1 thread1 = new Thread1();
        Thread thread2 = new Thread(thread1);
        Thread thread3 = new Thread(thread1);
        thread2.start();
        thread3.start();

    }
}

class Thread1 implements Runnable {
//    private static int total = 100;

    private volatile int total = 100;
    @Override
    public void run() {
        while (true) {
            if (total > 0) {
                System.out.println(Thread.currentThread().getName() + "=" + total);
                total = total - 1;
            } else {
                break;
            }
        }
    }
}
