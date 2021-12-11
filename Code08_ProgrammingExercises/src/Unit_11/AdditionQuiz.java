package Unit_11;

import java.util.Scanner;
import java.util.TreeSet;

/**
 * 11.16
 */
public class AdditionQuiz {
    public static void main(String[] args) {
        int number1 = (int)(Math.random() * 10);
        int number2 = (int)(Math.random() * 10);

        // Create a Scanner
        Scanner input = new Scanner(System.in);

        System.out.print("What is " + number1 + " + " + number2 + "? ");
        TreeSet<Integer> set = new TreeSet<>();
        int answer = input.nextInt();
        set.add(answer);

        while (number1 + number2 != answer)  {
            System.out.print("Wrong answer. Try again. What is "
                    + number1 + " + " + number2 + "? ");
            answer = input.nextInt();
            if (set.contains(answer)) {
                System.out.println("You already entered " + answer);
            }
        }

        System.out.println("You got it!");
    }
}

