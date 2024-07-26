package com.chatapp;

import java.net.*;
import java.io.*;
import java.util.*;

public class ChatServer {
    private static List<ClientHandler> clients = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        //inizializza un ServerSocket che ascolta le connessioni client
        @SuppressWarnings("resource")
        ServerSocket serverSocket = new ServerSocket(5000);
        System.out.println("Attesa dei client: " + serverSocket);

        //crea un Socket ed un Thread collegato ad esso per ogni connessione client
        while (true){
            //utilizza il metodo accept() di serverSocket per istanziare un Socket
            Socket clientSocket = serverSocket.accept();

            //Spawna i threads, che sono rappresentati da un istanza della classe ClientHandler
            ClientHandler clientThread = new ClientHandler(clientSocket, clients);
            clients.add(clientThread);
            new Thread(clientThread).start();
        }
    }
}

//La classe ClientHandler gestisce le operazioni da eseguire per ogni Thread
class ClientHandler implements Runnable {
    private Socket clientSocket;
    private List<ClientHandler> clients;
    private PrintWriter out;
    private BufferedReader in;

    public ClientHandler(Socket socket, List<ClientHandler> clients) throws IOException {
        this.clientSocket = socket;
        this.clients = clients;
        this.out = new PrintWriter(clientSocket.getOutputStream(), true);
        this.in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }
    //Il metodo run() è un metodo astratto dell'interfaccia Runnable quindi è necessario definirne il corpo
    //All'interno del metodo è presente un esempio dell'utilizzo della sintassi try/catch/finally
    public void run(){
        try{
            String inputLine;
            //salva il messaggio inserito dal client e lo trasmette a tutti i client
            while((inputLine = in.readLine()) != null){
                //per ogni elemento della lista clients mostra il messaggio appena inviato
                for(ClientHandler aClient : clients){
                    aClient.out.println(inputLine);
                }
            }
        }catch(IOException e){
            System.out.println("Errore I/O: " + e.getMessage());
        }finally{
            try{
                in.close();
                out.close();
                clientSocket.close();
            }catch (IOException e){
                e.getStackTrace();
            }
        }
    }
}