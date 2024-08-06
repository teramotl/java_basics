package sqlHibernator2077;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Courses")
public class Course {
    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Set<CourseEnrollment> getCourseEnrollments() {
        return courseEnrollments;
    }

    public void setCourseEnrollments(Set<CourseEnrollment> courseEnrollments) {
        this.courseEnrollments = courseEnrollments;
    }

    public Set<Subscription> getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(Set<Subscription> subscriptions) {
        this.subscriptions = subscriptions;
    }

    public Set<TeacherAssignment> getTeacherAssignments() {
        return teacherAssignments;
    }

    public void setTeacherAssignments(Set<TeacherAssignment> teacherAssignments) {
        this.teacherAssignments = teacherAssignments;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int courseId;

    private String courseName;

    @OneToMany(mappedBy = "course")
    private Set<CourseEnrollment> courseEnrollments;

    @OneToMany(mappedBy = "course")
    private Set<Subscription> subscriptions;

    @OneToMany(mappedBy = "course")
    private Set<TeacherAssignment> teacherAssignments;

    // Getters and Setters
}