package ma.projet.services;

import ma.projet.classes.Produit;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;
import ma.projet.dao.AbstractDao;

import java.util.Date;
import java.util.List;

public class ProduitService extends AbstractDao<Produit> {

    // 1. Produits par catégorie
    public List<Produit> findByCategorie(int categorieId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                            "select p from Produit p where p.categorie.id = :catId", Produit.class)
                    .setParameter("catId", categorieId)
                    .list();
        }
    }

    // 2. Produits commandés entre deux dates
    public List<Produit> findByCommandeEntreDates(Date debut, Date fin) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                            "select distinct l.produit from LigneCommandeProduit l " +
                                    "where l.commande.date between :d1 and :d2", Produit.class)
                    .setParameter("d1", debut)
                    .setParameter("d2", fin)
                    .list();
        }
    }

    // 3. Produits d'une commande donnée (affichage façon "exemple attendu")
    public void afficherProduitsCommande(int commandeId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<Object[]> result = session.createQuery(
                            "select c.id, c.date, p.reference, p.prix, l.quantite " +
                                    "from LigneCommandeProduit l " +
                                    "join l.commande c join l.produit p " +
                                    "where c.id = :cmdId", Object[].class)
                    .setParameter("cmdId", commandeId)
                    .list();

            if (result.isEmpty()) {
                System.out.println("Aucune commande trouvée.");
                return;
            }
            Object[] first = result.get(0);
            System.out.println("Commande : " + first[0] + "     Date : " + first[1]);
            System.out.println("Liste des produits :");
            System.out.printf("%-10s %-8s %-8s%n", "Référence", "Prix", "Quantité");
            for (Object[] row : result) {
                System.out.printf("%-10s %-6s DH %-8s%n", row[2], row[3], row[4]);
            }
        }
    }

    // 4. Produits dont prix > 100 DH, via requête nommée
    public List<Produit> findByPrixSuperieur(float prix) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createNamedQuery("Produit.findByPrixSup", Produit.class)
                    .setParameter("prix", prix)
                    .list();
        }
    }
}