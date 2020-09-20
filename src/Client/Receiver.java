package Client;

import Server.CommandExecutor;
import dependencies.CommandObject;
import dependencies.CommandObjectCreator;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;

@SuppressWarnings("FieldCanBeLocal")
public class Receiver {
    private final byte[] message = new byte[8 * 1024];
    private final InetAddress serverAddress;
    private CommandObject receivedCommandObject;
    private ByteArrayInputStream bais;
    private ObjectInputStream ois;
    public DatagramPacket packet;
    public DatagramSocket socket;
    private final int serverPort;

    public Receiver(DatagramSocket datagramSocket, InetAddress serverAddress, int serverPort) {
        this.socket = datagramSocket;
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;
    }

    public CommandObject handleMessage() throws IOException, ClassNotFoundException {
        try {
            this.socket.setSoTimeout(15000);
            packet = new DatagramPacket(message, message.length, this.serverAddress, this.serverPort);
            this.socket.receive(packet);
            bais = new ByteArrayInputStream(message);
            ois = new ObjectInputStream(bais);
            receivedCommandObject = (CommandObject) ois.readObject();
            return receivedCommandObject;
        } catch (SocketTimeoutException ignored) {
            System.out.println("Server is not available now, please, try later");
            return CommandObjectCreator.createErrorObject("error", "Server not responding");
        }
    }
}
