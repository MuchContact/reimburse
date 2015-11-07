package tw.pc.core;

import java.util.ArrayList;
import java.util.List;

public class ExpenseImpl implements Expense {
    private List<Receipt> receipts;

    public ExpenseImpl() {
        receipts = new ArrayList<>();
    }

    @Override
    public void addReceipt(Receipt receipt) {
        receipts.add(receipt);
    }

    @Override
    public double getAmount() {
        final double[] total = new double[1];
        receipts.stream().forEach(receipt -> {
            total[0] = receipt.getAmount();
        });
        return total[0];
    }

    @Override
    public Policy getPolicy() {
        return null;
    }
}
