package com.chatapp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ChatClientGUI extends JFrame {
    private JTextArea messageArea;
    private JTextField textField;
    private JButton exitButton;
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

        String name = JOptionPane.showInputDialog(this, "Enter your name: ", "Name Entry", JOptionPane.PLAIN_MESSAGE);
        this.setTitle("Java application " + name);

        Color backgroundColor = new Color(240,240,240);
        Color buttonColor = new Color(75,75,75);
        Color textColor = new Color(50,50,50);
        Font textFont = new Font("Arial",Font.PLAIN,14);
        Font buttonFont = new Font("Arial",Font.BOLD,12);

        messageArea = new JTextArea();
        messageArea.setBackground(backgroundColor);
        messageArea.setForeground(textColor);
        messageArea.setFont(textFont);
        messageArea.setEditable(false);
        add(new JScrollPane(messageArea), BorderLayout.CENTER);

        textField = new JTextField();
        textField.setBackground(backgroundColor);
        textField.setForeground(textColor);
        textField.setFont(textFont);
        textField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                String message = "[" + new SimpleDateFormat("HH:mm:ss").format(new Date()) + "]" + name + ": " + textField.getText();
                client.sendMessage(message);
                textField.setText("");
            }
        });

        exitButton = new JButton("exit");
        exitButton.setBackground(buttonColor);
        exitButton.setForeground(Color.white);
        exitButton.setFont(buttonFont);
        exitButton.addActionListener(e -> System.exit(0));
        exitButton.addActionListener(e ->{
            String closeMsg = name + "has left the chat";
            client.sendMessage(closeMsg);
            try{
                Thread.sleep(700);
            }catch(InterruptedException ie){
                Thread.currentThread().interrupt();
            }
        });

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground(backgroundColor);
        bottomPanel.setForeground(textColor);
        bottomPanel.setFont(textFont);
        bottomPanel.add(textField, BorderLayout.CENTER);
        bottomPanel.add(exitButton, BorderLayout.EAST);
        add(bottomPanel, BorderLayout.SOUTH);

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
