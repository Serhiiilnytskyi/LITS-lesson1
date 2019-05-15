package lesson2.HomeTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.Callable;

public class Client implements Callable<String> {

    @Override
    public String call() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String word = null;
        System.out.print("Enter word: ");
        return reader.readLine();
    }
}
