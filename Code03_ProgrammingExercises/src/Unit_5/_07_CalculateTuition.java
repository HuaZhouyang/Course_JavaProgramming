package Unit_5;

public class _07_CalculateTuition {
    public static void main(String[] args) {
        double tuition = 10_000;
        for (int i = 0; i < 10; i++) {
            tuition *= 1.05;
        }
        System.out.println("Tuition after 10 tears: " + tuition);
        double sum = 0;
        for (int i = 0; i < 4; i++) {
            sum += tuition;
            tuition += tuition * 0.05;
        }
        System.out.println("Sum of tuition in 4 years: " + sum);
    }
}
