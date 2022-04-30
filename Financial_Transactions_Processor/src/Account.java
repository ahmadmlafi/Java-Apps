import java.util.ArrayList;
import java.util.List;

public class Account {
    private String accountNumber;
    private List<Transaction> transactionsHistory;
    private double balance;

    public Account(String account_number) {
        this.accountNumber = account_number;
        transactionsHistory = new ArrayList<>();
        balance = 0;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public List<Transaction> getTransactionsHistory() {
        return transactionsHistory;
    }

    public double getBalance() {
        return balance;
    }

    public void makeTransaction(Transaction transaction) {
        if (transaction.getAccountNumber().equalsIgnoreCase(getAccountNumber())) {
            if (transaction.getType().equals("D"))
                balance -= transaction.getAmount();
            else if (transaction.getType().equals("C"))
                balance += transaction.getAmount();
            else return;
            transactionsHistory.add(transaction);
        }
    }

    @Override
    public String toString() {
        return "Account with number: " + accountNumber + " ,has: " + balance;
    }
}
