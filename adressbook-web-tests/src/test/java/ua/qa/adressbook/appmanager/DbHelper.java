package ua.qa.adressbook.appmanager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ua.qa.adressbook.model.ContactData;
import ua.qa.adressbook.model.Contacts;
import ua.qa.adressbook.model.GroupData;
import ua.qa.adressbook.model.Groups;

import java.util.List;

/**
 * Created by polkota on 03.08.2016.
 */
public class DbHelper {

    private final SessionFactory sessionFactory;

    public DbHelper() {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();

        sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();

    }

    public Groups groups() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<GroupData> result = session.createQuery("from GroupData").list();
        session.getTransaction().commit();
        session.close();
        return new Groups(result);
    }

    public Contacts contacts() {
        Session session2 = sessionFactory.openSession();
        session2.beginTransaction();
        List<ContactData> result = session2.createQuery( "from ContactData where deprecated = '0000-00-00'").list();
        session2.getTransaction().commit();
        session2.close();
        return new Contacts(result);
    }
}


