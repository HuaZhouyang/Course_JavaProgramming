package Unit_3;

import java.util.Scanner;

public class _15_Lottery {
    public static void main(String[] args) {
        // Generate a lottery number
        int lottery = (int) (Math.random() * 1000);
//        int lottery = 123;
        // Prompt the user to enter a guess
        Scanner input = new Scanner(System.in);
        System.out.print("Enter your lottery pick (three digits): ");
        int guess = input.nextInt(), money = 10000;
        System.out.println("The lottery number is " + lottery);
        // check the guess
        boolean[] lotteries = new boolean[10];
        boolean[] guesses = new boolean[10];
        while (lottery != 0) {
            int l = lottery % 10, g = guess % 10;
            if (l != g) money = 3000;
            lotteries[l] = true;
            guesses[g] = true;
            lottery /= 10;
            guess /= 10;
        }
        boolean flag = false;
        for (int i = 0; i < 10; i++) {
            if (lotteries[i] && lotteries[i] == guesses[i])
                flag = true;
            else if (lotteries[i] != guesses[i])
                money = 1000;
        }
        if (flag) {
            switch (money) {
                case 10000:
                    System.out.println("Exact match: you win $10,000");
                    break;
                case 3000:
                    System.out.println("Match all digits: you win $3,000");
                    break;
                case 1000:
                    System.out.println("Match one digit: you win $1,000");
            }
        } else {
            System.out.println("Sorry, no match");
        }
    }
}

/*
Exact match: you win $10,000
Match all digits: you win $3,000
Match one digit: you win $1,000
Sorry, no match
 */