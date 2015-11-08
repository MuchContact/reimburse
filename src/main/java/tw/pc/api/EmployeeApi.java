package tw.pc.api;

import javax.ws.rs.Path;

public class EmployeeApi {
    @Path("expense reports")
    public ExpenseReportsApi getExpenseReports(){
        return new ExpenseReportsApi();
    }
}
