package tw.pc.mapper;

import org.apache.ibatis.annotations.Param;
import tw.pc.domain.AuditExpense;

public interface AuditExpenseMapper {
    int createExpense(@Param("auditExpense") AuditExpense auditExpense);
}
