package Unit_5;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class _37_DecimalToBinary {
    public static void main(String[] args) {
        Deque<Integer> s = new LinkedList<>();
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        while (n != 0) {
            s.push(n % 2);
            n /= 2;
        }
        int res = 0;
        while (!s.isEmpty()) {
            res = res * 10 + s.pop();
        }
        System.out.println(res);
    }
}
