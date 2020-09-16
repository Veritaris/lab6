package Server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.sql.Connection;

@SuppressWarnings("InfiniteLoopStatement")
public class Server {
    public String host;
    public int port;

    private SocketAddress serverAddress, clientAddress;
    private DatagramChannel datagramChannel;
    private Connection connection;
    private byte[] message;
    private ByteBuffer buffer = ByteBuffer.allocate(32768);

    public Server(String host, int port) throws IOException {
        System.out.printf("Creating server at '%s:%s'\n", host, port);
        this.host = host;
        this.port = port;
        this.datagramChannel = DatagramChannel.open();
        this.serverAddress = new InetSocketAddress(host, port);
        this.datagramChannel.bind(this.serverAddress);
        System.out.print("Server created");
    }

    public void startListening() throws IOException {
        System.out.printf("Server at '%s:%s' started listening\n", this.host, this.port);
        while (true) {
            buffer.clear();
            clientAddress = this.datagramChannel.receive(buffer);
            buffer.flip();
            message = new byte[buffer.limit()];
            buffer.get(message, 0, buffer.limit());
            buffer.clear();
            System.out.println(new String(message));
            sendMessage(new String(message), clientAddress);
        }
    }

    public void connectToClient() {

    }

    public void sendMessage(String message, SocketAddress address) throws IOException {
        buffer.clear();
        buffer = ByteBuffer.wrap(message.getBytes());
        this.datagramChannel.send(buffer, address);
    }
}
