package Server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerConnection {
    private static final Logger logger = Logger.getLogger(ServerConnection.class.getName());

    private int PORT;
    private String HOST;
    private DatagramChannel channel;
    private SocketAddress socketAddress;
//    private SocketAddress clientAddress;

    private static ServerConnection connection;

    public ServerConnection(String host, int port) throws IOException {
        this.HOST = host;
        this.PORT = port;
        this.socketAddress = new InetSocketAddress(this.HOST, this.PORT);
        this.channel = DatagramChannel.open().bind(socketAddress);
    }

    public static ServerConnection getInstance(String host, int port) throws IOException{
        if (connection == null){
            connection = new ServerConnection(host, port);
        }
        return connection;
    }

    public DatagramChannel getChannel(){
        return this.channel;
    }

    public boolean getConnection(){
        SocketAddress clientAddress;
        byte[] b = new byte[] {3, 2, 1};
        try {
            ByteBuffer buf = ByteBuffer.wrap(b);
            channel.socket().setSoTimeout(5000);
            clientAddress = channel.receive(buf);

            logger.log(Level.INFO, "Connecting to " + clientAddress);

            channel.connect(clientAddress);
            buf.flip();
            channel.write(buf);

            logger.log(Level.INFO, "Completed");
            return true;
        } catch (IOException | NullPointerException exception) {
            logger.log(Level.SEVERE, "Input/output error", exception);
            return false;
        }
    }
}


