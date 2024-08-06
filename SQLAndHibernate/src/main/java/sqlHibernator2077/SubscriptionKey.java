package sqlHibernator2077;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class SubscriptionKey implements Serializable {
    @Column(name = "student_id")
    private int studentId;

    @Column(name = "course_id")
    private int courseId;

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

    // Constructors, Getters, Setters, equals(), hashCode()
}