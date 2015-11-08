package tw.pc.domain.json;

import tw.pc.domain.ExpenseReportBean;

import javax.ws.rs.core.UriInfo;

public class ExpenseReportRefJson {
    private ExpenseReportBean reportBean;
    private UriInfo uri;

    public ExpenseReportRefJson(ExpenseReportBean reportBean, UriInfo uri) {
        this.reportBean = reportBean;
        this.uri = uri;
    }

    public String getName() {
        return reportBean.getName();
    }

    public int getId() {
        return reportBean.getId();
    }

    public String getUri() {
        return uri.getAbsolutePath() + "/" + reportBean.getId();
    }

    public String getIdentity() {
        return reportBean.getIdentity();
    }

    public String getTime() {
        return reportBean.getTime();
    }

    public String getNumber() {
        return reportBean.getNumber();
    }
}
