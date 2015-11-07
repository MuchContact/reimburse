package tw.pc.core;

import java.util.List;

public interface ExpenseReport {
    void addExpenses(List<Expense> expenses);

    double getAmount();

    void addPolicy(Policy policy);

    Policy getPolicy();

    List<Expense> getExpenses();
}
