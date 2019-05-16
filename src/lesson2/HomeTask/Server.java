package lesson2.HomeTask;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Server {
    List<Client> clients;
    static ExecutorService executor = Executors.newFixedThreadPool(2);

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
        //TODO  Think how to do this block (while-for-if) shoter ANTIPATERN
        while (true) {
            for (int i = 0; i <clients.size(); i++) {
                if (clients.get(i).isNewWord()) {
                    word = clients.get(i).getWord();
                    System.out.println("Client " + (i + 1) + " send this word: " + word + "-" +
                            (isPolydrom(word) ? "this is polindome" : "this isn't polindrome"));
                    clients.get(i).setNewWord(false);
                }

                //TODO complete shutdown executor if all Clients are stopped
/*                if (word.equals("stop")){
                    stopServer();
                    System.exit(1);
                }*/
            }
        }
    }

    public Server(Client client) {
        this.clients.add(client);
    }

    private boolean isPolydrom(String msg) {
        return msg.replaceAll("\\W", "")
                .equalsIgnoreCase(new StringBuilder(msg.replaceAll("\\W", ""))
                        .reverse().toString());
    }

    public static void stopServer(){
        try {
            System.out.println("attempt to shutdown executor");
            executor.shutdown();
            executor.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            System.err.println("task interrupted");
        } finally {
            if (!executor.isTerminated()) {
                System.err.println("cancel non-finished task");
            }
            executor.shutdownNow();
            System.out.println("shutdown finished");
        }
    }

}
