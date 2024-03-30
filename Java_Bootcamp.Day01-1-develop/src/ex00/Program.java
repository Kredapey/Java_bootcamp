package ex00;

public class Program {
    public static void main(String[] args) {
        User us1 = new User("John", 250);
        User us2 = new User("Steve", 1500);
        System.out.println(us1);
        System.out.println(us2);
        Transaction debit_transaction = new Transaction(us1, us2,
                Transaction.TransferCategory.DEBIT, 250);
        System.out.println(debit_transaction);
    }
}
