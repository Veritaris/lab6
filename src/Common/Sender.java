package Common;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.util.logging.Logger;

@SuppressWarnings("FieldCanBeLocal")
public class Sender {
    private static final Logger logger = Logger.getLogger(Sender.class.getName());
    private ByteArrayOutputStream byteArrayOutputStream;
    private ObjectOutputStream objectOutputStream;
    private final Connection connection;
    private DatagramPacket packet;
    private byte[] buff;

    public Sender(Connection connection) {
        this.connection = connection;
    }

    public void sendPacket(String message) {
        try {
            byteArrayOutputStream = new ByteArrayOutputStream(32768);
            objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            buff = message.getBytes();
            packet = new DatagramPacket(buff, buff.length, this.connection.getInetAddress(), this.connection.getPort());
        } catch (IOException e) {
            logger.severe("Input / output error");
        }
    }

    public Connection getConnection() {
        return this.connection;
    }
}
