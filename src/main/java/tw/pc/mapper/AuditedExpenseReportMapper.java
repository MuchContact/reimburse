package tw.pc.mapper;

import org.apache.ibatis.annotations.Param;
import tw.pc.domain.AuditedExpenseReport;

public interface AuditedExpenseReportMapper {
    int createReport(@Param("expenseReport") AuditedExpenseReport expenseReport);
}