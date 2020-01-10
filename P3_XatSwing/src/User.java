package src;
 
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
 
public class User implements Runnable {
 
    private int port;
    private String msg;
    private String host;
 
    public User(String host, int port, String msg) {
        this.port = port;
        this.msg = msg;
        this.host = host;
    }
 
    @Override
    public void run() {
        //Puerto del servidor
        DataOutputStream out;
 
        try {
            //Creo el socket para conectarme con el cliente
            MySocket mySocket = new MySocket(host, port);
 
            out = new DataOutputStream(mySocket.getOutputStream());
 
            //Envio un mensaje al cliente
            out.writeUTF(msg);
 
            mySocket.close();
 
        } catch (IOException ex) {
            //TODO: Canviar excepcio
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
 
    }
}