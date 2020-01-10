package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ConcurrentHashMap;


public class Servidor {

    public static void main(String[] args) {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Insert port number: ");
            String port = in.readLine();

            // Reserved ports. Permission Dennied
            while (Integer.parseInt(port) < 1100) {
                System.out.println("Choose a port value greater than 1100");
                port = in.readLine();
            }

            MyServerSocket serverSocket = new MyServerSocket(Integer.parseInt(port));
            // A concurrent map associating sockets to nicks.
            ConcurrentHashMap<String, MySocket> clients = new ConcurrentHashMap<>();

            System.out.println("Server is listening...");

            while (true) {
                MySocket socket = serverSocket.accept();
                (new User(socket, clients)).start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}