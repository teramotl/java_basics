public class TopManager implements Employee {
    private final double fixedSalary;
    private final double bonusMultiplier;
    private static final double HIGH_INCOME_THRESHOLD = 10_000_000.0;

    public TopManager() {
        this.fixedSalary = 80_000.0;
        this.bonusMultiplier = 1.5;
    }

    @Override
    public double getMonthSalary() {
        double companyIncome = Math.random() * (20_000_000 - 5_000_000) + 5_000_000;
        return fixedSalary + (companyIncome > HIGH_INCOME_THRESHOLD ? fixedSalary * bonusMultiplier : 0);
    }
}
