package dependencies;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.logging.Logger;

public class Connection {
    private static final Logger logger = Logger.getLogger(Connection.class.getName());
    private final InetSocketAddress address;
    private final String host;
    private final int port;

    public Connection(String host, int port) {
        this.host = host;
        this.port = port;
        this.address = new InetSocketAddress(this.host, this.port);
    }

    public String getHost() {
        return this.host;
    }

    public int getPort() {
        return this.port;
    }

    public InetSocketAddress getAddress() {
        return this.address;
    }

    public InetAddress getInetAddress() {
        try {
            return InetAddress.getByName(this.host);
        } catch (UnknownHostException e) {
            logger.severe(String.format("Unknown host: %s", this.host));
            return null;
        }
    }
}
