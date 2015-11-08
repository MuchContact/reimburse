package tw.pc.api;

import tw.pc.domain.Expense;
import tw.pc.domain.ExpenseReportBean;
import tw.pc.domain.json.ExpenseRefJson;
import tw.pc.mapper.ExpenseMapper;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

public class ExpensesApi {
    private ExpenseReportBean expenseReport;

    public ExpensesApi(ExpenseReportBean expenseReport) {
        this.expenseReport = expenseReport;
    }

    public ExpensesApi() {

    }

    @Path("/{expense-id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getExpense(@PathParam("expense-id") int id,
                               @BeanParam ExpenseMapper mapper,
                               @Context UriInfo uri){
        Expense expense = mapper.getExpenseById(id);
        ExpenseRefJson expenseRefJson = new ExpenseRefJson(expense, uri);
        return Response.status(201).entity(expenseRefJson).build();
    }
}
