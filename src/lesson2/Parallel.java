package lesson2;

import java.util.Random;
import java.util.stream.Stream;

public class Parallel {
    public static void main(String[] args) {
        long array []  = new long[100_000_000];
        long time1;
        long time2;
        time1 = System.currentTimeMillis();
        Random random = new Random();

        Stream.of(array).map(a -> random.nextInt(100000)).parallel().sorted();
        time2 = System.currentTimeMillis();
        System.out.println(time2-time1);

        //todo create comparable to show result of work .parallel()


    }
}
