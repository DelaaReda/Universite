package Proj.Reda.Classes;

import javax.persistence.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(
        name = "PERSONNE",
        uniqueConstraints =
        @UniqueConstraint(
                name = "UNQ_USERNAME_EMAIL",
                columnNames = { "COURRIEL" }
        ),indexes = {@Index(
        name = "IDX_COURRIEL",
        columnList = "COURRIEL")}
)
public abstract class Personne {


    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Size.List ({
            @Size(min=3, message="The field must be at least {min} characters"),
            @Size(max=20, message="The field must be less than {max} characters")
    })
    private String nom;


    @NotNull
    @Size.List ({
            @Size(min=3, message="The field must be at least {min} characters"),
            @Size(max=20, message="The field must be less than {max} characters")
    })
    private String prenom;


    @NotNull
    private Date dateNaiss;

    @NotNull
    @Pattern(regexp ="^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.(?:[a-zA-Z]{2,6})$", message="Invalid E-Mail")
    private String courriel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(
            name = "PERSONNE_UNIVERSITE",
            joinColumns =
            @JoinColumn(name = "PERSONNE_ID"),
            inverseJoinColumns =
            @JoinColumn(nullable = false))
    Universite universite;

    @OneToMany(mappedBy = "personne")
    protected Set<CoursDonne> coursDonnes = new HashSet<>();

    //#####################################GETTERS_SETTERS

    public Set<CoursDonne> getCoursDonnes() {
        return coursDonnes;
    }

    public void setCoursDonnes(Set<CoursDonne> coursDonnes) {
        this.coursDonnes = coursDonnes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Date getDateNaiss() {
        return dateNaiss;
    }

    public void setDateNaiss(Date dateNaiss) {
        this.dateNaiss = dateNaiss;
    }

    public String getCourriel() {
        return courriel;
    }

    public void setCourriel(String courriel) {
        this.courriel = courriel;
    }
    //################################## CONSTRUCTORS


    public Universite getUniversite() {
        return universite;
    }

    public void setUniversite(Universite universite) {
        this.universite = universite;
    }

    public Personne(String nom, String prenom, Date dateNaiss, String courriel, Universite universite) {
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaiss = dateNaiss;
        this.courriel = courriel;
        this.universite = universite;
    }

    public Personne() {
    }
}
