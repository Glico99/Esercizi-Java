import java.net.*;
import java.io.*;

public class GreetClient {
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    public void startClient(String address, int port) throws IOException{
        socket = new Socket(address,port);
        out = new PrintWriter(socket.getOutputStream(),true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public String sendMessage(String msg) throws IOException{
        out.println(msg);
        String resp = in.readLine();
        return resp;
    }

    public void stopClient() throws IOException{
        in.close();
        out.close();
        socket.close();
    }
}
