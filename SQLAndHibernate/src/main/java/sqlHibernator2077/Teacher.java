package sqlHibernator2077;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Teachers")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int teacherId;

    private String teacherName;

    public Set<TeacherAssignment> getTeacherAssignments() {
        return teacherAssignments;
    }

    public void setTeacherAssignments(Set<TeacherAssignment> teacherAssignments) {
        this.teacherAssignments = teacherAssignments;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    @OneToMany(mappedBy = "teacher")
    private Set<TeacherAssignment> teacherAssignments;

    // Getters and Setters
}