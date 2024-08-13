package sqlHibernator2077;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "students")
public class Students {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name", nullable = false, length = 45)
    private String name;
    @Column(name = "age")
    private int age;
    @Column(name = "registration_date")
    @Temporal(TemporalType.DATE)
    private Date registrationDate;

    public int getId() {
        return id;
    }
    public Students(String name, int age, Date registrationDate) {
        this.name = name;
        this.age = age;
        this.registrationDate = registrationDate;
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
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public Date getRegistrationDate() {
        return registrationDate;
    }
    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }
}
