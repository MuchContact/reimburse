package tw.pc.mapper;

import org.junit.Test;
import tw.pc.domain.AuditExpense;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class AuditExpenseMapperTest extends BaseMapperTest{

    @Test
    public void should_persist_audit_expense() throws Exception {
        AuditExpenseMapper mapper = sqlSession.getMapper(AuditExpenseMapper.class);

        AuditExpense auditExpense = new AuditExpense(300, "employees/1/expense reports/1/expenses/1", "accoutants/policies/1");
        int result = mapper.auditExpense(auditExpense);
        assertThat(result, is(1));
    }
}
