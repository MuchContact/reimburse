package tw.pc.mapper;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import tw.pc.domain.ExpenseReportBean;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ExpenseReportMapperTest {
    @Test
    public void should_create_new_report() throws Exception {
        SqlSession sqlSession = MybatisConnectionFactory.getSqlSessionFactory().openSession();
        ExpenseReportMapper mapper = sqlSession.getMapper(ExpenseReportMapper.class);
        ExpenseReportBean report = new ExpenseReportBean("Travel to Xi'an");
        int affectRows = mapper.createNewExpenseReport(report);
        ExpenseReportBean fetchedFromDb = mapper.getExpenseReportById(report.getId());
        assertThat(affectRows, is(1));
        assertThat(fetchedFromDb.getName(), is("Travel to Xi'an"));
    }

    @Test
    public void should() throws Exception {


    }
}
