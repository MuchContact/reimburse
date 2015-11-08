package tw.pc.mapper;

import org.apache.ibatis.annotations.Param;
import tw.pc.domain.Expense;

public interface ExpenseMapper {
    int createExpense(@Param("expense") Expense expense);

    Expense getExpenseById(int id);
}
