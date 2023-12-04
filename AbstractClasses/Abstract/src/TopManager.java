import java.util.Random;

class TopManager implements Employee {
    private double fixedSalary;
    private double bonusMultiplier = 1.5;
    private double companyIncomeThreshold = 10_000_000;
    private Company company;

    public TopManager() {
        this.fixedSalary = Math.random() * (230_000 - 160_000) + 160_000;
    }

    @Override
    public double getMonthSalary() {
        if(company != null && company.getIncome() > companyIncomeThreshold){
            return fixedSalary * bonusMultiplier;
        } else {
            return fixedSalary;
        }
    }
    /*public double getMonthSalary() {
        double bonus = (company != null && company.getIncome() > companyIncomeThreshold) ? fixedSalary * bonusMultiplier : 0;
        return fixedSalary + bonus;
    }*/

    @Override
    public void setCompany(Company company) {
        this.company = company;
    }
}