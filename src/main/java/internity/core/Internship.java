package internity.core;

import java.util.Comparator;

public class Internship implements Comparable<Internship> {
    public static Comparator<Internship> SortByDeadline;
    private String company;
    private String role;
    private Date deadline;
    private int pay;
    private String status;

    public Internship(String company, String role, Date deadline, int pay) {
        this.company = company;
        this.role = role;
        this.deadline = deadline;
        this.pay = pay;
        this.status = "Pending";
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public int compareTo(Internship o) {
        return this.deadline.compareTo(o.getDeadline());
    }

    public static class SortByDeadline implements Comparator<Internship> {
        public int compare(Internship a, Internship b) {
            return a.getDeadline().compareTo(b.getDeadline());
        }
    }
}
