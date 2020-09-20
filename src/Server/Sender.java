package Server;

import dependencies.CommandObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

@SuppressWarnings("FieldCanBeLocal")
public class Sender {
    private final DatagramChannel datagramChannel;
    private ByteArrayOutputStream baos;
    private ObjectOutputStream oos;

    public Sender(DatagramChannel datagramChannel) {
        this.datagramChannel = datagramChannel;
    }

    public void sendMessage(CommandObject commandObject, SocketAddress address) throws IOException {
        baos = new ByteArrayOutputStream();
        oos = new ObjectOutputStream(baos);
        oos.writeObject(commandObject);
        oos.flush();
        byte[] payload = baos.toByteArray();
        baos.flush();
        try {
            this.datagramChannel.send(ByteBuffer.wrap(payload), address);
        } catch (IOException e) {
            System.out.printf("Something wrong with given message: %s\n", commandObject.toString());
        }
    }
}
