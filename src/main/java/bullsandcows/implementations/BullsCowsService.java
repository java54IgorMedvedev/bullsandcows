package bullsandcows.implementations;

import java.util.List;

public interface BullsCowsService {
    Long createNewGame();
    List<MoveResult> getResults(long gameId, Move move);
    boolean isGameOver(long gameId);
}
