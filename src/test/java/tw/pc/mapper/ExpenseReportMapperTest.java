package tw.pc.mapper;

import org.junit.Test;
import tw.pc.domain.Expense;
import tw.pc.domain.ExpenseReportBean;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ExpenseReportMapperTest extends BaseMapperTest{

    @Test
    public void should_create_new_report() throws Exception {
        ExpenseReportMapper mapper = sqlSession.getMapper(ExpenseReportMapper.class);
        ExpenseReportBean report = new ExpenseReportBean("Travel to Xi'an");
        int affectRows = mapper.createNewExpenseReport(report);
        ExpenseReportBean fetchedFromDb = mapper.getExpenseReportById(report.getId());
        assertThat(affectRows, is(1));
        assertThat(fetchedFromDb.getName(), is("Travel to Xi'an"));
    }

    @Test
    public void should_add_a_single_expense() throws Exception {
        ExpenseReportMapper mapper = sqlSession.getMapper(ExpenseReportMapper.class);
        ExpenseReportBean report = new ExpenseReportBean("Travel to Xi'an");
        Expense expense = new Expense("Li", 39.8d);
        mapper.createNewExpenseReport(report);
        int result = mapper.addExpense(report, expense);
        assertThat(result, is(1));
    }
}
