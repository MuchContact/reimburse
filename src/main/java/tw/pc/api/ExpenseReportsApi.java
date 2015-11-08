package tw.pc.api;

import tw.pc.domain.ExpenseReportBean;
import tw.pc.domain.json.ExpenseReportRefJson;
import tw.pc.mapper.ExpenseReportMapper;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

public class ExpenseReportsApi {

   @Path("/")
   @POST
   @Produces(MediaType.APPLICATION_JSON)
   public Response createNewResponse(@FormParam("name") String name, @Context UriInfo uri, @BeanParam ExpenseReportMapper mapper){
      ExpenseReportBean reportBean = new ExpenseReportBean(name);
      mapper.createNewExpenseReport(reportBean);
      ExpenseReportRefJson responseContent = new ExpenseReportRefJson(reportBean, uri);
      return Response.status(201).entity(responseContent).build();
   }

   @Path("/{report-id}")
   @GET
   @Produces(MediaType.APPLICATION_JSON)
   public Response selectExpenseReport(@PathParam("report-id") int id, @Context UriInfo uri, @BeanParam ExpenseReportMapper mapper) {
      ExpenseReportBean expenseReport = mapper.getExpenseReportById(id);
      return Response.status(200)
              .entity(new ExpenseReportRefJson(expenseReport, uri)).build();
   }

   @Path("/{report-id}")
   public ExpenseReportApi getExpensesApi(@PathParam("report-id") int id, @Context UriInfo uri, @BeanParam ExpenseReportMapper mapper) {
      ExpenseReportBean expenseReport = mapper.getExpenseReportById(id);
      return new ExpenseReportApi(expenseReport);
   }
}
