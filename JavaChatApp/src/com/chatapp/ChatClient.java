package com.chatapp;

import java.net.*;
import java.util.function.Consumer;
import java.io.*;

public class ChatClient {
    private Socket socket = null;
    private PrintWriter out = null;
    private BufferedReader in = null;
    private Consumer<String> onMessageReceived;

    public ChatClient(String serverAddress, int serverPort, Consumer<String> onMessageReceived) throws IOException {
       this.socket = new Socket(serverAddress,serverPort);
       this.out = new PrintWriter(socket.getOutputStream(), true);
       this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
       this.onMessageReceived = onMessageReceived;
    }
    
    public void sendMessage(String msg) {
        out.println(msg);
    }

    public void startClient(){
        new Thread(()->{
            try{
                String line;
                while((line = in.readLine()) != null){
                    onMessageReceived.accept(line);

                }
            }catch(IOException e){
                e.printStackTrace();
            }
        }).start();;
    }
}
