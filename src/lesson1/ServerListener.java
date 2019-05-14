import java.io.*;
import java.net.*;

class ServerListener extends Thread {

    private Socket socket;
    private BufferedReader in;
    private BufferedWriter out;

    public ServerListener(Socket socket) throws IOException {
        this.socket = socket;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        start();
    }
    @Override
    public void run() {
        String word;
        try {
            try {
                while (true) {
                    word = in.readLine();
                    if(word.equals("stop")) {
                        this.downService();
                        break;
                    }
                    System.out.println("Echoing: " + word);
                    for (ServerListener vr : Server.serverList) {
                        vr.send(word);
                    }
                }
            } catch (NullPointerException ignored) {}


        } catch (IOException e) {
            this.downService();
        }
    }

    private void send(String msg) {
       String polydrom = isPolydrom(msg) ? "is polydrom" : "isn't polydrom";
        try {
            out.write("Client send this message: " + msg + ". This message " + polydrom + "\n");
            out.flush();
        } catch (IOException ignored) {
        }
    }

    private boolean isPolydrom(String msg) {
        return msg.replaceAll("\\W", "")
                .equalsIgnoreCase(new StringBuilder(msg.replaceAll("\\W", ""))
                        .reverse().toString());
    }

    private void downService() {
        try {
            if(!socket.isClosed()) {
                socket.close();
                in.close();
                out.close();
                for (ServerListener vr : Server.serverList) {
                    if(vr.equals(this)) vr.interrupt();
                    Server.serverList.remove(this);
                }
            }
        } catch (IOException ignored) {}
    }
}



