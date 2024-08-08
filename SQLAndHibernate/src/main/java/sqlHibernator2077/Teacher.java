package sqlHibernator2077;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Teachers")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "teacher_id")
    private int teacherId;

    @Column(name = "teacher_name")
    private String teacherName;

    @OneToMany(mappedBy = "teacher")
    private Set<TeacherAssignment> teacherAssignments;

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public Set<TeacherAssignment> getTeacherAssignments() {
        return teacherAssignments;
    }

    public void setTeacherAssignments(Set<TeacherAssignment> teacherAssignments) {
        this.teacherAssignments = teacherAssignments;
    }
// Getters and Setters
}
