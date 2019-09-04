package com.example.springboot.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * Created by ZhangPei on 2019/9/4.
 */
public class CallableTrheadTest implements Callable {

    @Override
    public Object call() throws Exception {

        TimeUnit.SECONDS.sleep(5);  //线程休眠五秒
        return 10;
    }
    
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Object> task = new FutureTask<Object>(new CallableTrheadTest());
        Thread thread = new Thread(task);
        thread.start();

        //执行了一些操作，比如先算出1到100的和，然后需要等上一个task的返回值来计算结果

        int sum = 0;
        for (int i = 0; i < 100; i++) {
            sum += i;
        }

        Object o = task.get();      //会阻塞主线程
        if (o instanceof Integer) {
            int o1 = (int) o;
            sum += o1;
        }

        System.out.println("主线程执行完毕" + sum);
    }
}
