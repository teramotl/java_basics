package sqlHibernator2077;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {
    public static void main(String[] args) {
        // Create SessionFactory
        try (SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .addAnnotatedClass(Subscription.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Teacher.class)
                .addAnnotatedClass(CourseEnrollment.class)
                .addAnnotatedClass(TeacherAssignment.class)
                .buildSessionFactory();

             // Open session
             Session session = sessionFactory.openSession()) {

            session.beginTransaction();

            // This line ensures Hibernate validates the mappings and creates the schema
            session.createQuery("FROM Student").list();

            session.getTransaction().commit();
            System.out.println("Database schema created successfully.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
