package lesson2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Example1 {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> {
            String threadName = Thread.currentThread().getName();
            System.out.println("Hello " +
                    threadName);
        });
//        try {
//            System.out.println("attempt to shutdown executor");
//            executor.shutdown();
//            executor.awaitTermination(5, TimeUnit.SECONDS);
//        } catch (InterruptedException e) {
//            System.err.println("task interrupted");
//        } finally {
//            if (!executor.isTerminated()) {
//                System.err.println("cancel non-finished task");
//            }
//            executor.shutdownNow();
//            System.out.println("shutdown finished");
//        }
    }


}
