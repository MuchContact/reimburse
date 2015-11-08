package tw.pc.api;

import tw.pc.domain.AuditedExpenseReport;
import tw.pc.domain.json.AuditedExpenseReportRefJson;
import tw.pc.mapper.AuditedExpenseReportMapper;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

public class AccountantApi {
    @Path("/audited expense reports")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response createNewAuditReport(@FormParam("expenseURI") String expenseReport,
                                         @Context UriInfo uri,
                                         @BeanParam AuditedExpenseReportMapper mapper){
        AuditedExpenseReport auditedExpenseReport = new AuditedExpenseReport(expenseReport);
        mapper.createReport(auditedExpenseReport);
        AuditedExpenseReportRefJson auditedExpenseReportRefJson = new AuditedExpenseReportRefJson(auditedExpenseReport, uri);
        return Response.status(201).entity(auditedExpenseReportRefJson).build();
    }

    @Path("/audited expense reports/{id}")
    public AuditExpensesApi getAuditExpenseApi(@PathParam("id") int id,
                                               @BeanParam AuditedExpenseReportMapper reportMapper){
        AuditedExpenseReport auditReport = reportMapper.getAuditReportById(id);
        return new AuditExpensesApi(auditReport);
    }

}
