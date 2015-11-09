package tw.pc.mapper;

import org.junit.Before;
import org.junit.Test;
import tw.pc.domain.AuditExpense;
import tw.pc.domain.AuditedExpenseReport;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class AuditedExpenseReportMapperTest extends BaseMapperTest{
    private AuditedExpenseReportMapper mapper;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        mapper = sqlSession.getMapper(AuditedExpenseReportMapper.class);

    }

    @Test
    public void should_persist_audit_expense_report_separately() throws Exception {
        AuditedExpenseReport auditReport = new AuditedExpenseReport("employees/1/expense reports/1/expenses/1");
        int result = mapper.createReport(auditReport);
        assertThat(result, is(1));
    }

    @Test
    public void should_persist_audit_expense_report_with_policy() throws Exception {
        AuditedExpenseReport auditReport = new AuditedExpenseReport("employees/1/expense reports/1/expenses/1", "accoutants/policies/1", 300);
        int result = mapper.createReport(auditReport);
        assertThat(result, is(1));
    }

    @Test
    public void should_persist_audit_expense_into_audit_export_report() throws Exception {
        AuditExpenseMapper expenseMapper = sqlSession.getMapper(AuditExpenseMapper.class);

        AuditExpense auditExpense = new AuditExpense(300, "employees/1/expense reports/1/expenses/1", "accoutants/policies/1");
        expenseMapper.auditExpense(auditExpense);

        AuditedExpenseReportMapper reportMapper = sqlSession.getMapper(AuditedExpenseReportMapper.class);
        AuditedExpenseReport auditReport = new AuditedExpenseReport("employees/1/expense reports/1/expenses/1");
        reportMapper.createReport(auditReport);
        int result = reportMapper.addExpense(auditReport, auditExpense);
        assertThat(result, is(1));

    }

    @Test
    public void should_get_audited_expense_report_from_db() throws Exception {
        AuditedExpenseReportMapper reportMapper = sqlSession.getMapper(AuditedExpenseReportMapper.class);
        AuditedExpenseReport auditReport = new AuditedExpenseReport("employees/1/expense reports/1/expenses/1");
        reportMapper.createReport(auditReport);
        AuditedExpenseReport fetchedAuditReportFromDB = reportMapper.getAuditReportById(auditReport.getId());
        assertThat(fetchedAuditReportFromDB.getExpenseReport(), is("employees/1/expense reports/1/expenses/1"));
        assertThat(fetchedAuditReportFromDB.getReferencedPolicy(), is(nullValue()));

    }
}
