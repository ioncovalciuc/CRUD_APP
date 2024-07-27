package com.person.crud_app.fileTest;

import com.person.crud_app.model.Client;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class TestAddConnection {
    public static void main(String[] args) {
        try (SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Client.class)
                .buildSessionFactory()) {
            try (Session session = factory.getCurrentSession()) {
                Client client1 = new Client("Covalciuc Ion", "covalciuc@gmail.com", "+37368882480");
                session.beginTransaction();
                session.save(client1);
                session.getTransaction().commit();
            }
        }
        finally {
            System.out.println("First app save");
        }
    }
}
