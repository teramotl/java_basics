public class Manager implements Employee {
    private final double fixedSalary;
    private final double bonusMultiplier;

    public Manager() {
        this.fixedSalary = 60_000.0;
        this.bonusMultiplier = 0.05;
    }

    @Override
    public double getMonthSalary() {
        double companyIncome = Math.random() * (140_000 - 115_000) + 115_000;
        return fixedSalary + companyIncome * bonusMultiplier;
    }
}
