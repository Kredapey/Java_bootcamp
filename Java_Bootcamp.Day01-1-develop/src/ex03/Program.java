package ex03;

import java.util.Arrays;
import java.util.UUID;

public class Program {
    public static void main(String[] args) throws TransactionNotFoundException {
        User us1 = new User("Ivan", 500);
        User us2 = new User("Niko", 500);
        User us3 = new User("Alex", 500);
        Transaction transaction1 = new Transaction(us1, us2,
                Transaction.TransferCategory.DEBIT, 100);
        Transaction transaction2 = new Transaction(us2, us3,
                Transaction.TransferCategory.CREDIT, -200);
        Transaction transaction3 = new Transaction(us1, us3,
                Transaction.TransferCategory.DEBIT, 400);
        System.out.println("IVAN TRANSACTIONS");
        Transaction[] ivanTransactions =
                us1.getTransactionList().transactionToArray();
        System.out.println(Arrays.toString(ivanTransactions));
        System.out.println("NIKO TRANSACTIONS");
        Transaction[] nikoTransactions =
                us2.getTransactionList().transactionToArray();
        System.out.println(Arrays.toString(nikoTransactions));
        System.out.println("ALEX TRANSACTIONS");
        Transaction[] alexTransactions =
                us3.getTransactionList().transactionToArray();
        System.out.println(Arrays.toString(alexTransactions));
        UUID id = alexTransactions[0].getId();
        us3.getTransactionList().removeTransactionById(id);
        alexTransactions = us3.getTransactionList().transactionToArray();
        System.out.println("ALEX TRANSACTIONS AFTER DELETE");
        System.out.println(Arrays.toString(alexTransactions));
        System.out.println("WRONG DELETE");
        us3.getTransactionList().removeTransactionById(UUID.randomUUID());
    }
}
