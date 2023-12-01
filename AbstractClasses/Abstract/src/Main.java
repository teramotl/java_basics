import java.util.List;

public class Main {
    public static void main(String[] args) {
        Company company = new Company();

        for (int i = 0; i < 180; i++) {
            company.hire(new Operator());
        }

        for (int i = 0; i < 80; i++) {
            company.hire(new Manager());
        }

        for (int i = 0; i < 10; i++) {
            company.hire(new TopManager());
        }

        System.out.println("Top salaries:");
        printSalaries(company.getTopSalaryStaff(15));

        System.out.println("\nLowest salaries:");
        printSalaries(company.getLowestSalaryStaff(30));

        int employeesToFire = company.getTopSalaryStaff(company.getIncome() > 10_000_000 ? 50 : 25).size();
        for (int i = 0; i < employeesToFire; i++) {
            company.fire(company.getTopSalaryStaff(1).get(0));
        }

        System.out.println("\nTop salaries after firing:");
        printSalaries(company.getTopSalaryStaff(15));

        System.out.println("\nLowest salaries after firing:");
        printSalaries(company.getLowestSalaryStaff(30));
    }

    private static void printSalaries(List<Employee> employees) {
        for (Employee employee : employees) {
            System.out.printf("%.2f руб.%n", employee.getMonthSalary());
        }
    }
}