package bullsandcows.appl;

import telran.view.Item;
import bullsandcows.implementations.*;

import java.util.ArrayList;
import java.util.List;

public class BullsCowsApplItems {

    public static List<Item> getItems(BullsCowsService bullsCowsService) {
        List<Item> items = new ArrayList<>();
        items.add(Item.of("Start new game", io -> {
            Long gameId = bullsCowsService.createNewGame();
            io.writeLine("New game started with ID: " + gameId);
        }));
        items.add(Item.of("Make a move", io -> {
            long gameId = io.readLong("Enter game ID: ", "Invalid game ID. Please try again.");
            String guess = io.readString("Enter 4 unique digits (0-9): ");
            Move move = new Move(gameId, guess);
            List<MoveResult> results = bullsCowsService.getResults(gameId, move);
            results.forEach(io::writeLine);
            if (bullsCowsService.isGameOver(gameId)) {
                io.writeLine("Game is over!");
            }
        }));
        items.add(Item.of("Exit", io -> System.exit(0)));
        return items;
    }
}
