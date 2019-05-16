package lesson2.HomeTask;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Server {
    List<Client> clients;
    ExecutorService executor = Executors.newFixedThreadPool(2);
    List<Future<String>> futures = new ArrayList<Future<String>>();

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        List<Client> clients = new ArrayList<>();
        Client client1 = new Client();
        Client client2 = new Client();
        clients.add(client1);
        clients.add(client2);
        Server server = new Server(clients);
    }

    public Server(List<Client> clients) throws ExecutionException, InterruptedException {
        this.clients = clients;
        String word = "";
        for (int i = 0; i < clients.size(); i++) {
            executor.execute(clients.get(i));
        }
        while (true) {
            for (int i = 0; i <clients.size(); i++) {
                if (clients.get(i).isNewWord()) {
                    System.out.println("Client " + (i + 1) + " send this word: " +
                            clients.get(i).getWord() + "-" +
                            (isPolydrom(clients.get(i).getWord()) ? "this is polindome" : "this isn't polindrome"));

                    clients.get(i).setNewWord(false);
                }
            }
        }
    }



    public Server(Runnable client) {
/*        this.clients = new List<Callable>() {
        }*/
    }

    private boolean isPolydrom(String msg) {
        return msg.replaceAll("\\W", "")
                .equalsIgnoreCase(new StringBuilder(msg.replaceAll("\\W", ""))
                        .reverse().toString());
    }
}
