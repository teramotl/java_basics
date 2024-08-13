package sqlHibernator2077;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "purchaseList")
@IdClass(PurchaseListId.class)
public class PurchaseList {

    @Id
    @Column(name = "student_name", length = 100)
    private String studentName;

    @Id
    @Column(name = "course_name", length = 100)
    private String courseName;

    @Column(name = "price")
    private int price;

    @Column(name = "subscription_date")
    @Temporal(TemporalType.DATE)
    private Date subscriptionDate;

    // Constructors
    public PurchaseList() {}

    public PurchaseList(String studentName, String courseName, int price, Date subscriptionDate) {
        this.studentName = studentName;
        this.courseName = courseName;
        this.price = price;
        this.subscriptionDate = subscriptionDate;
    }

    // Getters and Setters
    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Date getSubscriptionDate() {
        return subscriptionDate;
    }

    public void setSubscriptionDate(Date subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }
}
