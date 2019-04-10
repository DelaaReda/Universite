package Proj.Reda.Classes;



import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "COUR")
public class Cour {
    @Id
    @GeneratedValue(generator = "cour-generator")
    @GenericGenerator(name = "cour-generator",
            parameters = @Parameter(name = "prefix", value = "INF"),
            strategy = "Proj.Reda.Helper.IdCoursGenerator")
    private String id;

    @Column
    @org.hibernate.annotations.Type(type="true_false")
    @NotNull
    private boolean obligatoire;

    @Column(nullable = false)
    @org.hibernate.annotations.ColumnDefault("60")
    private int nbMaxEtudiants;

    @Column(nullable = false)
    private int nbCredits;

    @ManyToOne
    @JoinTable(
            name = "COUR_UNIVERSITE",
            joinColumns =
            @JoinColumn(name = "COUR_ID"),
            inverseJoinColumns =
            @JoinColumn(nullable = false))
    Universite universite;


    @OneToMany(mappedBy = "cour", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    @org.hibernate.annotations.OnDelete(action = org.hibernate.annotations.OnDeleteAction.CASCADE)
    protected Set<CoursDonne> coursDonnes = new HashSet<>();

    @ManyToMany(mappedBy = "cours")
    protected Set<Programme> programmes = new HashSet<Programme>();

    public Set<CoursDonne> getCoursDonnes() {
        return coursDonnes;
    }

    public void setCoursDonnes(Set<CoursDonne> coursDonnes) {
        this.coursDonnes = coursDonnes;
    }

    public Universite getUniversite() {
        return universite;
    }

    public void setUniversite(Universite universite) {
        this.universite = universite;
    }

    public Set<Programme> getProgrammes() {
        return programmes;
    }

    public void setProgrammes(Set<Programme> programmes) {
        this.programmes = programmes;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isObligatoire() {
        return obligatoire;
    }

    public void setObligatoire(boolean obligatoire) {
        this.obligatoire = obligatoire;
    }

    public int getNbMaxEtudiants() {
        return nbMaxEtudiants;
    }

    public void setNbMaxEtudiants(int nbMaxEtudiants) {
        this.nbMaxEtudiants = nbMaxEtudiants;
    }

    public int getNbCredits() {
        return nbCredits;
    }

    public void setNbCredits(int nbCredits) {
        this.nbCredits = nbCredits;
    }

    public Cour(boolean obligatoire, int nbMaxEtudiants, int nbCredits, Universite universite) {
        this.obligatoire = obligatoire;
        this.nbMaxEtudiants = nbMaxEtudiants;
        this.nbCredits = nbCredits;
        this.universite = universite;
    }
    public Cour() {
    }
}
