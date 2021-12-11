package Unit_11;

import java.util.ArrayList;
import java.util.Date;

/**
 * 11.8
 */
public class NewAccount extends Account {
    public static void main(String[] args) {
        NewAccount.setAnnualInterestRate(1.5);
        NewAccount georgeAccount = new NewAccount(1122, 1000, "George");
        georgeAccount.deposit(30);
        georgeAccount.deposit(40);
        georgeAccount.deposit(50);
        georgeAccount.withdraw(5);
        georgeAccount.withdraw(4);
        georgeAccount.withdraw(2);
        georgeAccount.printAccountSummary();

    }

    private String name;
    private ArrayList<Transaction> transactions = new ArrayList<>();

    public NewAccount(int id, double balance, String name) {
        super(id, balance);
        this.name = name;
    }

    @Override
    public void withdraw(double amount) {
        super.withdraw(amount);
        transactions.add(new Transaction('W', amount, getBalance(), "从账户提取"+amount+"元"));
    }

    @Override
    public void deposit(double amount) {
        super.deposit(amount);
        transactions.add(new Transaction('D', amount, getBalance(), "向账户存储"+amount+"元"));
    }

    public void printAccountSummary() {
        System.out.println("Name: " + name);
        System.out.println("Annual Interest Rate: " + getAnnualInterestRate() + "%%");
        System.out.println("Balance: " + getBalance());
        System.out.println("All Transactions: ");
        for (Transaction transaction : transactions) {
            System.out.println("\t" + transaction.toString());
        }
    }
}

class Transaction {
    private Date date; // 该交易的日期
    private char type; // 交易类型
    private double amount; // 交易量
    private double balance; // 交易后的新余额
    private String description; // 交易的描述

    public Transaction(char type, double amount, double balance, String description) {
        this.date = new Date();
        this.type = type;
        this.amount = amount;
        this.balance = balance;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "date=" + date +
                ", type=" + type +
                ", amount=" + amount +
                ", balance=" + balance +
                ", description='" + description + '\'' +
                '}';
    }
}

class Account {
    private int id = 0; // 标识账号
    private double balance = 0; // 余额
    private static double annualInterestRate = 0; // 当前利率
    private Date dateCreated; // 账户开户日期

    public Account() {
    }

    public Account(int id, double balance) {
        this.id = id;
        this.balance = balance;
        this.dateCreated = new Date();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public static double getAnnualInterestRate() {
        return annualInterestRate;
    }

    public static void setAnnualInterestRate(double annualInterestRate) {
        Account.annualInterestRate = annualInterestRate;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    // 返回月利率
    public double getMonthlyInterestRate() {
        return annualInterestRate / 100 / 12;
    }

    // 返回月利息
    public double getMonthlyInterest() {
        return balance * getMonthlyInterestRate();
    }

    // 取款
    public void withdraw(double amount) {
        if (this.balance >= amount) this.balance -= amount;
    }

    // 存款
    public void deposit(double amount) {
        this.balance += amount;
    }
}