import java.util.*;
import java.util.Scanner;

/**
 * Represents the main class of the program, reading in the poker hands from input, determining winners and displaying
 * the output.
 */
public class PokerGame {

    public static void main(String[] args) {
        //First, define a scanner to read in input from the terminal and initialise the 2 players
        Scanner scanner = new Scanner(System.in);
        Player player1 = new Player(1);
        Player player2 = new Player(2);

        //Then, read in hands line by line and save them to player 1 and player 2 objects
        while (scanner.hasNextLine()) {
            //Read in the next line from the text file and split the line based on the whitespaces
            String line = scanner.nextLine();
            String[] hands = line.split(" ");

            //Loop over the line, and add the first 5 cards to player 1's hand, and the next 5 to player 2's hand
            List<Card> playerOneHand = new ArrayList<>();
            List<Card> playerTwoHand = new ArrayList<>();
            for (int i = 0; i < hands.length; ++i) {
                //Initialise each string as a new card, with a rank and suit
                Card card = new Card(hands[i]);
                if (i < 5) {
                    playerOneHand.add(card);
                }
                else {
                    playerTwoHand.add(card);
                }
            }

            //Create a new hand for each player, for each round
            player1.createHand(playerOneHand);
            player2.createHand(playerTwoHand);
            System.out.println(player1.toString());
            System.out.println(player2.toString());

            //Get the rank of each player's hand and add a win to the player that has a higher ranked hand
            int player1Hand = player1.rankHand();
            int player2Hand = player2.rankHand();
            if (player1Hand > player2Hand) {
                player1.incrementWinCount();
            }
            else if (player1Hand < player2Hand) {
                player2.incrementWinCount();
            }
            //If both hands are tied, score each hand based on the highest card values within the hand
            //Add a win to the player that has the higher valued hand
            else {
                //Check highest values in hands (Hand ranks will be the same in this else statement)
                int winner = scoreHand(player1, player2, player1Hand);
                if (winner == 1) {
                    player1.incrementWinCount();
                }
                else if (winner == 2) {
                    player2.incrementWinCount();
                }
            }
        }
        //After reading all hands, print the number of wins per player
        System.out.println("Player 1: " + player1.getWinCount());
        System.out.println("Player 2: " + player2.getWinCount());
    }

    /**
     * @param player1 player1 object
     * @param player2 player2 object
     * @param handRank numerical value of the associated handRank enum
     * @return 1 if player 1 has a higher value hand, 2 if player 2 has a higher value hand
     */
    public static int scoreHand(Player player1, Player player2, int handRank) {
        //Initialise the winner variable
        int winner = 0;

        //Checks for the highest card in a straight or straight flush hand (Since this is made up of all 5)
        if (handRank == 9 || handRank == 5) {
            if (player1.scoreStraightHands() > player2.scoreStraightHands()) {
                winner = 1;
            }
            else {
                winner = 2;
            }
        }

        //For all other cases -> Get card values ordered in descending order based on count and value
        // Then iterate through to find the winner by comparing each value in the list
        else {
            //Call functions to get both hands sorted in descending order based on count and value
            List<Integer> player1SortedHand = player1.getTieBreakerValues();
            List<Integer> player2SortedHand = player2.getTieBreakerValues();

            //Loop over the lists and determine winner by getting the first card that is higher
            for (int i = 0; i < player1SortedHand.size(); ++i) {
                //If player has a higher card, set their number as the winner and break out of the loop
                if (player1SortedHand.get(i) > player2SortedHand.get(i)) {
                    winner = 1;
                    break;
                }
                else if (player1SortedHand.get(i) < player2SortedHand.get(i)){
                    winner = 2;
                    break;
                }
            }
        }
        return winner;
    }

    /*
    Initial Planning Notes:
    There are some base cases that each hand can fall in:
     * 1) High Card (1)
     * 2) Same Values:
     *      - Pair (2)
     *      - Two Pairs (3)
     *      - 3 of a kind (4)
     *      - full house (7)
     *      - four of a kind (8)
     *  3) Same Suit:
     *      - Flush (6)
     *      - Straight Flush (9) (Combo of 3 and 4 cases)
     *      - Royal Flush (10) (Combo of 3, 4 with specific cards)
     *  4) Order:
     *      - Straight (5)
     *      - Straight Flush (9) (Combo of 3 and 4 cases)
     *      ~ Maybe Royal Flush (Combo of 3, 4 with specific cards)
     */
}
