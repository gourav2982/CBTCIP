package banky;



public class Main {
    public static void main(String[] args) {
        Bank bank = new Bank("banky/accounts.dat");

        // Create some accounts
        bank.createAccount("123", "Alice", 1000);
        bank.createAccount("456", "Bob", 500);
        //Check account details
        bank.getAccountDetail("123");

        // Deposit and withdraw funds
        bank.deposit("123", 200);
        bank.withdraw("123", 100);

        // Transfer funds
        bank.transfer("123", "456", 300);
    }
}
