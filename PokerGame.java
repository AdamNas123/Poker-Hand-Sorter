import java.util.*;
import java.util.Scanner;

public class PokerGame {

    public static void main(String[] args) {
    //First, read in hands and save them to player 1 and player 2 objects
        Scanner scanner = new Scanner(System.in);

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
            Player player1 = new Player(1, playerOneHand);
            Player player2 = new Player(2, playerTwoHand);

            System.out.println(player1.toString());
            System.out.println(player2.toString());
            break;
        }
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
}
