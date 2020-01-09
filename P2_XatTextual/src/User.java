package src;

import java.util.concurrent.ConcurrentHashMap;
import java.util.Enumeration;

public class User extends Thread {
    MySocket socket;
    ConcurrentHashMap<String, MySocket> clients;
    String nick;

    public User(MySocket s, ConcurrentHashMap<String,MySocket> c) {
        socket = s;
        clients = c;
    }

    public void run() {
        socket.println(Keywords.NICK);
        nick = socket.readLine();

        // Check unique nick
        while (clients.containsKey(nick)) {
            socket.println("Invalid nick, already exists");
            nick = socket.readLine();
        }
        // Addclient
        clients.put(nick, socket);
        sendOthers(nick, nick + " has entered the chatroom");
        welcome(socket);

        String msg;
        while ((msg = socket.readLine()) != null) {
            if (msg.equalsIgnoreCase(Keywords.DISCONNECT)) {
                clients.remove(nick);
                sendOthers(nick, nick + " has left the chatroom");
                socket.close();
                break;
            }
            sendEverybody(nick + "> " + msg);
        }
    }

    public void sendEverybody(String msg) {
        System.out.println(msg);
        // TODO USE ONLY KEY
        clients.forEach((nickKey, socketValue) -> send((String) nickKey, msg));
    }

    // Send message to everyone except me
    public void sendOthers(String nick, String msg) {
        System.out.println(msg);
        // TODO USE ONLY KEY
        clients.forEach((nickKey, socketValue) -> {
            if (nickKey != nick)
                send((String) nickKey, msg);
        });
    }

    public void welcome(MySocket socket) {
        socket.println("\nWelcome to the chat!\n");
        socket.println("Write BYE to leave the chatroom");
        socket.println("Numbers of users in the chatroom: " + clients.mappingCount());
        socket.println("Users in the chatroom:");
        Enumeration<String> users = clients.keys();
        while (users.hasMoreElements()) {
            socket.println((String) users.nextElement());
        }
    }

    public MySocket getSocket(String nick) {
        return clients.get(nick);
    }

    public void send(String nick, String msg) {
        getSocket(nick).println(msg);
    }

}