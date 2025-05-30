/**
 * Represents the value and suit of each card
 */
public class Card {
    private final char rank;
    private final char suit;

    //Constructor that takes the 2 parts of the card string and splits into a rank and suit
    public Card(String card) {
        if (card.length() != 2) {
            System.err.println("Invalid input: Card " + card + " must have exactly 2 characters");
            System.exit(1);
        }
        this.rank = card.charAt(0);
        this.suit = card.charAt(1);
        if ("23456789TJQKA".indexOf(rank) == -1 ||"DHSC".indexOf(suit) == -1) {
            System.err.println("Invalid input: Card " + card + " is not in expected format");
            System.exit(1);
        }
    }

    // Function to get the numeric value of cards with a value of 10 or more,
    // Otherwise, converts char to int type
    public int getRankValue() {
        return switch(rank) {
            case 'T' -> 10;
            case 'J' -> 11;
            case 'Q' -> 12;
            case 'K' -> 13;
            case 'A' -> 14;
            default -> rank - '0';
        };
    }

    public char getSuit() {
        return suit;
    }

    @Override
    public String toString() {
        return "" + rank + suit;
    }
}