package sqlHibernator2077;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SchemaCreationTest
{
    public static void main(String[] args)
    {
        //config to create object and load xml file
        Configuration configuration = new Configuration().configure();


        //add class to config
        configuration.addAnnotatedClass(Students.class);
        configuration.addAnnotatedClass(Teacher.class);
        configuration.addAnnotatedClass(Courses.class);
        configuration.addAnnotatedClass(Subscriptions.class);
        configuration.addAnnotatedClass(PurchaseList.class);
        //build session factory from config and close it to release resources
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        sessionFactory.close();

        System.out.println("SUCCESS");
    }
}
