import java.awt.*;
import javax.swing.*;

/**
 * general purpose powerful free layouts: JGoodies' FormLayout MigLayout
 * DesignGridLayout
 */

public class Xat {

    private static void createAndShowGUI() {

        // Set the look and feel.
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
        }

        // Make sure we have nice window decorations.
        JFrame.setDefaultLookAndFeelDecorated(true);

        // Create and set up the window.
        JFrame frame = new JFrame("Chat template");
        frame.setLayout(new BorderLayout(5, 5));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create an output JPanel and add a JTextArea(20, 30) inside a JScrollPane
        JPanel outP = new JPanel();
        outP.setLayout(new BoxLayout(outP, BoxLayout.LINE_AXIS));

        //Add chat text area
        JTextArea textArea = new JTextArea(/* 20, 30 */);
        textArea.setEditable(false);
        outP.add(new JScrollPane(textArea));

        //Add users text area
        JTextArea usersConnected = new JTextArea();
        usersConnected.setEditable(false);
        usersConnected.setColumns(20);
        usersConnected.setRows(5);
        outP.add(new JScrollPane(usersConnected));

        // Create an input JPanel and add a JTextField(25) and a JButton
        JPanel inP = new JPanel();
        inP.setLayout(new BoxLayout(inP, BoxLayout.LINE_AXIS));
        JTextField entry = new JTextField(/* 25 */);
        JButton sendButton = new JButton("Send");
        inP.add(entry);
        inP.add(sendButton);

        // add panels to main frame
        frame.add(inP, BorderLayout.PAGE_END);
        frame.add(outP, BorderLayout.CENTER);

        // Display the window centered.
        // frame.pack();
        frame.setSize(400, 500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        // Schedule a job for the event-dispatching thread:
        // creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
