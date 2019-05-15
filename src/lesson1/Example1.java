package lesson1;

public class Example1 {
    public static void main(String[] args) {
        Runnable task = () -> {
            String threadName = Thread.currentThread().getName();
            System.out.println("Hello " +
                    threadName);
        };

        task.run();

        Thread thread = new Thread(task);
        thread.start();

        Thread thread1 = new Thread(task);
        thread1.start();

        System.out.println("Done");

    }

}
