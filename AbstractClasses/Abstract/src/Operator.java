public class Operator implements Employee {

    private final double fixedSalary;

    public Operator() {
        this.fixedSalary = 50_000.0;
    }

    @Override
    public double getMonthSalary() {
        return fixedSalary;
    }

}
