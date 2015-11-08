package tw.pc.domain;

public class ExpenseReportBean {
    int id;
    private String name;
    private String number;
    private String identity;
    private String time;

    public ExpenseReportBean() {
    }

    public ExpenseReportBean(String name ) {

        this.name = name;
    }

    public ExpenseReportBean(String name, String number, String identity, String time) {
        this.name = name;
        this.number = number;
        this.identity = identity;
        this.time = time;
    }

    public String getNumber() {
        return number;
    }

    public String getIdentity() {
        return identity;
    }

    public String getTime() {
        return time;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
