package lesson2.HomeTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Client implements Runnable {
    private String word;
    private boolean isNewWord = false;
    private static boolean waitForInput = true;

    @Override
    public void run() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String word = "";
        //TODO  Think how to do this block (while-if-try) shoter ANTIPATERN
        while (!word.equals("stop")) {
            if (!isNewWord && waitForInput) {
                try {
                    waitForInput = false;
                    System.out.print("Enter new word: ");

                    this.word = reader.readLine();
                    isNewWord = true;
                    waitForInput = true;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    synchronized (this) {
                        this.wait(1000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public boolean isNewWord() {
        return isNewWord;
    }

    public void setNewWord(boolean newWord) {
        isNewWord = newWord;
    }
}
