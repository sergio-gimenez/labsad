package src;

import java.util.Observer;

public class Frm1 implements Observer {
    public Frm1() {
        initComponents();
        this.getRootPane().setDefaultButton(this.btnEnviar);
        Servidor s = new Servidor(5000);
        s.addObserver(this);
        new Thread(s).start();
    }
}