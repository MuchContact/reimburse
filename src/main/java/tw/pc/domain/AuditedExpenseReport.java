package tw.pc.domain;

public class AuditedExpenseReport {
    private int id=0;
    private String expenseReport;
    private String referencedPolicy;
    private double approvedAmount;
    private String createTime;

    public AuditedExpenseReport(String expenseReport) {
        this.expenseReport = expenseReport;
    }

    public AuditedExpenseReport(String expenseReport, String referencedPolicy, double approvedAmount) {
        this.expenseReport = expenseReport;
        this.referencedPolicy = referencedPolicy;
        this.approvedAmount = approvedAmount;
    }

    public AuditedExpenseReport(Double approvedAmount, String expenseReport, String referencedPolicy) {
        this.approvedAmount = approvedAmount;
        this.expenseReport = expenseReport;
        this.referencedPolicy = referencedPolicy;
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

    public double getApprovedAmount() {
        return approvedAmount;
    }

    public void setExpenseReport(String expenseReport) {
        this.expenseReport = expenseReport;
    }

    public void setReferencedPolicy(String referencedPolicy) {
        this.referencedPolicy = referencedPolicy;
    }

    public void setApprovedAmount(int approvedAmount) {
        this.approvedAmount = approvedAmount;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
