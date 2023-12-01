import java.util.Random;

class Operator implements Employee {
    private double fixedSalary;

    public Operator() {
        this.fixedSalary = Math.random() * (120_000 - 100_000) + 100_000;
    }

    @Override
    public double getMonthSalary() {
        return fixedSalary;
    }
}