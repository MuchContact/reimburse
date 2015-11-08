package tw.pc.mapper;

import org.junit.Test;
import tw.pc.domain.AuditedExpenseReport;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class AuditedExpenseReportMapperTest extends BaseMapperTest{

    @Test
    public void should_persist_audit_expense_separately() throws Exception {
        AuditedExpenseReportMapper mapper = sqlSession.getMapper(AuditedExpenseReportMapper.class);
        AuditedExpenseReport auditReport = new AuditedExpenseReport("employees/1/expense reports/1/expenses/1");
        int result = mapper.createReport(auditReport);
        assertThat(result, is(1));
    }

    @Test
    public void should_persist_audit_expense_with_policy() throws Exception {
        AuditedExpenseReportMapper mapper = sqlSession.getMapper(AuditedExpenseReportMapper.class);
        AuditedExpenseReport auditReport = new AuditedExpenseReport("employees/1/expense reports/1/expenses/1", "accoutants/policies/1", 300);
        int result = mapper.createReport(auditReport);
        assertThat(result, is(1));
    }
}
