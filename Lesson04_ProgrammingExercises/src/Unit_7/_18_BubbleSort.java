package Unit_7;

import java.util.Arrays;
import java.util.Scanner;

public class _18_BubbleSort {
    public static void main(String[] args) {
        System.out.println("Please enter 10 numbers: ");
        Scanner sc = new Scanner(System.in);
        double[] arr = new double[10];
        for (int i = 0; i < 10; i++) {
            arr[i] = sc.nextDouble();
        }
        bubbleSort(arr);
        System.out.println("Result: " + Arrays.toString(arr));
    }

    private static void bubbleSort(double[] a) {
        if (a.length <= 1) return;

        for (int i = 0; i < a.length; ++i) {
            // 提前退出冒泡循环的标志位
            boolean flag = false;
            for (int j = 0; j < a.length - i - 1; ++j) {
                if (a[j] > a[j+1]) { // 交换
                    double tmp = a[j];
                    a[j] = a[j+1];
                    a[j+1] = tmp;
                    flag = true;  // 表示有数据交换
                }
            }
            if (!flag) break;  // 没有数据交换，提前退出
        }
    }
}
