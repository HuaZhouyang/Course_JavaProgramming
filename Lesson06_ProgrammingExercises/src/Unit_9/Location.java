package Unit_9;

import java.util.Scanner;

/**
 * 9.13
 */
public class Location {
    public int row, column;
    public double maxValue;

    public Location(int row, int column, double maxValue) {
        this.row = row;
        this.column = column;
        this.maxValue = maxValue;
    }

    public Location() {}
}

class LocationTest {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of rows and columns in the array: ");
        int row = sc.nextInt(), column = sc.nextInt();
        System.out.println("Enter the array:");
        double[][] arr = new double[row][column];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                arr[i][j] = sc.nextDouble();
            }
        }
        Location res = locateLargest(arr);
        System.out.println("The location of the largest element is " + res.maxValue + " at (" + res.row + ", " + res.column + ")");
    }

    public static Location locateLargest(double[][] a) {
        Location loc = new Location();
        loc.row = 0;
        loc.column = 0;
        loc.maxValue = a[0][0];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                if (loc.maxValue < a[i][j]) {
                    loc.row = i;
                    loc.column = j;
                    loc.maxValue = a[i][j];
                }
            }
        }
        return loc;
    }
}
