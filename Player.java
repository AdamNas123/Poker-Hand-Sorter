import java.util.*;

/**
 * Represents the player, their list of cards (hand) and number of wins
 */
public class Player {
    private final int playerNumber;
    private List<Card> hand;
    private HandRank handRank;
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
                ", handRank='" + handRank + '\'' +
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

    public int scoreHand() {
        //Start by sorting hand into both hashmap of ranks (card values) and then suits

        //Ranks Hashmap - rank:count pair
        HashMap<Integer, Integer> ranks = new HashMap<Integer, Integer>();

        //Suits Hashmap - suit: count pair
        HashMap<Character, Integer> suits = new HashMap<Character, Integer>();

        //Sort hand into the ranks and suits hashmaps
        for (Card card : this.hand) {
            ranks.merge(card.getRankValue(), 1, Integer::sum);
            suits.merge(card.getSuit(), 1, Integer::sum);
        }
        System.out.println("Player " + playerNumber + " Ranks: " + ranks.toString());
        System.out.println("Player " + playerNumber + " Suits: " + suits.toString());

        //Check for hands in reverse order of rank. I.e. Royal Flush first
        Object[] ranksArray = ranks.keySet().toArray();

        //Call the isStraight function to check if the hand is a straight
        boolean isStraight = isStraight(ranksArray);

        //Check if suits has one suit with 5 values (flush) and if ranks has an ace key (Could be a royal flush)
        boolean isFlush = suits.containsValue(5);
        boolean isRoyal = ranks.containsKey(14);

        //ROYAL FLUSH: Straight + flush + has the ace
        if (isStraight && isFlush && isRoyal) {
            this.handRank = HandRank.ROYAL_FLUSH;
        }
        //STRAIGHT FLUSH: Straight + flush
        else if (isStraight && isFlush) {
            this.handRank = HandRank.STRAIGHT_FLUSH;
        }
        else if (ranks.containsValue(4)) {
            this.handRank = HandRank.FOUR_OF_A_KIND;
        }
        else if (ranks.containsValue(3) && ranks.containsValue(2)) {
            this.handRank = HandRank.FULL_HOUSE;
        }
        else if (isFlush)  {
            this.handRank = HandRank.FLUSH;
        }
        else if (isStraight)  {
            this.handRank = HandRank.STRAIGHT;
        }
        else if (ranks.containsValue(3)) {
            this.handRank = HandRank.THREE_OF_A_KIND;
        }
        else {
            int pairs = countPairs(ranks);
            if (pairs == 2) {
                this.handRank = HandRank.TWO_PAIRS;
            }
            else if (pairs == 1) {
                this.handRank = HandRank.PAIR;
            }
            else {
                this.handRank = HandRank.HIGH_CARD;
            }
        }
        System.out.println("Player " + playerNumber + " HandRank: " + handRank.toString());
        return 0;
    }

    private int countPairs(HashMap<Integer, Integer> ranks) {
        int pairs = 0;
        for (int rankCount : ranks.values()) {
            if (rankCount == 2) {
                ++pairs;
            }
        }
        return pairs;
    }

    private boolean isStraight(Object[] ranksArray) {
        boolean isStraight = true;
        for (int i = 0; i < ranksArray.length - 1; ++i) {
            System.out.println("Converted int: " + (int)ranksArray[i+1]);
            if (((int) ranksArray[i + 1]) - (int)ranksArray[i] != 1) {
                isStraight = false;
                break;
            }
        }
        return isStraight;
    }
}
