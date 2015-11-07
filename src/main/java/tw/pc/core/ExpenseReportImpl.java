package tw.pc.core;

import java.util.ArrayList;
import java.util.List;

public class ExpenseReportImpl implements ExpenseReport {
    private List<Expense> expenses;
    private List<Policy> policies;

    public ExpenseReportImpl() {
        expenses = new ArrayList<>();
        policies = new ArrayList<>();
    }

    @Override
    public void addExpenses(List<Expense> expenses) {
        this.expenses.addAll(expenses);
    }

    @Override
    public double getAmount() {
        final double[] total = {0};
        expenses.stream().forEach(expense -> {
            total[0] += expense.getAmount();
        });
        return total[0];
    }

    @Override
    public void addPolicy(Policy policy) {
        policies.add(policy);
    }

    @Override
    public Policy getPolicy() {
        return policies.get(0);
    }

    @Override
    public List<Expense> getExpenses() {
        return expenses;
    }
}
