package Unit_5;

public class _32_Lottery {
    public static void main(String[] args) {
        int lottery_1 = (int)(Math.random() * 100), lottery_2;
        while ((lottery_2 = (int)(Math.random() * 100)) == lottery_1)
            ;
        System.out.println("lottery_1: " + lottery_1);
        System.out.println("lottery_2: " + lottery_2);
    }
}
