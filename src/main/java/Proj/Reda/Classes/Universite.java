package Proj.Reda.Classes;


import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.*;

@Entity
@org.hibernate.annotations.DynamicInsert
@org.hibernate.annotations.DynamicUpdate
public class Universite {

    @Transient
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @PostPersist
    public void notifyAdmin(){
        logger.info("Universite vient d'etre modifie !");
    }


    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Column
    @Size.List ({
            @Size(min=3, message="The field must be at least {min} characters"),
            @Size(max=40, message="The field must be less than {max} characters")
    })
    private String nom;


    @OneToMany(mappedBy = "universite", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    @org.hibernate.annotations.OnDelete(action = org.hibernate.annotations.OnDeleteAction.CASCADE)
    private Set<Programme> programmes = new HashSet<>();



    @OneToMany(mappedBy = "universite", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    @org.hibernate.annotations.OnDelete(action = org.hibernate.annotations.OnDeleteAction.CASCADE)
    private Set<Cour> cours = new HashSet<>();

    @OneToMany(mappedBy = "universite", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    @org.hibernate.annotations.OnDelete(action = org.hibernate.annotations.OnDeleteAction.CASCADE)
    private Set<Session> sessions = new HashSet<>();


    //Choix de Collection pour son access rapide au cas ou (nombre etudiants pourrait depasser des milliers)
    @OneToMany(mappedBy = "universite", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    @org.hibernate.annotations.OnDelete(action = org.hibernate.annotations.OnDeleteAction.CASCADE)
    public Collection<Personne> personnes = new ArrayList<>();

    public Universite() {
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Programme> getProgrammes() {
        return programmes;
    }

    public void setProgrammes(Set<Programme> programmes) {
        this.programmes = programmes;
    }

    public void addProgramme(Programme programme) {
        this.getProgrammes().add(programme);
    }
    public void deleteProgramme(Programme programme){
        this.getProgrammes().remove(programme);
    }

    public Set<Session> getSessions() {
        return sessions;
    }

    public void setSessions(Set<Session> sessions) {
        this.sessions = sessions;
    }
    public void addSession(Session session) {
        this.getSessions().add(session);
    }

    public Set<Cour> getCours() {
        return cours;
    }

    public void setCours(Set<Cour> cours) {
        this.cours = cours;
    }

    public Collection<Personne> getPersonnes() {
        return personnes;
    }

    public void setPersonnes(Collection<Personne> personnes) {
        this.personnes = personnes;
    }

    public Universite(String nom) {
        this.nom = nom;
    }
}
