package tw.pc.api;

import tw.pc.mapper.AuditedExpenseReportMapper;

import javax.inject.Inject;
import javax.ws.rs.Path;

@Path("accountants")
public class AccountantsApi {
    @Inject
    AuditedExpenseReportMapper auditedExpenseReportMapper;

    @Path("/{a-id}")
    public AccountantApi getAccountant(){
        return new AccountantApi();
    }
}
