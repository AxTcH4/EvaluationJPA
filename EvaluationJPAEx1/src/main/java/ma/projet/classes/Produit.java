package ma.projet.classes;

import jakarta.persistence.*;
import java.util.List;

@Entity
@NamedQuery(name = "Produit.findByPrixSup",
        query = "select p from Produit p where p.prix > :prix")
public class Produit {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String reference;
    private float prix;

    @ManyToOne
    @JoinColumn(name = "categorie_id")
    private Categorie categorie;

    @OneToMany(mappedBy = "produit", cascade = CascadeType.ALL)
    private List<LigneCommandeProduit> lignes;

    public Produit() {}
    public Produit(String reference, float prix) {
        this.reference = reference;
        this.prix = prix;
    }

    // Getters / Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getReference() { return reference; }
    public void setReference(String reference) { this.reference = reference; }
    public float getPrix() { return prix; }
    public void setPrix(float prix) { this.prix = prix; }
    public Categorie getCategorie() { return categorie; }
    public void setCategorie(Categorie categorie) { this.categorie = categorie; }
    public List<LigneCommandeProduit> getLignes() { return lignes; }
    public void setLignes(List<LigneCommandeProduit> lignes) { this.lignes = lignes; }
}