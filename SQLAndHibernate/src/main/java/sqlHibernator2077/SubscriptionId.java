package sqlHibernator2077;

import java.io.Serializable;
import java.util.Objects;

public class SubscriptionId implements Serializable {
    private int studentId;
    private int courseId;

    // Default constructor
    public SubscriptionId() {}

    // Parameterized constructor
    public SubscriptionId(int studentId, int courseId) {
        this.studentId = studentId;
        this.courseId = courseId;
    }

    // Getters and Setters
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

    // Equals and HashCode
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        SubscriptionId that = (SubscriptionId) o;
//        return studentId == that.studentId &&
//                courseId == that.courseId;
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(studentId, courseId);
//    }
}
