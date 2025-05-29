import java.util.*;

/**
 * Represents the player, their list of cards (hand) and number of wins
 */
public class Player {
    private final int playerNumber;
    private List<Card> hand;
    private HashMap<Integer, Integer> ranks;
    private HashMap<Character, Integer> suits;
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

        //Sort hand into both hashmap of ranks (card values) and then suits
        //Ranks Hashmap - rank:count pair
        this.ranks = new HashMap<Integer, Integer>();

        //Suits Hashmap - suit: count pair
        this.suits = new HashMap<Character, Integer>();

        //Sort hand into the ranks and suits hashmaps
        for (Card card : this.hand) {
            this.ranks.merge(card.getRankValue(), 1, Integer::sum);
            this.suits.merge(card.getSuit(), 1, Integer::sum);
        }
        System.out.println("Player " + playerNumber + " Ranks: " + ranks.toString());
        System.out.println("Player " + playerNumber + " Suits: " + suits.toString());
    }

    public int rankHand() {
        //Check for hands in reverse order of rank. I.e. Royal Flush first
        List<Integer> ranksList = new ArrayList<>(ranks.keySet());
        Collections.sort(ranksList);

        //Call the isStraight function to check if the hand is a straight
        boolean isStraight = isStraight(ranksList);

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
        return handRank.getHandRankValue();
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

    private boolean isStraight(List<Integer> ranksList) {
        int consecutiveCount = 0;
        for (int i = 0; i < ranksList.size() - 1; ++i) {
            if (ranksList.get(i + 1) - ranksList.get(i) != 1) {
                break;
            }
            else {
                ++consecutiveCount;
            }
        }
        return consecutiveCount == 4;
    }

    public int scoreStraightHands() {
        return Collections.max(ranks.keySet());
    }

        /*
        Cases for same ranked hand:
            1)NO ROYAL FLUSH since not considering suits
            2)Straight Flush + Straight -> Look at highest value in straight
                And determine winner from there - Nothing else since 5 cards
            3) Four of a kind -> Look at value in four of a kind and find highest
               - If same, look at remaining card and find highest
            4) Three of a kind -> Same as 3)
               - If same, look at next highest card and then final card
            5) Two Pair -> Look at highest pair value - then second pair
                - If both pairs -> Look at final remaining card
            6) Pair -> Same as two pair but one pair
                - Compare next 3 cards in descending order
            7) Full House -> Higher 3 of a kind, then higher pair
            8) Flush, High card -> Just loop through cards until highest value found
         */

    public List<Integer> getTieBreakerValues() {
        // Get ranks and find the highest values (first), then add to list
        List<Integer> sortedCardValues = new ArrayList<>();
        int currentRankCount = Collections.max(ranks.values());
        for (Map.Entry<Integer, Integer> rank : ranks.entrySet()) {
            if (rank.getValue() == currentRankCount) {

            }
        }

    }
}
