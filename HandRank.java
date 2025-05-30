/**
 * An enum that contains each possible hand rank, with an associated numerical value
 */
public enum HandRank {
    HIGH_CARD(1),
    PAIR(2),
    TWO_PAIRS(3),
    THREE_OF_A_KIND(4),
    STRAIGHT(5),
    FLUSH(6),
    FULL_HOUSE(7),
    FOUR_OF_A_KIND(8),
    STRAIGHT_FLUSH(9),
    ROYAL_FLUSH(10);

    //Define a numerical value associated with each HandRank
    private final int handRankValue;

    //Constructor that initialises each Enum with its associated numerical value
    HandRank(int handRankValue) {
        this.handRankValue = handRankValue;
    }

    public int getHandRankValue() {
        return handRankValue;
    }
}
