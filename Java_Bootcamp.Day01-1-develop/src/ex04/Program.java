package ex04;

import java.util.Arrays;
import java.util.UUID;

public class Program {
    public static void main(String[] args) throws UserNotFoundException, IllegalTransactionException {
        TransactionService transactionService = new TransactionService();
        transactionService.addUser(new User("Ivan", 500));
        transactionService.addUser(new User("Niko", 500));
        transactionService.addUser(new User("Alex", 500));
        transactionService.transactionTransfer(1, 2, 15);
        transactionService.transactionTransfer(2, 3, 20);
        transactionService.transactionTransfer(3, 1, 25);
        System.out.println("Ivan balance: "
                + transactionService.getBalance(transactionService.getUsersList().getUserById(1)));
        System.out.println("Niko balance: "
                + transactionService.getBalance(transactionService.getUsersList().getUserById(2)));
        System.out.println("Alex balance: "
                + transactionService.getBalance(transactionService.getUsersList().getUserById(3)));
        System.out.println("IVAN TRANSACTIONS");
        System.out.println(Arrays.toString(transactionService.getUserTransactions(transactionService.getUsersList().getUserById(1))));
        UUID deleteId = transactionService.getUserTransactions(transactionService.getUsersList().getUserById(1))[0].getId();
        transactionService.deleteTransactionById(1, deleteId);
        System.out.println("IVAN TRANSACTIONS AFTER DELETE");
        System.out.println(Arrays.toString(transactionService.getUserTransactions(transactionService.getUsersList().getUserById(1))));
        System.out.println("UNPAIRED TRANSACTIONS");
        System.out.println(Arrays.toString(transactionService.checkTransactionValid()));
    }
}
