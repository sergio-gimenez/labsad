/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Client {

    public static MySocket mySocket;
    public static String nick;

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Insert host: ");
        String host = in.readLine();
        System.out.print("Insert the port number: ");
        String port = in.readLine();
        System.out.print("Insert the nickname: ");
        nick = in.readLine();

        mySocket = new MySocket(host, Integer.parseInt(port));

        new Thread() {
            public void run() {
                try {
                    String linia;
                    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
                    while (!(linia = bf.readLine()).equalsIgnoreCase(Keywords.BYE)) {
                        mySocket.println(linia);
                    }
                    System.out.println("Disconnecting");
                    mySocket.println(Keywords.BYE);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        new Thread() {
            public void run() {
                String linia;
                while ((linia = mySocket.readLine()) != null) {
                    if (linia.equalsIgnoreCase(Keywords.NICK)) {
                        mySocket.println(nick);

                    } else if (linia.equalsIgnoreCase(Keywords.BYE)) {
                        break; // Disconnect
                    } else {
                        System.out.println(linia);
                    }
                }
                mySocket.close();
            }
        }.start();
    }
}