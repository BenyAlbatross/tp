package internity.core;

public class Internship {
    private String company;
    private Date deadline;
    private int salary;
    private boolean isCompleted;

    public Internship(String company, Date deadline, int salary) {
        this.company = company;
        this.deadline = deadline;
        this.salary = salary;
        isCompleted = false;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }
}
