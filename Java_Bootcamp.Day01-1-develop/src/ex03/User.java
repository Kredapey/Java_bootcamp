package ex03;

public class User {
    private String name;
    private double balance;
    private final int id;
    private TransactionList transactionList;

    public User(String name, double balance) {
        if (balance >= 0) {
            this.balance = balance;
        } else {
            System.err.println("Balance can't be less then zero");
        }
        this.name = name;
        this.id = UserIdsGenerator.getInstance().generateId();
        this.transactionList = new TransactionsLinkedList();
    }

    public void addTransaction(Transaction transaction) {
        transactionList.addTransaction(transaction);
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public int getId() {
        return id;
    }

    public void setBalance(double balance) {
        if (balance >= 0) {
            this.balance = balance;
        } else {
            System.err.println("Balance can't be less then zero");
        }
    }

    public TransactionList getTransactionList() {
        return transactionList;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", balance=" + balance +
                ", id=" + id +
                '}';
    }
}
