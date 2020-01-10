package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

public class ConfigWindow extends JDialog {

    private JTextField userTextField;
    private JTextField hostTextField;
    private JTextField portTextField;

    public ConfigWindow(JFrame frame) {
        super(frame, "Initial chat configuration", true);

        JLabel userLabel = new JLabel("User: ");
        JLabel hostLabel = new JLabel("Host: ");
        JLabel portLabel = new JLabel("Port: ");

        userTextField = new JTextField();
        hostTextField = new JTextField("");
        portTextField = new JTextField("");

        JButton joinButton = new JButton("Join");
        joinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        Container container = this.getContentPane();
        container.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(20, 20, 0, 20);

        gbc.gridx = 0;
        gbc.gridy = 0;
        container.add(userLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        container.add(hostLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        container.add(portLabel, gbc);

        gbc.ipadx = 100;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 1;
        gbc.gridy = 0;
        container.add(userTextField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        container.add(hostTextField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        container.add(hostTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 20, 20, 20);
        container.add(joinButton, gbc);

        this.pack();
        this.setLocation(450, 200); // Window Position
        this.setResizable(false);
        this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE); // Deshabilita el boton de cierre de la ventana
        this.setVisible(true);
    }


    public String getUser() {
        return userTextField.getText();
    }

    public String getHost() {
        return hostTextField.getText();
    }

    public int getPort() {
        return Integer.parseInt(portTextField.getText());
    }

}