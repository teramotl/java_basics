import java.util.Random;

class Operator implements Employee {
    private double fixedSalary;
    private Company company;

    public Operator() {
        this.fixedSalary = Math.random() * (120_000 - 100_000) + 100_000;
    }

    @Override
    public double getMonthSalary() {
        return fixedSalary;
    }
    public void setCompany(Company company) {
        this.company = company;
    }
}