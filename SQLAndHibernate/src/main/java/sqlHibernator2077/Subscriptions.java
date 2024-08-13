package sqlHibernator2077;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "subscriptions")
public class Subscriptions
{
    @Id
    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Students students;

    @Id
    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Courses courses;

    @Column(name = "subscription_date")
    @Temporal(TemporalType.DATE)
    private Date subscriptionDate;

    public Subscriptions() {}

    public Subscriptions(Students students, Courses courses, Date subscriptionDate) {
        this.students = students;
        this.courses = courses;
        this.subscriptionDate = subscriptionDate;
    }

    public Students getStudents() {
        return students;
    }

    public void setStudents(Students students) {
        this.students = students;
    }

    public Courses getCourses() {
        return courses;
    }

    public void setCourses(Courses courses) {
        this.courses = courses;
    }

    public Date getSubscriptionDate() {
        return subscriptionDate;
    }

    public void setSubscriptionDate(Date subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }
}
