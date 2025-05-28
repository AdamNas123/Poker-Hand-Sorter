import java.util.*;

/**
 * Represents the player, their list of cards (hand) and number of wins
 */
public class Player {
    private int playerNumber;
    private List<Card> hand;
    private int handScore; //A total score of the hand required for tie-breaking cases
    private int winCount;
}
