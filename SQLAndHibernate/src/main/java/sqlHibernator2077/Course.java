package sqlHibernator2077;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int courseId;

    @Column(name = "name")
    private String courseName;

    @Column(name = "duration")
    private int duration;

    @Column(name = "type")
    private String type;

    @Column(name = "description")
    private String description;

    @Column(name = "teacher_id")
    private int teacherId;

    @Column(name = "students_count")
    private int studentsCount;

    @Column(name = "price")
    private double price;

    @Column(name = "price_per_hour")
    private double pricePerHour;

    @OneToMany(mappedBy = "course")
    private Set<CourseEnrollment> courseEnrollments;

    @OneToMany(mappedBy = "course")
    private Set<Subscription> subscriptions;

    @OneToMany(mappedBy = "course")
    private Set<TeacherAssignment> teacherAssignments;

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

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public int getStudentsCount() {
        return studentsCount;
    }

    public void setStudentsCount(int studentsCount) {
        this.studentsCount = studentsCount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPricePerHour() {
        return pricePerHour;
    }

    public void setPricePerHour(double pricePerHour) {
        this.pricePerHour = pricePerHour;
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

    // Getters and Setters
}
