package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Observable;


public class Servidor extends Observable implements Runnable  {

    private int port;

    public Servidor(int port) {
        this.port = port;
    }
    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Insert port number: ");
            String port = in.readLine();

            // Reserved ports. Permission Dennied
            while (Integer.parseInt(port) < 1100) {
                System.out.println("Choose a port value greater than 1100");
                port = in.readLine();
            }
            // Server socket created
            MyServerSocket serverSocket = new MyServerSocket(Integer.parseInt(port));
            // A concurrent map associating sockets to nicks.
            ConcurrentHashMap<String, MySocket> clients = new ConcurrentHashMap<>();

            System.out.println("Server is listening...");

            // Always listening
            while (true) {
                MySocket mySocket = serverSocket.accept();
                String msg = in.readLine();  
                System.out.println(msg);

                // Notify to all the users
                this.setChanged();
                this.notifyObservers(msg);
                this.clearChanged();

                //Close connection with client
                mySocket.close();          
                System.out.println("Client disconnected");    
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}