package ex05;

import java.util.UUID;

public class TransactionsLinkedList implements TransactionList {
    private int size;
    private Node head;
    private Node tail;

    static class Node {
        Transaction transaction;
        Node next;
        Node prev;

        Node(Transaction t) {
            this.transaction = t;
            next = null;
            prev = null;
        }
    }

    public TransactionsLinkedList() {
        size = 0;
        head = null;
        tail = null;
    }

    @Override
    public void addTransaction(Transaction transaction) {
        Node temp = new Node(transaction);
        if (head == null) {
            head = temp;
            tail = temp;
        } else {
            temp.next = head;
            head.prev = temp;
            head = temp;
        }
        size += 1;
    }

    @Override
    public Transaction removeTransactionById(UUID id) throws TransactionNotFoundException {
        Node temp = head;
        while (temp != null) {
            if (temp.transaction.getId().equals(id)) {
                removeTransactionFromList(temp);
                return temp.transaction;
            }
            temp = temp.next;
        }
        throw new TransactionNotFoundException("There is no transaction with this UUID");
    }

    private void removeTransactionFromList(Node src) {
        if (src == head && src == tail) {
            head = null;
            tail = null;
        } else if (src == head) {
            head = src.next;
            head.prev = null;
        } else if (src == tail) {
            tail = src.prev;
            src.next = null;
        } else {
            src.prev.next = src.next;
            src.next.prev = src.prev;
        }
        if (size != 0) {
            size -= 1;
        }
    }

    @Override
    public Transaction[] transactionToArray() {
        Transaction[] transactionArr = new Transaction[size];
        Node temp = head;
        int counter = 0;
        while (temp != null) {
            transactionArr[counter++] = temp.transaction;
            temp = temp.next;
        }
        return transactionArr;
    }
}
