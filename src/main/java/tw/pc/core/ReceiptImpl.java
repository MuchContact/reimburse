package tw.pc.core;

public class ReceiptImpl implements Receipt {
    private final String receiptId;
    private final double amount;

    public ReceiptImpl(String receiptId, double amount) {
        this.receiptId = receiptId;
        this.amount = amount;
    }

    @Override
    public double getAmount() {
        return amount;
    }
}
