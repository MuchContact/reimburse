package tw.pc.mapper;

import org.junit.Test;
import tw.pc.domain.Expense;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ExpenseMapperTest extends BaseMapperTest{
    @Test
    public void should_create_a_new_expense_test() throws Exception {
        ExpenseMapper mapper = sqlSession.getMapper(ExpenseMapper.class);
        int affectRows = mapper.createExpense(new Expense("Li", 39.8d));
        assertThat(affectRows, is(1));
    }

}
