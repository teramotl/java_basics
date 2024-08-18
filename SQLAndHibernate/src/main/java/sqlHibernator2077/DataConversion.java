package sqlHibernator2077;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class DataConversion {

    public static void convertData() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Retrieve all PurchaseList records
            List<PurchaseList> purchaseList = session.createQuery("from PurchaseList", PurchaseList.class).list();

            // Iterate through PurchaseList records
            for (PurchaseList purchase : purchaseList) {
                // Get studentId and courseId by querying Students and Courses
                Integer studentId = (Integer) session.createQuery("select id from Students where name = :name")
                        .setParameter("name", purchase.getStudentName())
                        .uniqueResult();

                Integer courseId = (Integer) session.createQuery("select id from Courses where name = :name")
                        .setParameter("name", purchase.getCourseName())
                        .uniqueResult();

                // Create LinkedPurchaseList record and save it
                if (studentId != null && courseId != null) {
                    LinkedPurchaseList linked = new LinkedPurchaseList(studentId, courseId);
                    Transaction tx = session.beginTransaction();
                    session.save(linked);
                    tx.commit();
                }
            }
        }
    }
}
