package lesson2.HomeTask;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;

public class Server {
    List<Callable<String>> clients;
    ExecutorService excutor = Executors.newFixedThreadPool(2);
    List<Future<String>> futures = new ArrayList<Future<String>>();

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        List<Callable<String>> clients= new ArrayList<>();
        Client client1 = new Client();
        clients.add(client1);
        Server server = new Server(clients);
    }

    public Server(List<Callable<String>> clients) throws ExecutionException, InterruptedException {
        this.clients = clients;
        for (int i = 0; i < clients.size(); i++) {
            Future<String> future = excutor.submit(clients.get(i));
            futures.add(future);
            System.out.println(isPolydrom(future.get()));
        }

    }

    public Server(Callable client) {
/*        this.clients = new List<Callable>() {
        }*/
    }

    private boolean isPolydrom(String msg) {
        return msg.replaceAll("\\W", "")
                .equalsIgnoreCase(new StringBuilder(msg.replaceAll("\\W", ""))
                        .reverse().toString());
    }
}
