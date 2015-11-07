package tw.pc.core;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AuditedExpenseReportImpl implements AuditedExpenseReport {
    private ExpenseReport expenseReport;
    private boolean isValid;
    private String errorMsg;
    private List<Expense> validExpenses;
    private List<Expense> invalidExpenses;

    public AuditedExpenseReportImpl(ExpenseReport expenseReport) {
        this.expenseReport = expenseReport;
        validExpenses = new ArrayList<>();
        invalidExpenses = new ArrayList<>();
    }

    @Override
    public boolean isValid() {
        return isValid;
    }

    @Override
    public void audit() {
        isValid = expenseReport.getPolicy().isValid(expenseReport);
        errorMsg = expenseReport.getPolicy().getErrorMessage(expenseReport);
        validExpenses = expenseReport.getExpenses().stream()
                .filter(expense -> expense.getPolicy().isValid(expense))
                .collect(Collectors.toCollection(ArrayList::new));
        invalidExpenses = expenseReport.getExpenses().stream()
                .filter(expense -> !expense.getPolicy().isValid(expense))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public String getErrorMessage() {
        return errorMsg;
    }

    @Override
    public double getAmount() {
        if (!isValid)
            return 0;
        return validExpenses.stream().mapToDouble(s->s.getAmount()).sum();
    }

    @Override
    public List<Expense> getInvalidExpenses() {
        return invalidExpenses;
    }
}
