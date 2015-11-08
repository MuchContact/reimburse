package tw.pc.domain.json;

import tw.pc.domain.Expense;

import javax.ws.rs.core.UriInfo;

public class ExpenseRefJson extends Expense{
    private final Expense expense;
    private final UriInfo uri;

    public ExpenseRefJson(Expense expense, UriInfo uri) {
        this.expense = expense;
        this.uri = uri;
    }

    public String getUri() {
        return uri.getAbsolutePath()+"/"+expense.getId();
    }

    @Override
    public String getName() {
        return expense.getName();
    }

    @Override
    public int getId() {
        return expense.getId();
    }

    @Override
    public double getPrice() {
        return expense.getPrice();
    }

    @Override
    public String getTitle() {
        return expense.getTitle();
    }

    @Override
    public double getFee() {
        return expense.getFee();
    }

    @Override
    public String getTime() {
        return expense.getTime();
    }
}
