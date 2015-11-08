package tw.pc.mapper;

import org.apache.ibatis.annotations.Param;
import tw.pc.domain.AuditExpense;
import tw.pc.domain.AuditedExpenseReport;

public interface AuditedExpenseReportMapper {
    int createReport(@Param("expenseReport") AuditedExpenseReport expenseReport);

    int addExpense(@Param("auditReport") AuditedExpenseReport auditReport, @Param("auditExpense") AuditExpense auditExpense);

    AuditedExpenseReport getAuditReportById(@Param("id") int id);
}