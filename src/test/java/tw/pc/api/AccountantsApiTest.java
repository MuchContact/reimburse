package tw.pc.api;

import org.apache.ibatis.session.SqlSession;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import tw.pc.mapper.AuditExpenseMapper;
import tw.pc.mapper.AuditedExpenseReportMapper;
import tw.pc.mapper.MybatisConnectionFactory;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;

import static org.hamcrest.CoreMatchers.endsWith;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class AccountantsApiTest extends JerseyTest{
    @Mock
    AuditedExpenseReportMapper auditedExpenseReportMapper;

    @Mock
    AuditExpenseMapper auditExpenseMapper;

    @Override
    protected Application configure() {
        SqlSession sqlSession = MybatisConnectionFactory.getSqlSessionFactory().openSession();

        auditedExpenseReportMapper = sqlSession.getMapper(AuditedExpenseReportMapper.class);

        return new ResourceConfig().packages("tw.pc.api").register(new AbstractBinder() {

            @Override
            protected void configure() {
                bind(auditedExpenseReportMapper).to(AuditedExpenseReportMapper.class);
                bind(auditExpenseMapper).to(AuditExpenseMapper.class);
            }
        });
    }

    @Test
    public void should_create_new_audited_expense_report() throws Exception {
        Form form  = new Form();
        form.param("expenseReportURI", "employees/1/expense reports/1");
        Response response = target("accountants/1/audited expense reports/")
                .request().post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE));
        assertThat(response.getStatus(), is(201));
        Map result = response.readEntity(Map.class);
        assertThat((String)result.get("uri"), endsWith("accountants/1/audited%20expense%20reports/"+result.get("id")));
    }

    @Test
    public void should_create_audit_expense() throws Exception {
        Form form  = new Form();
        form.param("expenseURI", "employees/1/expense reports/1/expenses/1");
        form.param("approvedAmount", "200");
        form.param("policyURI", "");
        Response response = target("accountants/1/audited expense reports/1/audit expenses")
                .request().post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE));
        assertThat(response.getStatus(), is(201));
        Map result = response.readEntity(Map.class);
        assertThat((String)result.get("uri"), endsWith("accountants/1/audited%20expense%20reports/1/audit%20expenses/"+result.get("id")));

    }

    @Test
    public void should_add_audit_expense_into_audit_expense_report() throws Exception {
        Form form  = new Form();
        form.param("expenseURI", "employees/1/expense reports/1/expenses/1");
        form.param("approvedAmount", "200");
        form.param("policyURI", "");
        Response response = target("accountants/1/audited expense reports/1/audit expenses")
                .request().post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE));
        assertThat(response.getStatus(), is(201));

        verify(auditedExpenseReportMapper, times(1)).addExpense(any(), any());
    }


}
