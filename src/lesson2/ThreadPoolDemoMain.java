package lesson2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolDemoMain {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(() -> {
            System.out.println("hello");
        });
        executorService.submit(() -> {
            System.out.println("hello2");
        });
        executorService.submit(() -> {
            System.out.println("hello3");
        });
        executorService.shutdown();
      //        executorService.shutdownNow(); Stop all threads now but Throw  InterruptedException
        System.out.println(executorService.isShutdown());
    }
}
