package Unit_11;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * 11.19
 */
public class BinPackingUsingFirstFit {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of objects: ");
        int n = sc.nextInt(), i;
        ArrayList<Integer> weights = new ArrayList<>();
        ArrayList<Integer> sizes = new ArrayList<>();
        System.out.print("Enter the weights of the objects: ");
        for (i = 0; i < n; i++) {
            weights.add(sc.nextInt());
        }
        i = 1;
        while (!weights.isEmpty()) {
            sizes.clear();
            int size = 0;
            for (Integer weight : weights) {
                if (size + weight <= 10) {
                    size += weight;
                    sizes.add(weight);
                }
            }
            System.out.print("Container " + i++ + " contains objects with weight");
            for (Integer weight : sizes) {
                System.out.print(" " + weight);
                weights.remove(weight);
            }
            System.out.println();
        }
    }
}

/*

Enter the number of objects: 6
Enter the weights of the objects: 7 5 2 3 5 8
Container 1 contains objects with weight 7 2
Container 2 contains objects with weight 5 3

Container 3 contains objects with weight 5
Container 4 contains objects with weight 8

 */