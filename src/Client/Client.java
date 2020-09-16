package Client;

import java.io.IOException;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.sql.Connection;
import java.util.Scanner;

public class Client {
    public String serverHost;
    public int serverPort;

    public DatagramSocket datagramSocket;
    public DatagramPacket datagramPacket;
    public SocketAddress serverAddress;
    public Connection connection;
    public DatagramChannel client;
    public String line;

    private final CommandProcessor commandProcessor = new CommandProcessor();
    private final Scanner consoleScanner = new Scanner(System.in);
    public ByteBuffer buffer = ByteBuffer.allocate(32768);

    public Client(String host, int port) throws IOException {
        System.out.printf("Creating client at '%s:%s'\n", host, port);
        this.serverHost = host;
        this.serverPort = port;
        this.serverAddress = new InetSocketAddress(this.serverHost, this.serverPort);;
        this.client = DatagramChannel.open();
        this.datagramSocket = new DatagramSocket();
        System.out.println("Client created");
    }

    public void connectToServer() {

    }

    public void sendMessage(String message) {
        byte[] payload = message.getBytes();
        try {
            datagramPacket = new DatagramPacket(payload, payload.length, InetAddress.getByName(this.serverHost), this.serverPort);
            this.datagramSocket.send(datagramPacket);
        } catch (IOException e) {
            System.out.printf("Something wrong with given message: %s\n", message);
        }
    }

    public void startInteractiveMode() {
        System.out.println("You have entered into interactive mode");

        while (true) {
            line = consoleScanner.nextLine();
            sendMessage(line);
        }
    }
}
