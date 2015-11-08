package tw.pc.api;

import tw.pc.mapper.ExpenseMapper;
import tw.pc.mapper.ExpenseReportMapper;

import javax.inject.Inject;
import javax.ws.rs.Path;

@Path("/employees")
public class EmployeesApi {
    @Inject
    ExpenseReportMapper reportMapper;

    @Inject
    ExpenseMapper expenseMapper;

    @Path("/{id}")
    public EmployeeApi getEmployee(){
        return new EmployeeApi();
    }
}
