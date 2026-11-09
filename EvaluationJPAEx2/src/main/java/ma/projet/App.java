
package ma.projet;

import ma.projet.classes.*;
import ma.projet.service.*;
import ma.projet.util.HibernateUtil;

import java.time.LocalDate;

public class App {

    public static void main(String[] args) {

        ProjetService ps = new ProjetService();
        TacheService ts = new TacheService();
        EmployeService es = new EmployeService();
        EmployeTacheService ets = new EmployeTacheService();


        Employe e1 = new Employe(
                "SAFI", "Ahmed", "ahmed@gmail.com");

        Employe e2 = new Employe(
                "RAMI", "Karim", "karim@gmail.com");

        es.create(e1);
        es.create(e2);



        Projet p1 = new Projet(
                "Gestion de stock",
                LocalDate.of(2013, 1, 14),
                LocalDate.of(2013, 12, 30),
                e1
        );

        ps.create(p1);



        Tache t1 = new Tache(
                "Analyse",
                1500,
                LocalDate.of(2013, 2, 1),
                LocalDate.of(2013, 2, 28),
                LocalDate.of(2013, 2, 10),
                LocalDate.of(2013, 2, 20),
                p1
        );

        Tache t2 = new Tache(
                "Conception",
                2000,
                LocalDate.of(2013, 3, 1),
                LocalDate.of(2013, 3, 30),
                LocalDate.of(2013, 3, 10),
                LocalDate.of(2013, 3, 15),
                p1
        );

        Tache t3 = new Tache(
                "Développement",
                500,
                LocalDate.of(2013, 4, 1),
                LocalDate.of(2013, 4, 30),
                LocalDate.of(2013, 4, 10),
                LocalDate.of(2013, 4, 25),
                p1
        );

        Tache t4 = new Tache(
                "Tests",
                1200,
                LocalDate.of(2013, 5, 1),
                LocalDate.of(2013, 5, 30),
                null,
                null,
                p1
        );

        ts.create(t1);
        ts.create(t2);
        ts.create(t3);
        ts.create(t4);



        ets.create(new EmployeTache(e1, t1, 30));
        ets.create(new EmployeTache(e1, t2, 40));
        ets.create(new EmployeTache(e2, t3, 50));
        ets.create(new EmployeTache(e2, t4, 20));


        System.out.println("\n===== TACHES PLANIFIEES =====");

        for (Tache t : ps.getTachesPlanifiees(p1)) {
            System.out.println(t.getId() + " - " + t.getNom());
        }



        System.out.println("\n===== TACHES REALISEES =====");

        ps.afficherTachesRealisees(p1);


        System.out.println("\n===== PRIX SUPERIEUR A 1000 DH =====");

        ts.afficherTaches(ts.findPrixSuperieur1000());


        System.out.println("\n===== TACHES ENTRE DEUX DATES =====");

        ts.afficherTaches(
                ts.findTachesRealiseesEntreDates(
                        LocalDate.of(2013, 2, 1),
                        LocalDate.of(2013, 4, 30)
                )
        );



        System.out.println("\n===== TACHES PAR EMPLOYE =====");

        es.afficherTachesRealisees(e1);


        System.out.println("\n===== PROJETS GERES =====");

        es.afficherProjetsGeres(e1);



        HibernateUtil.shutdown();
    }
}
