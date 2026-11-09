package ma.projet.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ma.projet.classes.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class HibernateUtil {
    private static SessionFactory sessionFactory;

    private static SessionFactory buildSessionFactory() {
        try {
            Properties props = new Properties();
            try (InputStream is = HibernateUtil.class.getClassLoader()
                    .getResourceAsStream("application.properties")) {
                props.load(is);
            }

            StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                    .applySettings(props)
                    .build();

            return new MetadataSources(registry)
                    .addAnnotatedClass(Categorie.class)
                    .addAnnotatedClass(Produit.class)
                    .addAnnotatedClass(Commande.class)
                    .addAnnotatedClass(LigneCommandeProduit.class)
                    .buildMetadata()
                    .buildSessionFactory();
        } catch (IOException e) {
            throw new RuntimeException("Erreur chargement application.properties", e);
        }
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = buildSessionFactory();
        }
        return sessionFactory;
    }

    public static void shutdown() {
        getSessionFactory().close();
    }
}