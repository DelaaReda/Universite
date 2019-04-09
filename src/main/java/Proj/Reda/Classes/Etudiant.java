package Proj.Reda.Classes;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@PrimaryKeyJoinColumn(name = "STUD_ID")
public class Etudiant extends Personne{

    @NotNull
    private String codePerm;

    public String getCodePerm() {
        return codePerm;
    }

    public void setCodePerm(String codePerm) {
        this.codePerm = codePerm;
    }

    public Etudiant(String nom, String prenom, Date dateNaiss, String courriel, Universite universite, String codePerm) {
        super(nom, prenom, dateNaiss, courriel, universite);
        this.codePerm = codePerm;
    }
    public Etudiant(){}
}