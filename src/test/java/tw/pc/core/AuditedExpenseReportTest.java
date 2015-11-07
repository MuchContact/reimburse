package tw.pc.core;

import org.junit.Test;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AuditedExpenseReportTest {
    @Test
    public void should_add_policy_to_expense_report_without_exception() throws Exception {
        ExpenseReport expenseReport = mock(ExpenseReport.class);
        when(expenseReport.getPolicy()).thenReturn(Policy.TotalLessThan500);

        AuditedExpenseReport auditedExpenseReport = new AuditedExpenseReportImpl(expenseReport);
        auditedExpenseReport.audit();
        assertThat(auditedExpenseReport.isValid(), is(true));

    }

    @Test
    public void should_audit_as_invalid_expense_report_depend_on_policy() throws Exception {
        ExpenseReport expenseReport = mock(ExpenseReport.class);
        when(expenseReport.getAmount()).thenReturn(600d);
        when(expenseReport.getPolicy()).thenReturn(Policy.TotalLessThan500);

        AuditedExpenseReport auditedExpenseReport = new AuditedExpenseReportImpl(expenseReport);
        auditedExpenseReport.audit();
        assertThat(auditedExpenseReport.isValid(), is(false));
        assertThat(auditedExpenseReport.getErrorMessage(), is("总额不能大于500元"));
    }

    @Test
    public void should_audit_expense_report_check_invalid_expense_wich_conflicts_with_policy() throws Exception {
        Expense expense1 = mock(Expense.class);
        Expense expense2 = mock(Expense.class);
        when(expense1.getPolicy()).thenReturn(Policy.TotalLessThan500);
        when(expense1.getAmount()).thenReturn(600d);
        when(expense2.getPolicy()).thenReturn(Policy.NoLimitation);
        when(expense2.getAmount()).thenReturn(400d);
        ExpenseReport expenseReport = mock(ExpenseReport.class);
        when(expenseReport.getPolicy()).thenReturn(Policy.NoLimitation);
        when(expenseReport.getExpenses()).thenReturn(asList(expense1, expense2));

        AuditedExpenseReport auditedExpenseReport = new AuditedExpenseReportImpl(expenseReport);
        auditedExpenseReport.audit();
        assertThat(auditedExpenseReport.getAmount(), is(400d));
        assertThat(auditedExpenseReport.getInvalidExpenses().get(0), is(expense1));
    }


}
