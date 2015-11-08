package tw.pc.api;

import tw.pc.domain.Expense;
import tw.pc.domain.ExpenseReportBean;
import tw.pc.domain.json.ExpenseRefJson;
import tw.pc.mapper.ExpenseMapper;
import tw.pc.mapper.ExpenseReportMapper;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

public class ExpenseReportApi {

    private ExpenseReportBean report;

    public ExpenseReportApi(ExpenseReportBean report) {

        this.report = report;
    }

    @Path("/expenses")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response addExpense(@FormParam("title") String title,
                               @FormParam("fee") double fee,
                               @FormParam("time") String time,
                               @BeanParam ExpenseReportMapper reportMapper,
                               @BeanParam ExpenseMapper mapper,
                               @Context UriInfo uri){

        Expense expense = new Expense(title, fee, time);
        mapper.createExpense(expense);
        reportMapper.addExpense(report, expense);
        ExpenseRefJson expenseRefJson = new ExpenseRefJson(expense, uri);
        return Response.status(201).entity(expenseRefJson).build();
    }

    @Path("/expenses")
    public ExpensesApi getExpense(){
        return new ExpensesApi();
    }
}
