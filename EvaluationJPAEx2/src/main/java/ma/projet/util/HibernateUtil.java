package ma.projet.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static final SessionFactory factory =
            new Configuration()
                    .addProperties(loadProperties())
                    .addAnnotatedClass(ma.projet.classes.Projet.class)
                    .addAnnotatedClass(ma.projet.classes.Tache.class)
                    .addAnnotatedClass(ma.projet.classes.Employe.class)
                    .addAnnotatedClass(ma.projet.classes.EmployeTache.class)
                    .buildSessionFactory();

    private static java.util.Properties loadProperties() {
        java.util.Properties properties = new java.util.Properties();

        try {
            properties.load(
                    HibernateUtil.class.getClassLoader()
                            .getResourceAsStream("application.properties")
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return properties;
    }

    public static SessionFactory getSessionFactory() {
        return factory;
    }

    public static void shutdown() {
        factory.close();
    }
}
