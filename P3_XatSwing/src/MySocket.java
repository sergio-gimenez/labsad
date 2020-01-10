package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class MySocket {
    public Socket socket;
    public BufferedReader buffReader;
    public PrintWriter pW;
    private String host;

    // Varios constructors en funcio de la necessitat
    public MySocket(int port) {
        try {
            socket = new Socket("localhost", port);
            start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public MySocket(String host, int port) {
        try {
            socket = new Socket(host, port);
            start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public MySocket(Socket s) {
        socket = s;
        host = s.getRemoteSocketAddress().toString();
        start();
    }

    public String getHost(){
        return host;
    }

    public void start() {
        try {
            buffReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            pW = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String readLine() {
        try {
            return buffReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void close() {
        try {
            pW.close();
            buffReader.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void println(String message) {
        pW.println(message);
    }

    public OutputStream getOutputStream() throws IOException {
        return socket.getOutputStream();
    }

    public InputStream getInputStream() throws IOException {
        return socket.getInputStream();
    }


}