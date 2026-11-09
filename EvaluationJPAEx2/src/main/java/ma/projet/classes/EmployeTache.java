
package ma.projet.classes;

import javax.persistence.*;

@Entity
@Table(name = "employe_tache")
public class EmployeTache {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private double nombreHeures;

    @ManyToOne
    @JoinColumn(name = "employe_id")
    private Employe employe;

    @ManyToOne
    @JoinColumn(name = "tache_id")
    private Tache tache;

    public EmployeTache() {}

    public EmployeTache(Employe employe,
                        Tache tache,
                        double nombreHeures) {
        this.employe = employe;
        this.tache = tache;
        this.nombreHeures = nombreHeures;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public double getNombreHeures() {
        return nombreHeures;
    }

    public void setNombreHeures(double nombreHeures) {
        this.nombreHeures = nombreHeures;
    }

    public Employe getEmploye() { return employe; }
    public void setEmploye(Employe employe) {
        this.employe = employe;
    }

    public Tache getTache() { return tache; }
    public void setTache(Tache tache) {
        this.tache = tache;
    }
}
