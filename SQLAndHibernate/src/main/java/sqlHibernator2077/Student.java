package sqlHibernator2077;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "Students")
public class Student {
    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
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

    public Set<Subscription> getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(Set<Subscription> subscriptions) {
        this.subscriptions = subscriptions;
    }

    public Set<CourseEnrollment> getCourseEnrollments() {
        return courseEnrollments;
    }

    public void setCourseEnrollments(Set<CourseEnrollment> courseEnrollments) {
        this.courseEnrollments = courseEnrollments;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int studentId;

    private String name;
    private int age;
    private Date registrationDate;

    @OneToMany(mappedBy = "student")
    private Set<Subscription> subscriptions;

    @OneToMany(mappedBy = "student")
    private Set<CourseEnrollment> courseEnrollments;

    // Getters and Setters
}