package ex05;

import java.util.Scanner;
import java.util.UUID;

public class Menu {
    public enum Status {
        PROD, DEV
    }

    private TransactionService transactionService;
    private Status status;

    public Menu(Status status) {
        this.status = status;
        transactionService = new TransactionService();
    }

    public void start() {
        printMenu();
        userChoice();
    }

    private void userChoice() {
        try {
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    addUser();
                    break;
                case 2:
                    checkUserBalance();
                    break;
                case 3:
                    performTransfer();
                    break;
                case 4:
                    viewAllUserTransactions();
                    break;
                case 5:
                    removeTransfer();
                    break;
                case 6:
                    invalidTransfers();
                    break;
                case 7:
                    return;
                default:
                    System.out.println("Enter the correct number (1 - 7)");
                    start();
            }
        } catch (RuntimeException runExc) {
            System.out.println("Enter the correct number (1 - 7)");
            start();
        }
    }

    private void printMenu() {
        System.out.println("1. Add a user");
        System.out.println("2. View user balances");
        System.out.println("3. Perform a transfer");
        System.out.println("4. View all transactions for a specific user");
        System.out.println("5. DEV – remove a transfer by ID");
        System.out.println("6. DEV – check transfer validity");
        System.out.println("7. Finish execution");
        System.out.print("-> ");
    }

    private void addUser() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter a user name and a balance");
            String name = scanner.next();
            double balance = scanner.nextDouble();
            User addingUser = new User(name, balance);
            transactionService.addUser(addingUser);
            System.out.println("User with id = " + addingUser.getId() + " is added");
        } catch (RuntimeException runExc) {
            System.out.println("Enter correct data");
            System.out.println(runExc);
        } finally {
            start();
        }
    }

    private void checkUserBalance() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter a user id");
            int id = scanner.nextInt();
            System.out.println(transactionService.usersList.getUserById(id).getName()
                    + " - " + transactionService.usersList.getUserById(id).getBalance());
        } catch (RuntimeException runExc) {
            System.out.println("Enter correct data");
            System.out.println(runExc);
        } finally {
            start();
        }
    }

    private void performTransfer() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter a sender ID, a recipient ID, and a transfer amount");
            int senderId = scanner.nextInt();
            int recipientId = scanner.nextInt();
            double transferAmount = scanner.nextDouble();
            transactionService.transactionTransfer(recipientId, senderId, transferAmount);
            System.out.println("The transfer is completed");
        } catch (RuntimeException runExc) {
            System.out.println("Enter correct data");
            System.out.println(runExc);
        } finally {
            start();
        }
    }

    private void viewAllUserTransactions() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter a user ID");
            int userId = scanner.nextInt();
            Transaction[] userTransactions =
                    transactionService.getUserTransactions(transactionService.usersList.getUserById(userId));
            for (Transaction t : userTransactions) {
                System.out.println(t.toString());
            }
        } catch (RuntimeException runExc) {
            System.out.println("Enter correct data");
            System.out.println(runExc);
        } finally {
            start();
        }
    }

    private void removeTransfer() {
        if (status == Status.DEV) {
            try {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Enter a user ID and a transfer ID");
                int userId = scanner.nextInt();
                String transferId = scanner.next();
                UUID id = UUID.fromString(transferId);
                Transaction deletedTransaction = transactionService.deleteTransactionById(userId, id);
                System.out.println(deletedTransaction.toString() + " deleted");
            } catch (RuntimeException runExc) {
                System.out.println("Enter correct data");
                System.out.println(runExc);
            } finally {
                start();
            }
        } else {
            System.err.println("You don't have permissions for this function");
            start();
        }
    }

    private void invalidTransfers() {
        if (status == Status.DEV) {
            try {
                System.out.println("Check results:");
                Transaction[] unpairTransactions = transactionService.checkTransactionValid();
                for (Transaction t : unpairTransactions) {
                    System.out.println(t.toString() + " is unacknowledged transfer");
                }
            } catch (RuntimeException runExc) {
                System.out.println("Enter correct data");
                System.out.println(runExc);
            } finally {
                start();
            }
        } else {
            System.err.println("You don't have permissions for this function");
            start();
        }
    }

}
