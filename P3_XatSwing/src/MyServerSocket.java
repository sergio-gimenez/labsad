package src;

import java.io.IOException;
import java.net.ServerSocket;

public class MyServerSocket {
    public ServerSocket serverSocket;

    public MyServerSocket(int port) {
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public MySocket accept() {
        try {
            return new MySocket(serverSocket.accept());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}