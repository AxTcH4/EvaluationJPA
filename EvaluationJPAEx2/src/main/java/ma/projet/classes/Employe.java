
package ma.projet.classes;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "employe")
public class Employe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nom;

    private String prenom;

    private String email;

    @OneToMany(mappedBy = "responsable")
    private List<Projet> projets = new ArrayList<>();

    @OneToMany(mappedBy = "employe",
            cascade = CascadeType.ALL)
    private List<EmployeTache> taches = new ArrayList<>();

    public Employe() {}

    public Employe(String nom, String prenom, String email) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() { return email; }
    public void setEmail(String email) {
        this.email = email;
    }

    public List<Projet> getProjets() { return projets; }
    public void setProjets(List<Projet> projets) {
        this.projets = projets;
    }

    public List<EmployeTache> getTaches() { return taches; }
    public void setTaches(List<EmployeTache> taches) {
        this.taches = taches;
    }

    @Override
    public String toString() {
        return "Employe{" + "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' + '}';
    }
}
