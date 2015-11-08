package tw.pc.mapper;

import org.apache.ibatis.annotations.Param;
import tw.pc.domain.Expense;
import tw.pc.domain.ExpenseReportBean;

public interface ExpenseReportMapper {
    int createNewExpenseReport(@Param("report") ExpenseReportBean report);

    ExpenseReportBean getExpenseReportById(@Param("id") int id);

    int addExpense(@Param("report") ExpenseReportBean report, @Param("expense") Expense expense);
}
