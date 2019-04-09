package Proj.Reda.Classes;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@PrimaryKeyJoinColumn(name = "PROF_ID")
public class Professeur extends Personne{



    @NotNull
    private Long numAssSociale;

    public Long getNumAssSociale() {
        return numAssSociale;
    }

    public void setNumAssSociale(Long numAssSociale) {
        this.numAssSociale = numAssSociale;
    }

    public Professeur(String nom, String prenom, Date dateNaiss, String courriel, Universite universite, Long numAssSociale) {
        super(nom, prenom, dateNaiss, courriel, universite);
        this.numAssSociale = numAssSociale;
    }
    public Professeur(){}
}
