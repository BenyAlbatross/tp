package internity.core;

public class Internship {
    private String company;
    private String role;
    private Date deadline;
    private int pay;

    public Internship(String company, String role, Date deadline, int pay) {
        this.company = company;
        this.role = role;
        this.deadline = deadline;
        this.pay = pay;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public int getPay() {
        return pay;
    }

    public void setPay(int pay) {
        this.pay = pay;
    }
}
