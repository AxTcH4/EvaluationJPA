package ma.projet;

import ma.projet.classes.*;
import ma.projet.services.*;
import ma.projet.util.HibernateUtil;

import java.util.Calendar;

public class App {
    public static void main(String[] args) {
        CategorieService categorieService = new CategorieService();
        ProduitService produitService = new ProduitService();
        CommandeService commandeService = new CommandeService();
        LigneCommandeService ligneService = new LigneCommandeService();

        // Créer données de test
        Categorie cat = new Categorie();
        cat.setCode("INF");
        cat.setLibelle("Informatique");
        categorieService.create(cat);

        Produit p1 = new Produit("ES12", 120);
        p1.setCategorie(cat);
        Produit p2 = new Produit("ZR85", 100);
        p2.setCategorie(cat);
        Produit p3 = new Produit("EE85", 200);
        p3.setCategorie(cat);
        produitService.create(p1);
        produitService.create(p2);
        produitService.create(p3);

        Calendar cal = Calendar.getInstance();
        cal.set(2013, Calendar.MARCH, 14);
        Commande cmd = new Commande(cal.getTime());
        commandeService.create(cmd);

        ligneService.create(new LigneCommandeProduit(7, p1, cmd).getCommande());
        ligneService.create(new LigneCommandeProduit(14, p2, cmd).getCommande());
        ligneService.create(new LigneCommandeProduit(5, p3, cmd).getCommande());

        // Test 1: produits par catégorie
        System.out.println("== Produits catégorie INF ==");
        for (Produit p : produitService.findByCategorie(cat.getId()))
            System.out.println(p.getReference() + " - " + p.getPrix());

        // Test 2: produits commandés entre deux dates
        Calendar d1 = Calendar.getInstance(); d1.set(2013, Calendar.JANUARY, 1);
        Calendar d2 = Calendar.getInstance(); d2.set(2013, Calendar.DECEMBER, 31);
        System.out.println("\n== Produits commandés en 2013 ==");
        for (Produit p : produitService.findByCommandeEntreDates(d1.getTime(), d2.getTime()))
            System.out.println(p.getReference());

        // Test 3: affichage détaillé commande
        System.out.println("\n== Détails commande " + cmd.getId() + " ==");
        produitService.afficherProduitsCommande(cmd.getId());

        // Test 4: produits prix > 100
        System.out.println("\n== Produits prix > 100 DH ==");
        for (Produit p : produitService.findByPrixSuperieur(100))
            System.out.println(p.getReference() + " - " + p.getPrix());

        HibernateUtil.shutdown();
    }
}