package ex03;

import java.util.UUID;

public class Transaction {
    public enum TransferCategory {
        DEBIT, CREDIT
    }

    private UUID id;
    private User recipient;
    private User sender;
    private Transaction.TransferCategory category;
    private double transferAmount;

    public Transaction(User recipient, User sender,
                       Transaction.TransferCategory category,
                       double transferAmount) {
        if ((category == Transaction.TransferCategory.DEBIT
                && transferAmount < 0)) {
            System.err.println("Income transfer amount should be greater then zero");
        } else if ((category == Transaction.TransferCategory.CREDIT
                && transferAmount > 0)) {
            System.err.println("Outcome transfer amount should be less then zero");
        } else {
            if (category == Transaction.TransferCategory.DEBIT
                    && sender.getBalance() < transferAmount) {
                System.err.println("Sender balance is less then transfer amount");
            } else if (category == Transaction.TransferCategory.CREDIT
                    && recipient.getBalance() < -transferAmount) {
                System.err.println("Recipient balance is less then transfer amount");
            } else {
                this.id = UUID.randomUUID();
                this.recipient = recipient;
                this.sender = sender;
                this.category = category;
                this.transferAmount = transferAmount;
                changeBalance();
                this.recipient.addTransaction(this);
                this.sender.addTransaction(this);
            }
        }
    }

    private void changeBalance() {
        recipient.setBalance(recipient.getBalance() + transferAmount);
        sender.setBalance(sender.getBalance() - transferAmount);
    }

    public UUID getId() {
        return id;
    }

    public User getRecipient() {
        return recipient;
    }

    public User getSender() {
        return sender;
    }

    public Transaction.TransferCategory getCategory() {
        return category;
    }

    public double getTransferAmount() {
        return transferAmount;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", recipient=" + recipient +
                ", sender=" + sender +
                ", category=" + category +
                ", transferAmount=" + transferAmount +
                '}' + '\n';
    }
}
