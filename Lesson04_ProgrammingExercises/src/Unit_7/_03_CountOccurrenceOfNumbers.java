package Unit_7;

import java.util.*;

public class _03_CountOccurrenceOfNumbers {
    public static void main(String[] args) {
        Map<Integer, Integer> map = new TreeMap<>();
        Scanner sc = new Scanner(System.in);
        int n;
        while ((n = sc.nextInt()) != 0) {
            if (map.containsKey(n)) {
                map.put(n, map.get(n) + 1);
            } else {
                map.put(n, 1);
            }
        }
        Set<Integer> keys = map.keySet();
        for (Integer key : keys) {
            int t = map.get(key);
            System.out.println(key + " occurs " + t + (t == 1 ? " time" : " times"));
        }
    }
}
