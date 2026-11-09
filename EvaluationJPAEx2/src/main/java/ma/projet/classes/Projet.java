
package ma.projet.classes;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "projet")
public class Projet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nom;

    private LocalDate dateDebut;

    private LocalDate dateFin;

    @ManyToOne
    @JoinColumn(name = "responsable_id")
    private Employe responsable;

    @OneToMany(mappedBy = "projet",
            cascade = CascadeType.ALL)
    private List<Tache> taches = new ArrayList<>();

    public Projet() {}

    public Projet(String nom, LocalDate dateDebut,
                  LocalDate dateFin, Employe responsable) {
        this.nom = nom;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.responsable = responsable;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public LocalDate getDateDebut() { return dateDebut; }
    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() { return dateFin; }
    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public Employe getResponsable() { return responsable; }
    public void setResponsable(Employe responsable) {
        this.responsable = responsable;
    }

    public List<Tache> getTaches() { return taches; }
    public void setTaches(List<Tache> taches) {
        this.taches = taches;
    }

    @Override
    public String toString() {
        return "Projet{" + "id=" + id +
                ", nom='" + nom + '\'' + '}';
    }
}
