import java.util.Random;

class TopManager implements Employee {
    private double fixedSalary;
    private double bonusMultiplier = 1.5;
    private double companyIncomeThreshold = 10_000_000;

    public TopManager() {
        this.fixedSalary = Math.random() * (230_000 - 160_000) + 160_000;
    }

    @Override
    public double getMonthSalary() {
        return fixedSalary + (Company.getIncome() > companyIncomeThreshold ? fixedSalary * bonusMultiplier : 0);
    }
}