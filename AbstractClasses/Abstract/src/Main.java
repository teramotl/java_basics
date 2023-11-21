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

        printSalaries("Top Salaries", company.getTopSalaryStaff(15));
        printSalaries("Lowest Salaries", company.getLowestSalaryStaff(30));

        int numEmployeesToFire = company.getEmployees().size() / 2;
        for (int i = 0; i < numEmployeesToFire; i++) {
            company.fire(company.getEmployees().get(i));
        }

        printSalaries("Top Salaries After Firing", company.getTopSalaryStaff(15));
        printSalaries("Lowest Salaries After Firing", company.getLowestSalaryStaff(30));
    }

    private static void printSalaries(String title, List<Employee> employees) {
        System.out.println(title + ":");
        for (Employee employee : employees) {
            System.out.printf("%.2f руб.%n", employee.getMonthSalary());
        }
        System.out.println();
    }
}