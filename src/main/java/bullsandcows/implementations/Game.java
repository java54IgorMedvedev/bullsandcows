package bullsandcows.implementations;

import java.time.Instant;
import java.util.*;

public class Game {
    private final long id;
    private final String serverSequence;
    private boolean finished;
    private final Instant startTime;
    private Instant finishTime;
    private final List<MoveResult> moveResults;

    public Game(long id) {
        this.id = id;
        this.serverSequence = generateSecret();
        this.finished = false;
        this.startTime = Instant.now();
        this.moveResults = new ArrayList<>();
    }

    private String generateSecret() {
        List<Character> digits = new ArrayList<>();
        for (char c = '0'; c <= '9'; c++) {
            digits.add(c);
        }
        Collections.shuffle(digits);
        StringBuilder secretBuilder = new StringBuilder(4);
        for (int i = 0; i < 4; i++) {
            secretBuilder.append(digits.get(i));
        }
        return secretBuilder.toString();
    }

    public MoveResult makeMove(String guess) {
        int bulls = 0;
        int cows = 0;

        for (int i = 0; i < 4; i++) {
            char guessDigit = guess.charAt(i);
            char secretDigit = serverSequence.charAt(i);

            if (guessDigit == secretDigit) {
                bulls++;
            } else if (serverSequence.indexOf(guessDigit) != -1) {
                cows++;
            }
        }

        MoveResult result = new MoveResult(guess, bulls, cows);
        moveResults.add(result);

        if (bulls == 4) {
            finished = true;
            finishTime = Instant.now();
        }

        return result;
    }

    public boolean isFinished() {
        return finished;
    }

    public long getId() {
        return id;
    }

    public List<MoveResult> getMoveResults() {
        return moveResults;
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", serverSequence='" + serverSequence + '\'' +
                ", finished=" + finished +
                ", startTime=" + startTime +
                ", finishTime=" + finishTime +
                ", moveResults=" + moveResults +
                '}';
    }
}
