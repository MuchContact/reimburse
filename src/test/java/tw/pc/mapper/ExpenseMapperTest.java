package tw.pc.mapper;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import tw.pc.domain.Expense;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ExpenseMapperTest {
    @Test
    public void should_have_mapper_test() throws Exception {
        SqlSessionFactory sqlSessionFactory = MybatisConnectionFactory.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ExpenseMapper mapper = sqlSession.getMapper(ExpenseMapper.class);
        int affectRows = mapper.createExpense(new Expense("Li", 39.8d));
        assertThat(affectRows, is(1));
    }

}
