package tw.pc.domain;

public class Expense {
    private int id;
    private String title;
    private double fee;
    private String time;

    public Expense(String name, double price) {
        this.title = name;
        this.fee = price;
    }

    public Expense(String title, double fee, String time) {
        this.title = title;
        this.fee = fee;
        this.time = time;
    }

    public Expense() {
    }

    public String getName() {
        return title;
    }

    public double getPrice() {
        return fee;
    }

    public int getId() {

        return id;
    }

    public String getTitle() {
        return title;
    }

    public double getFee() {
        return fee;
    }

    public String getTime() {
        return time;
    }
}
