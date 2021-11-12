package Unit_9;

import java.util.Date;
import java.util.Random;

/**
 * 9.6
 */
public class StopWatch {

    private long startTime;
    private long endTime;

    public StopWatch() {
        startTime = new Date().getTime();
    }

    public void start() {
        startTime = new Date().getTime();
    }

    public void stop() {
        endTime = new Date().getTime();
    }

    public long getElapsedTime() {
        return (endTime - startTime);
    }
}

class StopWatchTest {
    public static void main(String[] args) {
        int[] arr = new int[100000];
        Random r = new Random();
        for (int i = 0; i < 100000; i++) {
            arr[i] = r.nextInt(1000);
        }
        StopWatch sw = new StopWatch();
        selectSort(arr);
        sw.stop();
        System.out.println(sw.getElapsedTime());
    }
    private static void selectSort(int[] arr) {
        for(int i = 0; i < arr.length-1; i++) {
            int min = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[min]) {
                    min = j;
                }
            }
            if (min != i) {
                swap(arr, i, min);
            }
        }
    }
    private static void swap(int[] arr,int a,int b){
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}