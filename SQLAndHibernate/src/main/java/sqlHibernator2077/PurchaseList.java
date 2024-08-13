package sqlHibernator2077;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "purchaselist")
public class PurchaseList
{
    @Column(name = "price")
    private int price;

    @Temporal(TemporalType.DATE)
    @Column(name = "subscription_date")
    private Date subscriptionDate;

    @Column(name = "student_name")
    private String name;

    @Column(name = "course_name")
    private String courseName;

    public PurchaseList(int price, Date subscriptionDate, String name, String courseName) {
        this.price = price;
        this.subscriptionDate = subscriptionDate;
        this.name = name;
        this.courseName = courseName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
