import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class ReadTransactions {

    public static void main(String[] args) {
        final File fileName = new File("src/Transactions.csv");
        HashMap<String, Account> currentAccounts = new HashMap<>();

        for (Transaction tra : fetchAllTransactions(fileName)) {
            if (currentAccounts.keySet().contains(tra.getAccountNumber()))
                currentAccounts.get(tra.getAccountNumber()).makeTransaction(tra);
            else {
                currentAccounts.put(tra.getAccountNumber(), new Account(tra.getAccountNumber()));
                currentAccounts.get(tra.getAccountNumber()).makeTransaction(tra);
            }
        }
        printResults(currentAccounts);
    }

    public static List<Transaction> fetchAllTransactions(File file) {
        String[] parameters;
        List<Transaction> transactions = new ArrayList<>();
        Scanner reader = null;
        try {
            reader = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.err.println(e);
        }
        while (reader.hasNext()) {
            parameters = reader.next().split(",");
            transactions.add(new Transaction(parameters[0], parameters[1], Double.parseDouble(parameters[2])));
        }
        reader.close();
        return transactions;
    }

    public static void printResults(HashMap<String, Account> accounts) {
        for (Account acc : accounts.values())
            System.out.println(acc);
    }
}

