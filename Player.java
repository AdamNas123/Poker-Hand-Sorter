import java.util.*;

/**
 * Represents the player, their list of cards (hand) and number of wins
 */
public class Player {
    private final int playerNumber;
    private List<Card> hand;
    private int handScore = 0; //A total score of the hand required for tie-breaking cases
    private int winCount = 0;

    public Player(int playerNumber) {
        this.playerNumber = playerNumber;
    }

    //Overridden to string function to display player fields
    @Override
    public String toString() {
        return "Player {" +
                "playerNumber='" + playerNumber + '\'' +
                ", hand='" + hand + '\'' +
                ", handScore='" + handScore + '\'' +
                ", winCount='" + winCount + '\'' +
                '}'
                ;
    }

    //Function to increment win count if their hand wins
    public void incrementWinCount() {
        ++winCount;
    }

    //Function to return the win count for the final output
    public int getWinCount() {
        return winCount;
    }

    public void createHand(List<Card> hand) {
        this.hand = new ArrayList<>(hand);
    }

    public int rankHand() {
        //Start by sorting hand into both hashmap of ranks and then suits
        HashMap<Integer, Integer> ranks = new HashMap<Integer, Integer>();
        HashMap<Character, Integer> suits = new HashMap<Character, Integer>();

        //Sort hand into the ranks and suits hashmaps
        for (Card card : this.hand) {
            ranks.merge(card.getRankValue(), 1, Integer::sum);
            suits.merge(card.getSuit(), 1, Integer::sum);
        }
        System.out.println("Player " + playerNumber + " Ranks: " + ranks.toString());
        System.out.println("Player " + playerNumber + " Suits: " + suits.toString());

        //For ranks -> Determine if hand is 4 of a kind, full house, 3 of a kind, 2 pair or pair
        //For suits -> Determine if hand is royal flush or flush
        // Straight -> Straight or straight flush
        return 0;
    }
}
