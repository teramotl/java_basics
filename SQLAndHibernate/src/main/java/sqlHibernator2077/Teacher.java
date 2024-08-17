package sqlHibernator2077;

import javax.persistence.*;

@Entity
@Table(name = "teachers")
public class Teacher
{
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "name", nullable = false, length = 45)
    private String name;

    @Column(name = "salary")
    private int salary;

    @Column(name = "age")
    private int age;

    public Teacher(String name, int salary, int age) {
        this.name = name;
        this.salary = salary;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
