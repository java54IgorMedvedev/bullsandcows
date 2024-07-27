package bullsandcows.implementations;

public record Move(long gameId, String clientSequence) {
    public Move {
        if (clientSequence.length() != 4 || !clientSequence.matches("\\d+")) {
            throw new IllegalArgumentException("Sequence must be 4 digits long.");
        }
    }
}
