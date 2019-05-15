package lesson2;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolDemoMain {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

//        CompletableFuture<Integer> comFuture = executorService.submit(() -> {
//           return 22;
//        });
//
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
