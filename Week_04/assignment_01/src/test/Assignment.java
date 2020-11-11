package test;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Assignment {

    public static void main(String[] args) throws InterruptedException, ExecutionException, Exception{

        long start=System.currentTimeMillis();
        int result = 0;
        // 在这里创建一个线程或线程池，
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        // 异步执行 下面方法
        //1 线程池执行callable
//        Callable<Integer> callable = new Callable<Integer>() {
//            @Override
//            public Integer call() throws Exception {
//                return sum();
//            }
//        };
//        try {
//            result = executorService.submit(callable).get();
////            result = executorService.submit(callable).get(10, TimeUnit.MILLISECONDS);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }

        //2.主线程轮询 随便写个class持有int即可
//        final AtomicInteger value = new AtomicInteger(0);
//        executorService.submit(new Task(value));
//        while (value.get() == 0) {
//            System.out.println("value is not changed");
//        }
//        result = value.get();
        //这是得到的返回值

        //3.使用join阻塞当前线程
//        final AtomicInteger value = new AtomicInteger(0);
//        Thread thread = new Thread(new Task(value));
//        thread.start();
//        thread.join();
//        result = value.get();

        //4 CompletableFuture
//        final AtomicInteger atomicResult = new AtomicInteger();
//        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(Assignment::sum);
//        completableFuture.thenAccept(i -> {atomicResult.set(i);});
//        Thread.sleep(1000);
//        result = atomicResult.get();

        //5 countdown latch
//        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final AtomicInteger value = new AtomicInteger();
//        new Thread(new Task5(countDownLatch, value)).start();
//        countDownLatch.await();
//        result = value.get();

        //6 cyclic barrier
        final CyclicBarrier cyclicBarrier = new CyclicBarrier(2);
        new Thread(new Task6(cyclicBarrier, value)).start();
        cyclicBarrier.await();
        result = value.get();

        
        // 确保  拿到result 并输出
        System.out.println("异步计算结果为："+result);

        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");

        // 然后退出main线程
    }

    private static int sum() {
        return fibo(36);
    }

    private static int fibo(int a) {
        if ( a < 2)
            return 1;
        return fibo(a-1) + fibo(a-2);
    }

    private static class Task implements Runnable {
        private AtomicInteger sum;
        @Override
        public void run() {
            sum.addAndGet(sum());
        }

        public Task(AtomicInteger sum) {
            this.sum = sum;
        }
    }

    private static class Task5 implements Runnable {
        private CountDownLatch countDownLatch;
        private AtomicInteger sum;

        public Task5(CountDownLatch countDownLatch, AtomicInteger sum) {
            this.countDownLatch = countDownLatch;
            this.sum = sum;
        }

        @Override
        public void run() {
            sum.set(sum());
            countDownLatch.countDown();
        }
    }

    private static class Task6 implements Runnable {
        private CyclicBarrier cyclicBarrier;
        private AtomicInteger sum;

        public Task6(CyclicBarrier cyclicBarrier, AtomicInteger sum) {
            this.cyclicBarrier = cyclicBarrier;
            this.sum = sum;
        }

        @Override
        public void run(){
            sum.set(sum());
            try {
                cyclicBarrier.await(1000, TimeUnit.MILLISECONDS);
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }
    }
}
