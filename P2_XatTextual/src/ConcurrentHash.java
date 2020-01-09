package src;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.Enumeration;

public class ConcurrentHash {
    ConcurrentHashMap<String, MySocket> clients = new ConcurrentHashMap<>();
    MySocket socket;
    String nick;

    public ConcurrentHash(MySocket s) {
        socket = s;
    }

    public void run() {
        socket.println(Keywords.NICK);
        nick = socket.readLine();

        //Check unique nick
        while (clients.containsKey(nick)) {
            socket.println("Invalid nick, already exists");
            nick = socket.readLine();
        }
        //Addclient
        clients.put(nick, socket);
        sendAllExceptMe(nick, nick + "is in bitches!");
        welcome(socket);
    }

    public void sendAllExceptMe(String nick, String msg) {
        System.out.println(msg);
        clients.forEach((k, v) -> {
            if (k != nick)
                send(k, msg);
        });
    }

    public void welcome(MySocket socket){
        socket.println("\nWelcome to the chat!\n");
        socket.println("Write EXIT to leave");
        socket.println("Users connected:");

        Enumeration users=clients.keys();
        while (users.hasMoreElements()) { 
            socket.println((String) users.nextElement()); 
        } 
        socket.print("Numbers of users connected: " + users.count());
        
    }

    public MySocket getSocket(String nick) {
        return clients.get(nick);
    }

    public void send(String nick, String msg) {
        getSocket(nick).println(msg);
    }

}