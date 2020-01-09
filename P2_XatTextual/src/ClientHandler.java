package src;

import java.io.BufferedReader;
import java.io.PrintWriter;

public class ClientHandler extends Thread {

    private String nickname;
    private MySocket socket;
    private BufferedReader in;
    private PrintWriter out;

    public ClientHandler(MySocket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            out.println(WELCOME_MESSAGE);
        }catch (IOException e){
            e.printStackTrace();
                }
    }

}