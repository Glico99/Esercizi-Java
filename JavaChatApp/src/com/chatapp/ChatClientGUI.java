package com.chatapp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class ChatClientGUI extends JFrame {
    private JTextArea messageArea;
    private JTextField textField;
    private ChatClient client;

    //In questo caso la keyword super() è utilizzata per richiamare il super-costruttore della classe JFrame, ed accetta un titolo come argomento
    //Le librerie -awt- vengono utilizzate per gestire ciò che viene visualizzato nella GUI
    //Le librerie -Swing- vengono utilizzate per definire il comportamento delle varie componenti della GUI
    //La sintassi try/catch viene utilizzata per creare un nuovo Socket client, sfruttando la classe ChatClient
    //Il metodo onMessageReceived è utilizzato per aggiungere i messaggi degli altri utenti all messageArea quando vengono ricevuti dal client
    public ChatClientGUI(){
        super("Chat Application");
        setSize(400,500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        messageArea = new JTextArea();
        messageArea.setEditable(false);
        add(new JScrollPane(messageArea), BorderLayout.CENTER);

        textField = new JTextField();
        textField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                client.sendMessage(textField.getText());
                textField.setText("");
            }
        });
        add(textField, BorderLayout.SOUTH);

        try{
            this.client = new ChatClient("127.0.0.1", 5000, this::onMessageReceived);
            client.startClient();
        }catch(IOException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Errore nella connessione al server", "Errore di connessione", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }
    
    private void onMessageReceived(String message) {
        SwingUtilities.invokeLater(() -> messageArea.append(message + "\n"));
    }
}
