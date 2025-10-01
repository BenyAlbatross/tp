package core;

import java.time.LocalDate;

public class Internship {
    private String company;
    private LocalDate deadline;
    private int salary;
    private boolean isCompleted;

    public Internship(String company, LocalDate deadline, int salary) {
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

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
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
