package tw.pc.core;

public enum Policy {
    TotalLessThan500{
        @Override
        public boolean isValid(ExpenseReport expenseReport) {
            return expenseReport.getAmount()<500;
        }

        @Override
        public String getErrorMessage(ExpenseReport expenseReport) {
            return "总额不能大于500元";
        }

        @Override
        public boolean isValid(Expense expense) {
            return expense.getAmount()<500;
        }
    }, NoLimitation;

    public boolean isValid(ExpenseReport expenseReport) {
        return true;
    }

    public String getErrorMessage(ExpenseReport expenseReport) {
        return "";
    }

    public boolean isValid(Expense expense) {
        return true;
    }
}
