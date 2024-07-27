package bullsandcows.implementations;

public record MoveResult(String clientSequence, int bulls, int cows) {
    @Override
    public String toString() {
        return clientSequence + ": " + bulls + " Bulls, " + cows + " Cows";
    }
}
