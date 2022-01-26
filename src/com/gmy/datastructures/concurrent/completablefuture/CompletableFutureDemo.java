package com.gmy.datastructures.concurrent.completablefuture;

import org.omg.CORBA.PUBLIC_MEMBER;
import sun.jvm.hotspot.gc_implementation.parallelScavenge.PSYoungGen;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author guomaoyang
 * @Date 2020/11/23
 */
public class CompletableFutureDemo {
    private static final ExecutorService executorService = Executors.newFixedThreadPool(10);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // thenApply
        /*CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "123";
        }, executorService).thenApply(result -> result + "第一次转换").thenApply(result -> result + "第二次转换");
        System.out.println(completableFuture.get());

        // exceptionally
//        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            if (1 == 1) {
//                throw new RuntimeException("异常");
//            }
//            return "123";
//        }, executorService).exceptionally(ex -> {
//            System.out.println("发生异常！msg:" + ex.getMessage());
//            return "ex";
//        }).thenApply(result -> result + "第一次转换").thenApply(result -> result + "第二次转换");
        // handle
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (1 == 2) {
                throw new RuntimeException("异常");
            }
            return "123";
        }, executorService).handle((res,ex)-> {
            if(ex != null){
                System.out.println("发生异常！msg:" + ex.getMessage());
                return "ex";
            }
            return res;
        }).thenApply(result -> result + "第一次转换").thenApply(result -> result + "第二次转换");
        System.out.println(completableFuture.get());
        // thenCombine  合并2个线程
        long l = System.currentTimeMillis();
        CompletableFuture<Integer> combine = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 1 + 2 + 3 + 4;
        }).thenCombine(CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 5 + 6 + 7 + 8;
        }), (a, b) -> {
            return a + b;
        });
        System.out.println(combine.get());
        System.out.println("耗时："+(System.currentTimeMillis() - l));

        // anyOf 可以实现“任意个CompletableFuture只要一个成功”
        CompletableFuture<Integer> cf1 = CompletableFuture.supplyAsync(() -> 123);
        CompletableFuture<Integer> cf2 = CompletableFuture.supplyAsync(() -> 456);
        CompletableFuture<Object> objectCompletableFuture = CompletableFuture.anyOf(cf1, cf2);
        System.out.println(objectCompletableFuture.get());
        CompletableFuture<Integer> cf1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("future 开始执行,theadName:"+ Thread.currentThread().getName());
            return 123;
        });

        System.out.println("main sleep");
        Thread.sleep(1000);
        System.out.println("main wakeup");
        System.out.println(cf1.get());

        // allOf()可以实现“所有CompletableFuture都必须成功”
        CompletableFuture<Integer> cf1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("cf1执行完毕");
            return 123;
        });
        CompletableFuture<Integer> cf2 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("cf2执行完毕");
            return 123;
        });*/
        //CompletableFuture<Void> completableFuture = CompletableFuture.allOf(cf1, cf2);
        /*CompletableFuture<Object> completableFuture = CompletableFuture.anyOf(cf1, cf2);
        System.out.println(completableFuture.get());

        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("thread :"+Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "123";
        },executorService);
        subscribe(cf1);
        Thread.sleep(500);
        System.out.println("main finish");*/


    }

    public static void subscribe(CompletableFuture<?> completableFuture){
        completableFuture.whenComplete((res,ex) ->{
            System.out.println("thread :"+Thread.currentThread().getName());
            if(ex != null){
                System.out.println("error,msg:" + ex.getMessage());
            }else{
                System.out.println("success res:" + res);
            }
        });
    }
}
