import com.example.springboot.ThreaTest;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class TestThreadCallable {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("主线程开始执行");
        FutureTask<String> futureTask = new FutureTask<String>(new ThreaTest());
        Thread thread = new Thread(futureTask);
        thread.start();
        FutureTask<String> futureTask1 = new FutureTask<String>(new ThreaTest());
        Thread thread_ceshi = new Thread(futureTask1, "ceshi");
        thread_ceshi.start();

        Object o = futureTask.get();
        String s = futureTask1.get();

        System.out.println("主线程执行结束" + o + "，其他线程执行了" + s);

    }
}
