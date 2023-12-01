import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

class Company {
    private List<Employee> employees = new ArrayList<>();
    private static double income = Math.random() * (20_000_000 - 5_000_000) + 5_000_000;

    public void hire(Employee employee) {
        employees.add(employee);
    }

    public void hireAll(Collection<Employee> newEmployees) {
        employees.addAll(newEmployees);
    }

    public void fire(Employee employee) {
        employees.remove(employee);
    }

    public static double getIncome() {
        return income;
    }

    public List<Employee> getTopSalaryStaff(int count) {
        List<Employee> sortedList = new ArrayList<>(employees);
        sortedList.sort(Comparator.comparingDouble(Employee::getMonthSalary).reversed());
        return sortedList.subList(0, Math.min(count, sortedList.size()));
    }

    public List<Employee> getLowestSalaryStaff(int count) {
        List<Employee> sortedList = new ArrayList<>(employees);
        sortedList.sort(Comparator.comparingDouble(Employee::getMonthSalary));
        return sortedList.subList(0, Math.min(count, sortedList.size()));
    }
}