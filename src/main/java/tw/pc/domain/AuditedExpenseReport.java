package tw.pc.domain;

public class AuditedExpenseReport {
    private int id=0;
    private String expenseReport;
    private String referencedPolicy;
    private int approvedAmount;
    private String createTime;

    public AuditedExpenseReport(String expenseReport) {
        this.expenseReport = expenseReport;
    }

    public AuditedExpenseReport(String expenseReport, String referencedPolicy, int approvedAmount) {
        this.expenseReport = expenseReport;
        this.referencedPolicy = referencedPolicy;
        this.approvedAmount = approvedAmount;
    }

    public int getId() {
        return id;
    }

    public String getExpenseReport() {
        return expenseReport;
    }

    public String getCreateTime(){
        return createTime;
    }

    public String getReferencedPolicy() {
        return referencedPolicy;
    }

    public int getApprovedAmount() {
        return approvedAmount;
    }
}
