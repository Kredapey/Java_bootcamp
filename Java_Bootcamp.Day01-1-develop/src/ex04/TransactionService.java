package ex04;

import java.util.UUID;

public class TransactionService {
    UsersList usersList;

    public TransactionService() {
        usersList = new UsersArrayList();
    }

    public void addUser(User user) {
        usersList.addUser(user);
    }

    public double getBalance(User user) {
        return user.getBalance();
    }

    public void transactionTransfer(int recipientId, int senderId,
                                    double transferAmount) throws UserNotFoundException, IllegalTransactionException {
        User recipient = usersList.getUserById(recipientId);
        User sender = usersList.getUserById(senderId);
        if (sender.getBalance() < transferAmount) {
            throw new IllegalTransactionException("there are not enough funds in " + sender.getName() + " the account");
        }
        UUID id = UUID.randomUUID();
        Transaction debit = new Transaction(recipient, sender, Transaction.TransferCategory.DEBIT, transferAmount);
        Transaction credit = new Transaction(sender, recipient, Transaction.TransferCategory.CREDIT, -transferAmount);
        debit.setId(id);
        credit.setId(id);
        sender.setBalance(sender.getBalance() - transferAmount);
        recipient.setBalance(recipient.getBalance() + transferAmount);
        sender.getTransactionList().addTransaction(credit);
        recipient.getTransactionList().addTransaction(debit);
    }

    public Transaction[] getUserTransactions(User user) {
        return user.getTransactionList().transactionToArray();
    }

    public void deleteTransactionById(int userId, UUID transactionId) {
        User user = usersList.getUserById(userId);
        user.getTransactionList().removeTransactionById(transactionId);
    }

    public Transaction[] checkTransactionValid() {
        TransactionList unpairTransactions = new TransactionsLinkedList();
        TransactionList allTransactions = getAllTransactions();
        Transaction[] allTransactionArray = allTransactions.transactionToArray();
        for (Transaction checkedTransaction : allTransactionArray) {
            int match = 0;
            for (Transaction checks : allTransactionArray) {
                if (checkedTransaction.getId().equals(checks.getId())) {
                    match += 1;
                }
            }
            if (match != 2) {
                unpairTransactions.addTransaction(checkedTransaction);
            }
        }
        return unpairTransactions.transactionToArray();
    }

    public UsersList getUsersList() {
        return usersList;
    }

    public TransactionList getAllTransactions() {
        TransactionList allTransactions = new TransactionsLinkedList();
        for (int i = 0; i < usersList.getCountUsers(); ++i) {
            User temp = usersList.getUserByIndex(i);
            Transaction[] userTransactions = temp.getTransactionList().transactionToArray();
            for (Transaction t : userTransactions) {
                allTransactions.addTransaction(t);
            }
        }
        return allTransactions;
    }
}
