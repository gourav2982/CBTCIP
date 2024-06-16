package banky;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Bank {
    private Map<String, Account> accounts;
    private String filename;

    public Bank(String filename) {
        this.filename = filename;
        accounts = new HashMap<>();
        try {
            loadAccounts();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No existing accounts file found. Starting with a new bank.");
        }
    }

    public void createAccount(String accountNumber, String owner, double initialDeposit) {
        if (accounts.containsKey(accountNumber)) {
            System.out.println("Account number already exists.");
        } else {
            Account newAccount = new Account(accountNumber, owner, initialDeposit);
            accounts.put(accountNumber, newAccount);
            System.out.println("Account created: " + newAccount);
            saveAccounts();
        }
    }

    public Account getAccount(String accountNumber) {
        return accounts.get(accountNumber);
    }
    public void getAccountDetail(String accountNumber){
        Account acc = accounts.get(accountNumber);
        System.out.println();
        System.out.println("--------------------------------------------------");
        System.out.println("Details for Account Number: "+ accountNumber);
        System.out.println("Name of the holder: "+ acc.getOwner());
        System.out.println("Balance: "+ acc.getBalance());
        System.out.println("--------------------------------------------------");
        System.out.println();

    }

    public void deposit(String accountNumber, double amount) {
        Account account = accounts.get(accountNumber);
        if (account != null) {
            account.deposit(amount);
            saveAccounts();
        } else {
            System.out.println("Account not found.");
        }
    }

    public void withdraw(String accountNumber, double amount) {
        Account account = accounts.get(accountNumber);
        if (account != null) {
            account.withdraw(amount);
            saveAccounts();
        } else {
            System.out.println("Account not found.");
        }
    }

    public void transfer(String fromAccountNumber, String toAccountNumber, double amount) {
        Account fromAccount = accounts.get(fromAccountNumber);
        Account toAccount = accounts.get(toAccountNumber);
        if (fromAccount != null && toAccount != null) {
            if (fromAccount.getBalance() >= amount) {
                fromAccount.withdraw(amount);
                toAccount.deposit(amount);
                System.out.println("Transferred " + amount + " from " + fromAccountNumber + " to " + toAccountNumber);
                saveAccounts();
            } else {
                System.out.println("Insufficient funds in the source account.");
            }
        } else {
            System.out.println("One or both accounts not found.");
        }
    }

    private void saveAccounts() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(accounts);
        } catch (IOException e) {
            System.out.println("Error saving accounts: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private void loadAccounts() throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            accounts = (Map<String, Account>) ois.readObject();
        }
    }
}
