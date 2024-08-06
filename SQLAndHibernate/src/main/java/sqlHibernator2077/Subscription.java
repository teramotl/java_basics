package sqlHibernator2077;

import javax.persistence.*;

@Entity
@Table(name = "Subscriptions")
public class Subscription {
    @EmbeddedId
    private SubscriptionKey id;

    @MapsId("studentId")
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @MapsId("courseId")
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    private String subscriptionType;

    public SubscriptionKey getId() {
        return id;
    }

    public void setId(SubscriptionKey id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getSubscriptionType() {
        return subscriptionType;
    }

    public void setSubscriptionType(String subscriptionType) {
        this.subscriptionType = subscriptionType;
    }

    // Getters and Setters
}

