package tw.pc.api;

import org.apache.ibatis.session.SqlSession;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import tw.pc.domain.Expense;
import tw.pc.domain.ExpenseReportBean;
import tw.pc.mapper.ExpenseMapper;
import tw.pc.mapper.ExpenseReportMapper;
import tw.pc.mapper.MybatisConnectionFactory;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ExpenseAPITest extends JerseyTest{

    @Mock
    ExpenseReportMapper expenseReportMapper;
    @Mock
    ExpenseMapper expenseMapper;

    private ArgumentCaptor<ExpenseReportBean> expenseReportArgumentCaptor;

    @Override
    protected Application configure() {
        SqlSession sqlSession = MybatisConnectionFactory.getSqlSessionFactory().openSession();
        expenseReportMapper = sqlSession.getMapper(ExpenseReportMapper.class);
        expenseMapper = sqlSession.getMapper(ExpenseMapper.class);
        return new ResourceConfig().packages("tw.pc.api").register(new AbstractBinder() {
            @Override
            protected void configure() {
                bind(expenseReportMapper).to(ExpenseReportMapper.class);
                bind(expenseMapper).to(ExpenseMapper.class);
            }
        });
    }

    @Test
    public void should_create_new_expense_claim() throws Exception {

        Form form = new Form();
        form.param("name", "travel to beijing");
        Response response = target("employees/1/expense reports")
                .request()
                .post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE));

//        verify(expenseReportMapper).createNewExpenseReport(expenseReportArgumentCaptor.capture());
//        assertThat((String)expenseReportArgumentCaptor.getValue().getName(), endsWith("travel to beijing"));

        Map map = response.readEntity(Map.class);

        assertThat(response.getStatus(), is(201));
        assertThat((String)map.get("name"), CoreMatchers.endsWith("travel to beijing"));
        assertThat((String)map.get("uri"), containsString("employees/1/expense%20reports/"+map.get("id")));

    }

    @Test
    public void should_get_expense_report_detail() throws Exception {
        ExpenseReportBean report = new ExpenseReportBean("travel to beijing", "e-1234",
                "26372383298778787", "2015-09-21 17:00:00");
        when(expenseReportMapper.getExpenseReportById(eq(1))).thenReturn(report);
        Response response = target("employees/1/expense reports/1").request().get();

        Map expenseReportRefJson = response.readEntity(Map.class);

        assertThat(response.getStatus(), is(200));
        assertThat(expenseReportRefJson.get("name"), is("travel to beijing"));
        assertThat(expenseReportRefJson.get("identity"), is("26372383298778787"));
    }

    @Test
    public void should_append_expense_to_expense_report() throws Exception {
        Form form = new Form();
        form.param("title", "taxi from railway station to beijing office");
        form.param("fee", "120");
        form.param("time", "2015-10-10 10:00:00");

        Response response = target("employees/1/expense reports/1/expenses")
                .request()
                .post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE));
        assertThat(response.getStatus(), is(201));
        Map expenseRefJson = response.readEntity(Map.class);
        String suffix = "employees/1/expense%20reports/1/expenses/" + expenseRefJson.get("id");
        assertThat((String)expenseRefJson.get("uri"), CoreMatchers.endsWith(suffix));
        assertThat(expenseRefJson.get("title"), is("taxi from railway station to beijing office"));
    }

    @Test
    public void should_get_expense_detail() throws Exception {

        Expense expense = new Expense("taxi from railway station to beijing office", 120);
        when(expenseMapper.getExpenseById(eq(1))).thenReturn(expense);
        Response response = target("employees/1/expense reports/1/expenses/1")
                .request().get();
        assertThat(response.getStatus(), is(201));
        Map result = response.readEntity(Map.class);
        assertThat(result.get("title"), is("taxi from railway station to beijing office"));
        assertThat(result.get("fee"), is(120d));

    }

}
