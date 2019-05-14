package lesson1;

import java.io.*;
import java.net.Socket;

public class ClientListener{
    private Socket socket;
    private BufferedReader in;
    private BufferedWriter out;

    private static BufferedReader reader;

    private String address;
    private int port;


    public ClientListener(String address, int port) throws IOException {
        this.address = address;
        this.port = port;
        try {
            this.socket = new Socket(address, port);
        } catch (IOException e) {
            System.err.println("Socket failed");
        }
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            reader = new BufferedReader(new InputStreamReader(System.in));

            new ReadMsg().start();
            new WriteMsg().start();
        } catch (IOException e) {
            ClientListener.this.close();
        }

    }

    private void close() {
        try {
            if (!socket.isClosed()) {
                socket.close();
                in.close();
                out.close();
            }
        }catch (IOException e){

        }

    }

    private class ReadMsg extends Thread {
        @Override
        public void run(){
            String str;
            try {
                while (true) {
                    str = in.readLine();
                    if (str.equals("stop")) {
                        break;
                    } else {
                        System.out.println(str);
                    }
                }
            } catch (IOException e) {

            }
        }
    }

    private class WriteMsg extends Thread {
        @Override
        public void run() {
            while (true) {
                String userWord;
                try {
                    userWord = reader.readLine();
                    if (userWord.equals("stop")) {
                        out.write("stop" + "\n");
                        break;
                    } else {
                        out.write(userWord + "\n");
                    }
                    out.flush();
                } catch (IOException e) {

                }
            }
        }
    }
}
