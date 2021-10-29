package Unit_3;

import java.util.Random;

public class _24_DrawCard {
    public static void main(String[] args) {
        String[] scale = {
                "Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
        String[] flower = {
                "Clubs", "Diamonds", "Hearts", "Spades"};
        int r1 = (int) (Math.random() * 13);
        int r2 = (int) (Math.random() * 4);
        System.out.println("The card you picked is " + scale[r1] + " of " + flower[r2]);
    }
}
