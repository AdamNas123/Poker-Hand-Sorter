/**
 * Represents the value and suit of each card
 */
public class Card {
    private char rank;
    private char suit;

    public Card(String card) {
        this.rank = card.charAt(0);
        this.suit = card.charAt(1);
    }

    @Override
    public String toString() {
        return "" + rank + suit;
    }
}