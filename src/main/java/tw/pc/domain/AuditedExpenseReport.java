package tw.pc.domain;

public class AuditedExpenseReport {
    private int id=0;
    private String expenseReport;
    private String createTime;

    public AuditedExpenseReport(String expenseReport) {
        this.expenseReport = expenseReport;

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


}
