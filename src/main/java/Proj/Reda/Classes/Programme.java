package Proj.Reda.Classes;

import Proj.Reda.Enum.TypeProgramme;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Programme {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    @NotNull
    @Access(AccessType.PROPERTY)
    private String nomProg;

    @Column
    @NotNull
    @Enumerated(EnumType.STRING)
    private TypeProgramme type;

    @Column
    @NotNull
    private int totalCreditsobligatoires;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(
            name = "PROGRAMME_UNIVERSITE",
            joinColumns =
            @JoinColumn(name = "PROGRAMME_ID"),
            inverseJoinColumns =
            @JoinColumn(nullable = false))
    Universite universite;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "PROGRAMME_COUR",
            joinColumns = @JoinColumn(name = "PROGRAMME_ID"),
            inverseJoinColumns = @JoinColumn(name = "COUR_ID")
    )
    protected Set<Cour> cours = new HashSet<Cour>();

    public Set<Cour> getCours() {
        return cours;
    }

    public void setCours(Set<Cour> cours) {
        this.cours = cours;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomProg() {
        return nomProg;
    }

    public void setNomProg(String nomProg) {
        this.nomProg = nomProg;
    }

    public TypeProgramme getType() {
        return type;
    }

    public void setType(TypeProgramme type) {
        this.type = type;
    }

    public int getTotalCreditsobligatoires() {
        return totalCreditsobligatoires;
    }

    public void setTotalCreditsobligatoires(int totalCreditsobligatoires) {
        this.totalCreditsobligatoires = totalCreditsobligatoires;
    }

    public Universite getUniversite() {
        return universite;
    }

    public void setUniversite(Universite universite) {
        this.universite = universite;
    }

    public Programme(String nomProg, TypeProgramme type, int totalCreditsobligatoires, Universite universite) {
        this.nomProg = nomProg;
        this.type = type;
        this.totalCreditsobligatoires = totalCreditsobligatoires;
        this.universite = universite;
    }

    public Programme() {
    }
}
