package Proj.Reda.Classes;

import Proj.Reda.Enum.SaisonSession;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Session {

    @ManyToOne
    @JoinTable(
            name = "SESSION_UNIVERSITE",
            joinColumns =
            @JoinColumn(name = "SESSION_ID"),
            inverseJoinColumns =
            @JoinColumn(nullable = false))
    Universite universite;
    @Id
    @GeneratedValue
    private Long id;

    @Column
    @NotNull
    private int annee;

    @Column
    @Enumerated(EnumType.STRING)
    private SaisonSession saison;

    @Column
    @Temporal(TemporalType.DATE)
    private Date dateDebut;

    @Column
    @Temporal(TemporalType.DATE)
    private Date dateFin;

    @OneToMany(mappedBy = "session", cascade = CascadeType.PERSIST)
    protected Set<CoursDonne> coursDonnes = new HashSet<>();

    public Set<CoursDonne> getCoursDonnes() {
        return coursDonnes;
    }

    public void setCoursDonnes(Set<CoursDonne> coursDonnes) {
        this.coursDonnes = coursDonnes;
    }

    public Session(int annee, SaisonSession saison, Date dateDebut, Date dateFin, Universite universite) {
        this.annee = annee;
        this.saison = saison;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.universite = universite;
    }

    public Session() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public SaisonSession getSaison() {
        return saison;
    }

    public void setSaison(SaisonSession saison) {
        this.saison = saison;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public Universite getUniversite() {
        return universite;
    }

    public void setUniversite(Universite universite) {
        this.universite = universite;
    }
}
