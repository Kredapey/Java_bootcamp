package ex01;

public class User {
    private String name;
    private double balance;
    private final int id;

    public User(String name, double balance) {
        if (balance >= 0) {
            this.balance = balance;
        } else {
            System.err.println("Balance can't be less then zero");
        }
        this.name = name;
        this.id = UserIdsGenerator.getInstance().generateId();
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

