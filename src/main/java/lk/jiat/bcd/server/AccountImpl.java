package lk.jiat.bcd.server;

import BankingApp.AccountPOA;
import BankingApp.InsufficientBalance;

import java.util.HashMap;

public class AccountImpl  extends AccountPOA {

    private HashMap<String, Double> db = new HashMap<>();

    public AccountImpl() {
        db.put("Account-001",1000.0);
        db.put("Account-002",2000.0);
        db.put("Account-003",3000.0);
    }

    @Override
    public double getBalance(String accNo) {
        return db.getOrDefault(accNo,0.0);
    }

    @Override
    public void deposit(String accNo, double amount) {
        double currentBalance = db.getOrDefault(accNo,0.0);
        db.put(accNo,currentBalance + amount);

        System.out.println("Server Log: Deposited " + accNo + " from " + currentBalance + " to " + accNo);
    }

    @Override
    public void withdraw(String accNo, double amount) throws InsufficientBalance {

        double currentBalance = db.getOrDefault(accNo,0.0);

        if (currentBalance < amount) {
            System.out.println("Server Log: Failed to withdraw " + accNo + " from " + currentBalance + " to " + accNo);
            throw new InsufficientBalance("Insufficient balance in account: " + accNo);
        }

        db.put(accNo,currentBalance - amount);
        System.out.println("Server Log: Withdrew " + accNo + " from " + currentBalance + " to " + accNo);

    }
}
