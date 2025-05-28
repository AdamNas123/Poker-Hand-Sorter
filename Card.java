/**
 * Represents the value and suit of each card
 */
public class Card {
    private final char rank;
    private final char suit;

    public Card(String card) {
        this.rank = card.charAt(0);
        this.suit = card.charAt(1);
    }

    // Function to get the numeric associated value of cards with a value of 10 or more,
    // Otherwise, converts base number to int type
    public int getRankValue() {
        return switch(this.rank) {
            case 'T' -> 10;
            case 'J' -> 11;
            case 'Q' -> 12;
            case 'K' -> 13;
            case 'A' -> 14;
            default -> this.rank - '0';
        };
    }

    public char getSuit() {
        return this.suit;
    }

    @Override
    public String toString() {
        return "" + rank + suit;
    }
}