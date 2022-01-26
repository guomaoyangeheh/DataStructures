package com.gmy.datastructures.concurrent.threadpool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @Author guomaoyang
 * @Date 2020/11/20
 */
public class ThreadPoolDemo {
    public static ExecutorService executorService = Executors.newFixedThreadPool(1000);
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        List<String> result = new ArrayList<>();
        List<Future<String>> futures = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            Future<String> future = executorService.submit(new MyTask(0L,result));
            futures.add(future);
        }
        for (Future<String> future : futures) {
            future.get();
        }
        System.out.println("result size:" + result.size());

//        Future<String> submit = executorService.submit(new MyTask(1000L));
//        Future<String> submit2 = executorService.submit(new MyTask(1500L));
//        String s = submit.get();
//        System.out.println(s);
//        String s1 = submit2.get();
//        System.out.println(s1);

    }
}
class MyTask implements Callable<String>{
    private volatile   List<String> result;
    private long sleepTime;
    public MyTask(long sleepTime,List<String> result){
        this.sleepTime = sleepTime;
        this.result = result;
    }

    @Override
    public String call() throws Exception {
        //Thread.sleep(sleepTime);
        synchronized (result){
            result.add("我好了" + sleepTime);
        }
        return "我好了" + sleepTime;
    }
}