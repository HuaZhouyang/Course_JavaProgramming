package Unit_3;

import java.util.Scanner;

public class _09_CheckISBN10 {
    public static void main(String[] args) {
        System.out.print("Enter the first 9 digits of an ISBN as integer: ");
        Scanner scanner = new Scanner(System.in);
        String _9d = scanner.nextLine();
        int sum = 0;
        for (int i = 0; i < _9d.length();) {
            sum += (_9d.charAt(i) - '0') * ++i;
        }
        sum %= 11;
        String d10 = sum == 10 ? "X" : String.valueOf(sum);
        System.out.println("The ISBN-10 number is " + _9d + d10);
    }
}

/*
Enter the first 9 digits of an ISBN as integer:
The ISBN-10 number is
 */