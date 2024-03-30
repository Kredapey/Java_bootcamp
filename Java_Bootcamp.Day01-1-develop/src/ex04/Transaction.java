package ex04;

import java.util.UUID;

public class Transaction {
    public enum TransferCategory {
        DEBIT, CREDIT
    }

    private UUID id;
    private User recipient;
    private User sender;
    private TransferCategory category;
    private double transferAmount;

    public Transaction(User recipient, User sender, TransferCategory category,
                       double transferAmount) {
        if ((category == TransferCategory.DEBIT && transferAmount < 0)) {
            System.err.println("Income transfer amount should be greater then zero");
        } else if ((category == TransferCategory.CREDIT
                && transferAmount > 0)) {
            System.err.println("Outcome transfer amount should be less then zero");
        } else {
            if (category == TransferCategory.DEBIT
                    && sender.getBalance() < transferAmount) {
                System.err.println("Sender balance is less then transfer amount");
            } else if (category == TransferCategory.CREDIT
                    && recipient.getBalance() < -transferAmount) {
                System.err.println("Recipient balance is less then transfer amount");
            } else {
                this.recipient = recipient;
                this.sender = sender;
                this.category = category;
                this.transferAmount = transferAmount;
            }
        }
    }

    public void setId(UUID id) {
        this.id = id;
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

    public TransferCategory getCategory() {
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
