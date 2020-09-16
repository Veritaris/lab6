package Server;

import java.io.IOException;

public class ServerMain {
    public static Server server;

    public static void main(String[] args) throws IOException {
        server = new Server("localhost", 1488);
        server.startListening();
    }
}
