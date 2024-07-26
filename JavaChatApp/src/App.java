import javax.swing.SwingUtilities;

import com.chatapp.ChatClientGUI;

public class App {
    public static void main(String[] args) throws Exception {
        SwingUtilities.invokeLater(() -> {
            new ChatClientGUI().setVisible(true);
        });
    }
}
