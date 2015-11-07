package tw.pc.core;

public interface Expense {
    void addReceipt(Receipt receipt);

    double getAmount();

    Policy getPolicy();
}
