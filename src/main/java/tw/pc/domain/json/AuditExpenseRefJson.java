package tw.pc.domain.json;

import tw.pc.domain.AuditExpense;

import javax.ws.rs.core.UriInfo;

public class AuditExpenseRefJson {
    private AuditExpense auditExpense;
    private UriInfo uri;

    public AuditExpenseRefJson(AuditExpense auditExpense, UriInfo uri) {
        this.auditExpense = auditExpense;
        this.uri = uri;
    }

    public int getId() {
        return auditExpense.getId();
    }

    public String getUri() {
        return uri.getAbsolutePath() + "/" + auditExpense.getId();
    }
}
