package bullsandcows.implementations;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BullsCowsMapImpl implements BullsCowsService {

    private final Map<Long, Game> games = new HashMap<>();
    private long gameIdCounter = 0;

    @Override
    public Long createNewGame() {
        long gameId = ++gameIdCounter;
        games.put(gameId, new Game(gameId));
        return gameId;
    }

    @Override
    public List<MoveResult> getResults(long gameId, Move move) {
        Game game = games.get(gameId);
        if (game == null || game.isFinished()) {
            throw new IllegalStateException("Game not found or already finished.");
        }
        game.makeMove(move.clientSequence());
        return game.getMoveResults();
    }

    @Override
    public boolean isGameOver(long gameId) {
        Game game = games.get(gameId);
        return game != null && game.isFinished();
    }
}
