package tw.pc.domain.json;

import tw.pc.domain.AuditedExpenseReport;

import javax.ws.rs.core.UriInfo;

public class AuditedExpenseReportRefJson {
    private AuditedExpenseReport auditedExpenseReport;
    private UriInfo uri;

    public AuditedExpenseReportRefJson(AuditedExpenseReport auditedExpenseReport, UriInfo uri) {

        this.auditedExpenseReport = auditedExpenseReport;
        this.uri = uri;
    }

    public String getUri() {
        return uri.getAbsolutePath() + "" + auditedExpenseReport.getId();
    }

    public int getId(){
        return auditedExpenseReport.getId();
    }
}
