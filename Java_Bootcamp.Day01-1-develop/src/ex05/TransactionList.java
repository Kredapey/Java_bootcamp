package ex05;


import java.util.UUID;

public interface TransactionList {
    public void addTransaction(Transaction transaction);

    public Transaction removeTransactionById(UUID id) throws TransactionNotFoundException;

    public Transaction[] transactionToArray();
}
