package tw.pc.api;

import tw.pc.domain.AuditExpense;
import tw.pc.domain.AuditedExpenseReport;
import tw.pc.domain.json.AuditExpenseRefJson;
import tw.pc.mapper.AuditExpenseMapper;
import tw.pc.mapper.AuditedExpenseReportMapper;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

public class AuditExpensesApi {
    private AuditedExpenseReport auditedExpenseReport;

    public AuditExpensesApi(AuditedExpenseReport auditReport) {
        this.auditedExpenseReport = auditReport;
    }

    @Path("/audit expenses")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response createAuditExpense(@Context UriInfo uri,
                                       @BeanParam AuditedExpenseReportMapper reportMapper,
                                       @BeanParam AuditExpenseMapper mapper,
                                       @FormParam("expenseURI") String expense,
                                       @FormParam("approvedAmount") double approvedAmount,
                                       @FormParam("policyURI") String policy){
        AuditExpense auditExpense = new AuditExpense(approvedAmount, expense, policy);
        mapper.auditExpense(auditExpense);
        reportMapper.addExpense(auditedExpenseReport, auditExpense);
        AuditExpenseRefJson auditExpenseRefJson = new AuditExpenseRefJson(auditExpense, uri);
        return Response.status(201).entity(auditExpenseRefJson).build();
    }

}

