package Unit_5;

import java.util.Scanner;

public class _45_ComputeMeanAndStandardDeviation {
    /*
Enter 10 numbers: 1 2 3 4.5 5.6 6 7 8 9 10
The mean is 5.61
The standard deviation is 2.99794
     */
    public static void main(String[] args) {
        System.out.print("Enter 10 numbers: ");
        Scanner in = new Scanner(System.in);
        double[] arr = new double[10];
        for (int i = 0; i < 10; i++) {
            arr[i] = in.nextDouble();
        }
        System.out.println("The mean is " + getMean(arr));
        System.out.println("The standard deviation is " + getStandardDeviation(arr));

    }

    private static double getSum(double... nums) {
        double sum = 0;
        for (double num : nums) {
            sum += num;
        }
        return sum;
    }

    private static double getMean(double... nums) {
        return getSum(nums) / nums.length;
    }

    private static double getStandardDeviation(double... nums) {
        double sum = getSum(nums);
        double sum2 = 0;
        for (double num : nums) {
            sum2 += num * num;
        }
        return Math.sqrt((sum2 - sum * sum / nums.length) / (nums.length - 1));
    }
}
