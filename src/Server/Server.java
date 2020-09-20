package Server;

import java.nio.channels.DatagramChannel;

import dependencies.CommandProcessor;
import dependencies.CommandObject;
import java.net.InetSocketAddress;
import java.net.DatagramSocket;
import java.net.SocketAddress;
import java.io.IOException;
import java.nio.file.Paths;

@SuppressWarnings({"InfiniteLoopStatement", "FieldCanBeLocal"})
public class Server {
//    private static final Logger logger = LogManager.getLogger("ConsoleAppender");
//    private static final Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);
    public static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Server.class.getName());

    public String host;
    public int port;

    private final DatagramChannel datagramChannel;
    private final SocketAddress serverAddress;
    private final DatagramSocket datagramSocket;

    private final Receiver messageReceiver;
    private final Sender messageSender;

    private CommandObject receivedCommandObject;
    private CommandObject commandObjectToSend;

    private final CommandProcessor commandProcessor = new CommandProcessor();

    public Server(String host, int port) throws IOException {
        System.setProperty("java.util.logging.SimpleFormatter.format", "[%1$tF %1$tT] [%4$s] %5$s %n");
        logger.info(String.format("Creating server at '%s:%s'...", host, port));

        this.host = host;
        this.port = port;

        this.datagramChannel = DatagramChannel.open();
        this.serverAddress = new InetSocketAddress(this.host, this.port);
        this.datagramSocket = this.datagramChannel.socket();
        this.datagramSocket.bind(serverAddress);

        this.commandProcessor.setCommandExecutor(Paths.get(".").toAbsolutePath().normalize().toString() + String.format("/%s", "users.json"));

        logger.info("Server created!");

        this.messageReceiver = new Receiver(this.datagramChannel);
        this.messageSender = new Sender(this.datagramChannel);
    }

    public void startListening() throws IOException {
        logger.info(String.format("Server at '%s:%s' started listening\n", this.host, this.port));

        while (true) {
            commandObjectToSend = null;
            receivedCommandObject = null;
            receivedCommandObject = this.messageReceiver.handleMessage();

            logger.info(String.format("Received packet: ip %s, message %s", this.messageReceiver.getClientAddress(), receivedCommandObject.toString()));

            commandObjectToSend = commandProcessor.processCommand(receivedCommandObject);
            logger.info(String.format("Sending response to %s: %s", this.messageReceiver.getClientAddress(), commandObjectToSend));
            this.messageSender.sendMessage(commandObjectToSend, this.messageReceiver.getClientAddress());
        }
    }
}
