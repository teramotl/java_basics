import java.util.*;
import java.util.stream.Collectors;

public class Company {
    private final List<Employee> employees = new ArrayList<>();

    public void hire(Employee employee) {
        employees.add(employee);
    }

    public void hireAll(Collection<Employee> newEmployees) {
        employees.addAll(newEmployees);
    }

    public void fire(Employee employee) {
        employees.remove(employee);
    }

    public double getIncome() {
        return employees.stream().mapToDouble(Employee::getMonthSalary).sum();
    }

    public List<Employee> getTopSalaryStaff(int count) {
        return getSortedEmployees(Comparator.comparingDouble(Employee::getMonthSalary).reversed(), count);
    }

    public List<Employee> getLowestSalaryStaff(int count) {
        return getSortedEmployees(Comparator.comparingDouble(Employee::getMonthSalary), count);
    }

    private List<Employee> getSortedEmployees(Comparator<Employee> comparator, int count) {
        return employees.stream()
                .sorted(comparator)
                .limit(count)
                .collect(Collectors.toList());
    }
    public List<Employee> getEmployees() {
        return new ArrayList<>(employees);
    }
}
