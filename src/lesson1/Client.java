package lesson1;

import java.io.*;

public class Client {
    public static String ipAddr = "localhost";
    public static int port = 8080;

    public static void main(String[] args) throws IOException {
        new ClientListener(ipAddr, port);
    }


}
