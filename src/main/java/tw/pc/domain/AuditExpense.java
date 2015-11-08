package tw.pc.domain;

public class AuditExpense {
    private int id;
    private  double approvedAmount;

    private  String expenseOrigin;
    private  String policy;

    public AuditExpense(double approvedAmount, String expenseOrigin, String policy) {

        this.approvedAmount = approvedAmount;
        this.expenseOrigin = expenseOrigin;
        this.policy = policy;
    }

    public int getId() {
        return id;
    }

    public double getApprovedAmount() {
        return approvedAmount;
    }

    public String getExpenseOrigin() {
        return expenseOrigin;
    }

    public String getPolicy() {
        return policy;
    }
}
