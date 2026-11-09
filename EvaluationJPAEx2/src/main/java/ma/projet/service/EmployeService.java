
package ma.projet.service;

import ma.projet.classes.Employe;
import ma.projet.classes.Projet;
import ma.projet.classes.Tache;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class EmployeService extends BaseService<Employe> {

    public EmployeService() {
        super(Employe.class);
    }

    // Liste des tâches réalisées par un employé
    public List<Tache> getTachesRealisees(Employe employe) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            return session.createQuery(
                            "SELECT et.tache FROM EmployeTache et " +
                                    "WHERE et.employe.id = :id " +
                                    "AND et.tache.dateFinReelle IS NOT NULL",
                            Tache.class
                    )
                    .setParameter("id", employe.getId())
                    .list();
        }
    }

    // Liste des projets gérés par un employé
    public List<Projet> getProjetsGeres(Employe employe) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            return session.createQuery(
                            "SELECT p FROM Projet p " +
                                    "WHERE p.responsable.id = :id",
                            Projet.class
                    )
                    .setParameter("id", employe.getId())
                    .list();
        }
    }

    public void afficherTachesRealisees(Employe employe) {

        List<Tache> taches = getTachesRealisees(employe);

        System.out.println("Tâches réalisées par : "
                + employe.getNom() + " " + employe.getPrenom());

        for (Tache t : taches) {
            System.out.println(
                    t.getId() + " - " + t.getNom()
                            + " - " + t.getDateDebutReelle()
                            + " - " + t.getDateFinReelle()
            );
        }
    }

    public void afficherProjetsGeres(Employe employe) {

        List<Projet> projets = getProjetsGeres(employe);

        System.out.println("Projets gérés par : "
                + employe.getNom() + " " + employe.getPrenom());

        for (Projet p : projets) {
            System.out.println(p.getId() + " - " + p.getNom());
        }
    }
}
