package bullsandcows.client;

import telran.view.*;

import java.util.List;
import bullsandcows.implementations.BullsCowsService;
import bullsandcows.appl.BullsCowsApplItems;

public class BullsCowsClientAppl {

    private static final String HOST = "localhost";
    private static final int PORT = 5000;

    public static void main(String[] args) {
        BullsCowsService bullsCowsService = new BullsCowsProxy(HOST, PORT);
        List<Item> items = BullsCowsApplItems.getItems(bullsCowsService);
        Menu menu = new Menu("Bulls and Cows Client Application", items.toArray(Item[]::new));
        menu.perform(new SystemInputOutput());
    }
}
