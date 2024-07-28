package com.chatapp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class ChatClientGUI extends JFrame {
    private JTextArea messageArea;
    private JTextField textField;
    private JButton exitButton;
    private JButton sendButton;
    private ChatClient client;

    //In questo caso la keyword super() è utilizzata per richiamare il super-costruttore della classe JFrame, ed accetta un titolo come argomento
    //Le librerie -awt- vengono utilizzate per gestire ciò che viene visualizzato nella GUI
    //Le librerie -Swing- vengono utilizzate per definire il comportamento delle varie componenti della GUI
    //La sintassi try/catch viene utilizzata per creare un nuovo Socket client, sfruttando la classe ChatClient
    //Il metodo onMessageReceived è utilizzato per aggiungere i messaggi degli altri utenti all messageArea quando vengono ricevuti dal client
    public ChatClientGUI(){
        super("Java Chat App");

        //Finestra inserimento nome
        String name = JOptionPane.showInputDialog(this, "Inserisci il tuo nome: ", "Scelta nome", JOptionPane.PLAIN_MESSAGE);
        this.setTitle("Java Chat App - " + name);

        //Finestra della chat
        setSize(400, 500);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event){
                client.chatLeftMsg(name);
                setDefaultCloseOperation(EXIT_ON_CLOSE);
            }
        });

        //Creazione palette colori e font
        Color backgroundColor = new Color(240,240,240);
        Color buttonColor = new Color(75,75,75);
        Color textColor = new Color(50,50,50);
        Font textFont = new Font("Arial",Font.PLAIN,14);
        Font buttonFont = new Font("Arial",Font.BOLD,12);

        //Layout e posizione dell'area dove vengono visualizzai i messaggi
        messageArea = new JTextArea();
        messageArea.setBackground(backgroundColor);
        messageArea.setForeground(textColor);
        messageArea.setFont(textFont);
        messageArea.setEditable(false);
        add(new JScrollPane(messageArea), BorderLayout.CENTER);

        //Campo per inviare il messaggio
        textField = new JTextField();
        textField.setBackground(backgroundColor);
        textField.setForeground(textColor);
        textField.setFont(textFont);
        textField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                client.sendMessage(name, textField.getText());
                textField.setText("");
            }
        });

        //Tasto invia
        sendButton = new JButton("Invia");
        sendButton.setBackground(backgroundColor);
        sendButton.setForeground(textColor);
        sendButton.setFont(buttonFont);
        sendButton.addActionListener(e -> {
            client.sendMessage(name,textField.getText());
            textField.setText("");
        });

        //Tasto d'uscita
        exitButton = new JButton("Esci");
        exitButton.setBackground(buttonColor);
        exitButton.setForeground(Color.white);
        exitButton.setFont(buttonFont);
        exitButton.addActionListener(e -> System.exit(0));
        exitButton.addActionListener(e ->{
            client.chatLeftMsg(name);
            try{
                Thread.sleep(700);
            }catch(InterruptedException ie){
                Thread.currentThread().interrupt();
            }
        });

        //Layout e posizione del pannello inferiore
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground(backgroundColor);
        bottomPanel.setForeground(textColor);
        bottomPanel.setFont(textFont);
        bottomPanel.add(textField, BorderLayout.CENTER);
        bottomPanel.add(sendButton,BorderLayout.EAST);
        bottomPanel.add(exitButton, BorderLayout.WEST);
        add(bottomPanel, BorderLayout.SOUTH);

        //Connessione al server
        try{
            this.client = new ChatClient("127.0.0.1", 5000, this::onMessageReceived);
            client.startClient();
        }catch(IOException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Errore nella connessione al server", "Errore di connessione", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }
    
    //Metodo per mostrare i messaggi degli altri utenti nell'area messaggi
    //viene passato al client come parametro grazie all'interfaccia funzionale Consumer
    private void onMessageReceived(String message) {
        SwingUtilities.invokeLater(() -> messageArea.append(message + "\n"));
    }
}
