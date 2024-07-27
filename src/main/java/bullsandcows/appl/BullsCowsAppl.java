package bullsandcows.appl;

import telran.view.*;
import bullsandcows.implementations.*;

import java.util.List;

public class BullsCowsAppl {

    public static void main(String[] args) {
        BullsCowsService bullsCowsService = new BullsCowsMapImpl();
        List<Item> items = BullsCowsApplItems.getItems(bullsCowsService);
        Menu menu = new Menu("Bulls and Cows Application", items.toArray(Item[]::new));
        menu.perform(new SystemInputOutput());
    }
}
