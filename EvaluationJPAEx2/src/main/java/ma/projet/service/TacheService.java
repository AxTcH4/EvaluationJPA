
package ma.projet.service;

import ma.projet.classes.Tache;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;

import java.time.LocalDate;
import java.util.List;

public class TacheService extends BaseService<Tache> {

    public TacheService() {
        super(Tache.class);
    }

    // Requête nommée : prix supérieur à 1000 DH
    public List<Tache> findPrixSuperieur1000() {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            return session.createNamedQuery(
                    "Tache.findPrixSuperieur1000",
                    Tache.class
            ).list();
        }
    }

    // Tâches réalisées entre deux dates
    public List<Tache> findTachesRealiseesEntreDates(
            LocalDate dateDebut, LocalDate dateFin) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            return session.createQuery(
                            "SELECT t FROM Tache t " +
                                    "WHERE t.dateDebutReelle >= :d1 " +
                                    "AND t.dateFinReelle <= :d2 " +
                                    "AND t.dateDebutReelle IS NOT NULL " +
                                    "AND t.dateFinReelle IS NOT NULL",
                            Tache.class
                    )
                    .setParameter("d1", dateDebut)
                    .setParameter("d2", dateFin)
                    .list();
        }
    }

    public void afficherTaches(List<Tache> taches) {

        System.out.printf("%-5s %-20s %-10s %-15s %-15s%n",
                "ID", "Nom", "Prix", "Date début", "Date fin");

        for (Tache t : taches) {
            System.out.printf("%-5d %-20s %-10.2f %-15s %-15s%n",
                    t.getId(),
                    t.getNom(),
                    t.getPrix(),
                    t.getDateDebutReelle(),
                    t.getDateFinReelle());
        }
    }
}
