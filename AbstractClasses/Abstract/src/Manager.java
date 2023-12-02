import java.util.Random;

class Manager implements Employee {
    private double fixedSalary;
    private double bonusPercentage = 0.05;
    private double earnedForCompany;
    private Company company;

    public Manager() {
        this.fixedSalary = Math.random() * (150_000 - 100_000) + 100_000;
        this.earnedForCompany = Math.random() * (140_000 - 115_000) + 115_000;
    }

    @Override
    public double getMonthSalary() {
        return fixedSalary + earnedForCompany * bonusPercentage;
    }
    public void setCompany(Company company) {
        this.company = company;
    }
}
