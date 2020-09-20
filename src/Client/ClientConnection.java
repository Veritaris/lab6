package Client;

import java.io.IOException;
import java.net.*;

@SuppressWarnings("FieldCanBeLocal")
public class ClientConnection {
    private final SocketAddress address;
    private final DatagramSocket socket;
    private DatagramPacket packetToSend;
    private DatagramPacket packetToReceive;

    public static ClientConnection connection;
    private byte[] b;

    private ClientConnection(String host, int port) throws SocketException {
        this.address = new InetSocketAddress(host, port);
        this.socket = new DatagramSocket();
    }

    public static ClientConnection getInstance(String host, int port) throws SocketException{
        if (connection == null){
            connection = new ClientConnection(host, port);
        }
        return connection;
    }

    public SocketAddress getAddress(){
        return this.address;
    }

    public DatagramSocket getSocket(){
        return this.socket;
    }

    public boolean connect(){
        byte[] b = new byte[]{1, 2, 3};
        DatagramPacket packetOut = new DatagramPacket(b, b.length, address);
        try{
            socket.send(packetOut);
            for (byte c : b){
                c=0;
            }
            DatagramPacket packetIn = new DatagramPacket(b, b.length);
            socket.setSoTimeout(20000);
            socket.receive(packetIn);
            socket.connect(address);
            return true;
        } catch (SocketTimeoutException e){
            System.out.println("!Server isn't responding!");
            return false;
        } catch (PortUnreachableException e){
            return false;
        } catch (IOException e){
            System.out.println("!Something wrong with collection!");
            return false;
        }
    }
//        boolean flag = false;
//        b = new byte[]{1, 2, 3};
//        packetToSend = new DatagramPacket(b, b.length, this.address);
//        try {
//            socket.send(packetToSend);
//            for (byte chr : b){
//                chr = 0;
//            }
//            packetToReceive = new DatagramPacket(b, b.length);
//            socket.setSoTimeout(5000);
//            socket.receive(packetToReceive);
//            socket.connect(address);
//            flag = true;
//        } catch (SocketTimeoutException e) {
//            System.out.println("Server not responding");
//        } catch (PortUnreachableException e) {
//            System.out.println("Cannot reach port");
//        } catch (IOException e){
//            System.out.println("Something wrong with collection");
//        }
//        return flag;
//    }
}
