import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        GreetClient client = new GreetClient();
        client.startClient("localhost", 5000);
        String response = client.sendMessage("hello server");
        System.out.println(response);
        
    }
}
