package lesson2;

import java.beans.FeatureDescriptor;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ThreadPoolDemoMain {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Integer future = executorService.submit(() -> {
            return 43;
        }).get();
        System.out.println(future);

        Integer future2 = executorService.submit(() -> {
            return 44;
        }).get();
        System.out.println(future2);

        Integer future3 = executorService.submit(() -> {
            return 45;
        }).get();
        System.out.println(future3);


        executorService.shutdown();

//        executorService.submit(() -> {
//            System.out.println("hello2");
//        });
//        executorService.submit(() -> {
//            System.out.println("hello3");
//        });
//        executorService.shutdownNow(); Stop all threads now but Throw  InterruptedException
//        System.out.println(executorService.isShutdown()); Check if service is closed
    }
}
