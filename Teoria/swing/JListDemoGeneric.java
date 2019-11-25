/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

/* ListDemo.java requires no other files. */
public class JListDemoGeneric extends JPanel implements ActionListener {

    // define a JList and a DefaultListModel
    JList<String> list;
    DefaultListModel<String> listModel;
    JButton addButton;
    JButton removeButton;
    JTextField entry;

    int counter = 0;

    public JListDemoGeneric() {
        super(new BorderLayout());

        // Add buttons and entry text
        JPanel inp = new JPanel();
        inp.setLayout(new BoxLayout(inp, BoxLayout.LINE_AXIS));
        entry = new JTextField();
        addButton = new JButton("Add Element");
        removeButton = new JButton("Remove Element");

        entry.addActionListener(this);
        addButton.addActionListener(this);
        removeButton.addActionListener(this);

        // create initial listModel
        listModel = new DefaultListModel<>();
        listModel.addElement("element" + counter++);
        listModel.addElement("element" + counter++);
        listModel.addElement("element" + counter++);
        listModel.addElement("element" + counter++);
        listModel.addElement("element" + counter++);
        listModel.addElement("element" + counter++);
        listModel.addElement("element" + counter++);

        // Add action listeners for buttons
        // Manera 1: Creant varios ActionListener
        /*
         * addButton.addActionListener(new ActionListener() { public void
         * actionPerformed(ActionEvent e) { listModel.addElement("Element " +
         * counter++); } }); removeButton.addActionListener(new ActionListener() {
         * public void actionPerformed(ActionEvent e) { if (listModel.getSize() > 0)
         * listModel.removeElementAt(0); } });
         * 
         */

        // Create the list and put it in a scroll pane.
        list = new JList<>(listModel);
        JScrollPane listScrollPane = new JScrollPane(list);

        inp.add(entry);
        inp.add(addButton);
        inp.add(removeButton);
        add(inp, BorderLayout.PAGE_END);

    }

    /**
     * Create the GUI and show it. For thread safety, this method should be invoked
     * from the event-dispatching thread.
     */
    private static void createAndShowGUI() {
        // Set the look and feel.
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
        }

        // Make sure we have nice window decorations.
        JFrame.setDefaultLookAndFeelDecorated(true);

        // Create and set up the window.
        JFrame frame = new JFrame("JList Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create and set up the content pane.
        JListDemoGeneric model = new JListDemoGeneric();
        /* Tambe es valid fer: */ // JComponent newContentPane = new JListDemoGeneric();
        model.setOpaque(true);
        frame.setContentPane(model);

        // Display the window.
        frame.pack();
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

    @Override
    public void actionPerformed(ActionEvent event) {
        Object source = event.getSource();
        String text = entry.getText();
        if (source == removeButton) {
            for (int i = 0; i < listModel.size(); i++) {
                if (listModel.get(i).contains(text)) {
                    listModel.remove(i);
                    break;
                }
            }
        } else {
            int i = 0;
            for (i = 0; i < listModel.size(); i++) {
                if (listModel.get(i).contains(text)) {
                    break;
                }
            }
            if (i == listModel.size())
                listModel.addElement(text);
        }
    }
}
