package Unit_7;

import java.util.Scanner;
import java.util.StringJoiner;
import java.util.TreeSet;

public class _05_PrintDistinctNumbers {
    public static void main(String[] args) {
        TreeSet<Integer> set = new TreeSet<>();
        System.out.print("Enter 10 numbers: ");
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < 10; i++) {
            set.add(sc.nextInt());
        }
        System.out.println("The number of distinct numbers is " + set.size());
        System.out.print("The distinct numbers are: ");
        StringJoiner sj = new StringJoiner(" ");
        for (Integer n : set) {
            sj.add(n.toString());
        }
        System.out.println(sj);
    }
}
