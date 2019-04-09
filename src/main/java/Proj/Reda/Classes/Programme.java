package Proj.Reda.Classes;

import Proj.Reda.Enum.TypeProgramme;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

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
