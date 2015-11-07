package tw.pc.core;

import org.junit.Test;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ExpenseReportTest {
    @Test
    public void should_create_receipt() throws Exception {
        Receipt receipt = new ReceiptImpl("R-2789", 120);
        assertThat(receipt.getAmount(), is(120d));

    }

    @Test
    public void should_get_amount_from_receipt() throws Exception {
        Receipt receipt = mock(Receipt.class);
        when(receipt.getAmount()).thenReturn(120.5);
        Expense expense = new ExpenseImpl();
        expense.addReceipt(receipt);
        assertThat(expense.getAmount(), is(120.5));
    }

    @Test
    public void should_calculate_amount_from_expense_report() throws Exception {
        ExpenseReport report = new ExpenseReportImpl();
        Expense expense1 = mock(Expense.class);
        when(expense1.getAmount()).thenReturn(120d);
        Expense expense2 = mock(Expense.class);
        when(expense2.getAmount()).thenReturn(63.5d);
        report.addExpenses(asList(expense1, expense2));
        assertThat(report.getAmount(), is(183.5));
    }
}
