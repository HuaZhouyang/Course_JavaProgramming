package Unit_5;

import java.util.Scanner;

public class _22_LoanAmortizationSchedule {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Loan Amount: ");
        double loanAmount = input.nextDouble();
//        loanAmount = 10000;
        System.out.print("Number of Years: ");
        int numberOfYears = input.nextInt();
//        numberOfYears = 1;
        System.out.print("Annual Interest Rate: ");
        double annualInterestRate = input.nextInt();
//        annualInterestRate = 7;
        double monthlyInterestRate = annualInterestRate / 1200;
        double monthlyPayment = loanAmount * monthlyInterestRate /
                (1 - 1 / Math.pow(1 + monthlyInterestRate, numberOfYears * 12));
        double totalPayment = monthlyPayment * numberOfYears * 12;
        System.out.println("Monthly Payment: " + monthlyPayment);
        System.out.println("Total Payment: " + totalPayment);

        double balance = loanAmount;
        System.out.println("Payment#\t\tInterest\t\tPrincipal\t\tBalance");
        for (int i = 1; i <= 12; i++) {
            double interest = balance * monthlyInterestRate;
            double principal = monthlyPayment - interest;
            balance -= principal;
            System.out.printf("%d\t\t\t\t%.2f\t\t\t%.2f\t\t\t%.2f\n", i, interest, principal, balance);
        }
    }
}
