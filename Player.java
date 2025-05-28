import java.util.*;

/**
 * Represents the player, their list of cards (hand) and number of wins
 */
public class Player {
    private int playerNumber;
    private List<Card> hand;
    private int handScore = 0; //A total score of the hand required for tie-breaking cases
    private int winCount = 0;

    public Player(int playerNumber, List<Card> hand) {
        this.playerNumber = playerNumber;
        this.hand = new ArrayList<>(hand);
    }

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
}
