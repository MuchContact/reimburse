package tw.pc.api;

import tw.pc.mapper.AuditExpenseMapper;
import tw.pc.mapper.AuditedExpenseReportMapper;

import javax.inject.Inject;
import javax.ws.rs.Path;

@Path("accountants")
public class AccountantsApi {
    @Inject
    AuditedExpenseReportMapper auditedExpenseReportMapper;

    @Inject
    AuditExpenseMapper auditExpenseMapper;

    @Path("/{a-id}")
    public AccountantApi getAccountant(){
        return new AccountantApi();
    }
}
