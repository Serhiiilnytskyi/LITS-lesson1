package lesson2;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class Example5 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        test();

    }

    private static void test() throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newWorkStealingPool();

        List<Callable<String>> callables = Arrays.asList(
                callable("task1", 2),
                callable("task2", 1),
                callable("task3", 3)
        );
        String result = executor.invokeAny(callables);
        System.out.println(result);
        executor.shutdown();
    }

    private static Callable callable(String result,long sleepSeconds){
        return () -> {
            TimeUnit.SECONDS.sleep(sleepSeconds);
            return result;
        };
    }
}
