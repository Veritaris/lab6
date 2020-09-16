package Client;

import java.io.IOException;

public class ClientMain {
    public static Client client;
    public static void main(String[] args) throws IOException {
        client = new Client("localhost", 1488);
        client.startInteractiveMode();
    }
}
