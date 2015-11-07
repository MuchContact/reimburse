package tw.pc.core;

import java.util.List;

public interface AuditedExpenseReport {
    boolean isValid();

    void audit();

    String getErrorMessage();

    double getAmount();

    List<Expense> getInvalidExpenses();
}
