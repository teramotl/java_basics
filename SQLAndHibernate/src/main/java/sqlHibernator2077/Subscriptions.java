package sqlHibernator2077;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "subscriptions")
@IdClass(SubscriptionId.class)
public class Subscriptions {

    @Id
    @Column(name = "student_id")
    private int studentId;

    @Id
    @Column(name = "course_id")
    private int courseId;

    @Column(name = "subscription_date")
    @Temporal(TemporalType.DATE)
    private Date subscriptionDate;

    // Constructors, getters, and setters
    public Subscriptions() {}

    public Subscriptions(int studentId, int courseId, Date subscriptionDate) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.subscriptionDate = subscriptionDate;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public Date getSubscriptionDate() {
        return subscriptionDate;
    }

    public void setSubscriptionDate(Date subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }
}
