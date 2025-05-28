import java.util.*;
import java.util.Scanner;

public class PokerGame {

    public static void main(String[] args) {
        //First, define a scanner to read in input from the terminal and initialise the 2 players
        Scanner scanner = new Scanner(System.in);
        Player player1 = new Player(1);
        Player player2 = new Player(2);

        //Then, read in hands line by line and save them to player 1 and player 2 objects
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] hands = line.split(" ");
            List<Card> playerOneHand = new ArrayList<>(), playerTwoHand = new ArrayList<>();
            for (int i = 0; i < hands.length; ++i) {
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

            //Rank each player's hand, find the higher score, and increment that player's wins
            int player1Score = player1.scoreHand();
            int player2Score = player2.scoreHand();
            if (player1Score > player2Score) {
                player1.incrementWinCount();
            }
            else {
                player2.incrementWinCount();
            }
//            break;

            //Then, loop through each player's hands
            //Because of tied ranking cases, will need to consider the full hand
            //and give some sort of number ranking per hand

            /*There are some base cases that each hand can fall in:
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
        System.out.println("Player 1: " + player1.getWinCount());
        System.out.println("Player 2: " + player2.getWinCount());
    }
}
