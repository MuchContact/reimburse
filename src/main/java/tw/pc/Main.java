package tw.pc;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionManager;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import tw.pc.mapper.*;

import javax.ws.rs.core.UriBuilder;
import java.io.IOException;
import java.net.URI;

public class Main {

    private static URI getBaseURI() {
        return UriBuilder.fromUri("http://0.0.0.0/").port(8080).build();
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        final HttpServer httpServer = startServer();

        while (true) {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                httpServer.shutdownNow();
            }
        }
    }

    private static HttpServer startServer() {
        SqlSessionFactory sqlSessionFactory = MybatisConnectionFactory.getSqlSessionFactory();
        final SqlSessionManager sqlSessionManager = SqlSessionManager.newInstance(sqlSessionFactory);

        ExpenseReportMapper expenseReportMapper = sqlSessionManager.getMapper(ExpenseReportMapper.class);
        ExpenseMapper expenseMapper = sqlSessionManager.getMapper(ExpenseMapper.class);
        AuditedExpenseReportMapper auditedExpenseReportMapper = sqlSessionManager.getMapper(AuditedExpenseReportMapper.class);
        AuditExpenseMapper auditExpenseMapper = sqlSessionManager.getMapper(AuditExpenseMapper.class);

        final ResourceConfig config = new ResourceConfig()
                .packages("tw.pc.api")
                .register(new AbstractBinder() {
                    @Override
                    protected void configure() {
                        bind(auditedExpenseReportMapper).to(AuditedExpenseReportMapper.class);
                        bind(auditExpenseMapper).to(AuditExpenseMapper.class);
                        bind(expenseReportMapper).to(ExpenseReportMapper.class);
                        bind(expenseMapper).to(ExpenseMapper.class);
                    }
                });
        return GrizzlyHttpServerFactory.createHttpServer(getBaseURI(), config);
    }

}
