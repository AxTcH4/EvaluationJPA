
package ma.projet.service;

import ma.projet.classes.Projet;
import ma.projet.classes.Tache;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class ProjetService extends BaseService<Projet> {

    public ProjetService() {
        super(Projet.class);
    }

    // Liste des tâches planifiées pour un projet
    public List<Tache> getTachesPlanifiees(Projet projet) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            return session.createQuery(
                            "SELECT t FROM Tache t WHERE t.projet.id = :id",
                            Tache.class
                    )
                    .setParameter("id", projet.getId())
                    .list();
        }
    }

    // Liste des tâches réalisées avec les dates réelles
    public List<Tache> getTachesRealisees(Projet projet) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            return session.createQuery(
                            "SELECT t FROM Tache t " +
                                    "WHERE t.projet.id = :id " +
                                    "AND t.dateDebutReelle IS NOT NULL " +
                                    "AND t.dateFinReelle IS NOT NULL",
                            Tache.class
                    )
                    .setParameter("id", projet.getId())
                    .list();
        }
    }

    // Affichage des tâches réalisées
    public void afficherTachesRealisees(Projet projet) {

        List<Tache> taches = getTachesRealisees(projet);

        System.out.println("Projet : " + projet.getId()
                + "   Nom : " + projet.getNom()
                + "   Date début : " + projet.getDateDebut());

        System.out.println("Liste des tâches:");

        System.out.printf("%-5s %-20s %-20s %-20s%n",
                "Num", "Nom", "Date Début Réelle", "Date Fin Réelle");

        for (Tache t : taches) {
            System.out.printf("%-5d %-20s %-20s %-20s%n",
                    t.getId(),
                    t.getNom(),
                    t.getDateDebutReelle(),
                    t.getDateFinReelle());
        }
    }
}
