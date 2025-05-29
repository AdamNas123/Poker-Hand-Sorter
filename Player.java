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
    private int winCount = 0;

    //Constructor that sets the player number
    public Player(int playerNumber) {
        this.playerNumber = playerNumber;
    }

    //Overridden toString function to display player fields
    @Override
    public String toString() {
        return "Player {" +
                "playerNumber='" + playerNumber + '\'' +
                ", hand='" + hand + '\'' +
                ", handRank='" + handRank + '\'' +
                ", winCount='" + winCount + '\'' +
                '}'
                ;
    }

    /**
     * Function that increments the number of wins for a player
     */
    public void incrementWinCount() {
        ++winCount;
    }

    /**
     * Function that returns the number of wins for a player
     */
    public int getWinCount() {
        return winCount;
    }

    /**
     * @param hand List of cards for the player in the current round
     * Sets the ranks and suits hashmaps - Which record the card rank and card suit and frequency
     */
    public void createHand(List<String> hand) {
        //Create list of cards (Hand) for the player object
        this.hand = new ArrayList<Card>();
        for (String card : hand) {
            this.hand.add(new Card(card));
        }

        //Sort hand into both hashmap of ranks (card values) and then suits
        //Ranks Hashmap - rank:frequency pair
        this.ranks = new HashMap<Integer, Integer>();

        //Suits Hashmap - suit:frequency pair
        this.suits = new HashMap<Character, Integer>();

        //Sorts each card into the ranks and suits hashmaps
        for (Card card : this.hand) {
            //Checks if card exists in ranks and suits.
            //If it does not exist, it creates a new key:value pair with a value of 1
            //If it does exist, it sums the existing value with 1
            this.ranks.merge(card.getRankValue(), 1, Integer::sum);
            this.suits.merge(card.getSuit(), 1, Integer::sum);
        }
        System.out.println("Player " + playerNumber + " Ranks: " + ranks.toString());
        System.out.println("Player " + playerNumber + " Suits: " + suits.toString());
    }

    /**
     * @return numeric value of the handRank enum that is determined from the given player's hand
     * Checks for hands in reverse order of rank. I.e. Royal Flush first
     */
    public int rankHand() {
        //
        List<Integer> ranksList = new ArrayList<>(ranks.keySet());
        Collections.sort(ranksList);

        //Call the isStraight function to check if the hand is a straight
        boolean isStraight = isStraight(ranksList);

        //Check if hand has one suit with 5 values (flush) and if hand has an ace (Could be a royal flush)
        boolean isFlush = suits.containsValue(5); //5 is the total number of cards in a hand
        boolean hasAce = ranks.containsKey(14); //14 is the numerical value of the ace card

        //ROYAL FLUSH: Straight + flush + has the ace
        if (isStraight && isFlush && hasAce) {
            this.handRank = HandRank.ROYAL_FLUSH;
        }
        //STRAIGHT_FLUSH: Straight + flush
        else if (isStraight && isFlush) {
            this.handRank = HandRank.STRAIGHT_FLUSH;
        }
        //FOUR_OF_A_KIND: Four cards with same value/rank
        else if (ranks.containsValue(4)) {
            this.handRank = HandRank.FOUR_OF_A_KIND;
        }
        //FULL_HOUSE: Three of a kind + pair
        else if (ranks.containsValue(3) && ranks.containsValue(2)) {
            this.handRank = HandRank.FULL_HOUSE;
        }
        //FLUSH: 5 cards with same suit
        else if (isFlush)  {
            this.handRank = HandRank.FLUSH;
        }
        //STRAIGHT: 5 cards in consecutive order
        else if (isStraight)  {
            this.handRank = HandRank.STRAIGHT;
        }
        //THREE_OF_A_KIND
        else if (ranks.containsValue(3)) {
            this.handRank = HandRank.THREE_OF_A_KIND;
        }
        //Checks for amount of pairs and sets handRank accordingly. If no pairs, sets rank to just HIGH_CARD.
        else {
            int pairs = countPairs();
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

    /**
     * @return number of pairs in the hand (0, 1 or 2)
     */
    private int countPairs() {
        int pairs = 0;
        for (int rankCount : ranks.values()) {
            if (rankCount == 2) {
                ++pairs;
            }
        }
        return pairs;
    }

    /**
     * @return true if hand is straight, false if hand is not straight
     */
    private boolean isStraight(List<Integer> ranksList) {
        int consecutiveCount = 0;
        //Iterates over the list of card values (ranksList) and checks if the difference between each is 1 (consecutive)
        for (int i = 0; i < ranksList.size() - 1; ++i) {
            if (ranksList.get(i + 1) - ranksList.get(i) != 1) {
                break;
            }
            else {
                ++consecutiveCount;
            }
        }
        //Return true if there are 4 consecutive steps between card values (5 cards)
        return consecutiveCount == 4;
    }

    /**
     * @return highest card value in a straight hand
     */
    public int scoreStraightHands() {
        return Collections.max(ranks.keySet());
    }

    /**
     * @return List of card values in descending order, based on handRank, then count
     */
    public List<Integer> getTieBreakerValues() {
        List<Integer> sortedCardValues = new ArrayList<>();
        // Get the highest count of one card in any hand (Maximum expected value will be 4 - Since straight is not considered here)
        int currentRankCount = Collections.max(ranks.values());

        //Loop to add the card values in the hand in the highest hand rank, then in order of individual value
        while (currentRankCount > 0) {
            //Create a list to store the card values with the same count in descending order
            List<Integer> sameCountCards = new ArrayList<>();

            //Loop over the ranks hashmap and find all cards that have the same count and add them to the list
            for (Map.Entry<Integer, Integer> rank : ranks.entrySet()) {
                if (rank.getValue() == currentRankCount) {
                    sameCountCards.add(rank.getKey());
                }
            }
            //Sort the list of same count cards in descending order
            sameCountCards.sort(Collections.reverseOrder());

            //Append the small list to the larger sorted card values list
            sortedCardValues.addAll(sameCountCards);
            --currentRankCount;
        }
        return sortedCardValues;
    }

    /*
    Initial Planning Notes
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
}
