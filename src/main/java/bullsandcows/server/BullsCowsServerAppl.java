package bullsandcows.server;

import telran.net.*;
import bullsandcows.implementations.*;

public class BullsCowsServerAppl {

    private static final int PORT = 5000;

    public static void main(String[] args) {
        Protocol protocol = new BullsCowsServerHandler(new BullsCowsMapImpl());
        TcpServer server = new TcpServer(protocol, PORT);
        server.run();
    }
}
