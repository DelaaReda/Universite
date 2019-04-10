package Proj.Reda.Classes;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "COURSDONNE")
@org.hibernate.annotations.Immutable
public class CoursDonne {
    @Embeddable
    public static class Id implements Serializable {
        @Column(name = "COUR_ID")
        protected String courId;
        
        @Column(name = "PERSONNE_ID")
        protected Long personneId;

        @Column(name = "SESSION_ID")
        protected Long sessionId;
        
        public Id() {
        }
        public Id(String courId, Long personneId, Long sessionId) {
            this.courId = courId;
            this.personneId = personneId;
            this.sessionId = sessionId;
        }

        public boolean equals(Object o) {
            if (o != null && o instanceof Id) {
                Id that = (Id) o;
                return this.courId.equals(that.courId)
                        && this.personneId.equals(that.personneId) && this.sessionId.equals(that.sessionId);
            }
            return false;
        }
        public int hashCode() {
            return courId.hashCode() + personneId.hashCode() + sessionId.hashCode();
        }
    }
    @EmbeddedId
    protected Id id = new Id();


    @Column
    @NotNull
    private char jourSemaine;


    @ManyToOne
    @JoinColumn(
            name = "COUR_ID",
            insertable = false, updatable = false)
    protected Cour cour;

    @ManyToOne
    @JoinColumn(name = "SESSION_ID",
            insertable = false, updatable = false)
    protected Session session;

    @ManyToOne
    @JoinColumn(
            name = "PERSONNE_ID",
            insertable = false, updatable = false)
    protected Personne personne;

    public Id getId() {
        return id;
    }

    public void setId(Id id) {
        this.id = id;
    }

    public char getJourSemaine() {
        return jourSemaine;
    }

    public void setJourSemaine(char jourSemaine) {
        this.jourSemaine = jourSemaine;
    }


    public CoursDonne(
            char jourSemaine,
            Cour cour,
            Personne personne, Session session) {
        this.jourSemaine = jourSemaine;
        this.cour = cour;
        this.personne = personne;
        this.id.courId = cour.getId();
        this.id.personneId = personne.getId();
        this.id.sessionId = session.getId();
        cour.getCoursDonnes().add(this);
        personne.getCoursDonnes().add(this);
        session.getCoursDonnes().add(this);
    }

    public CoursDonne() {
    }
// ...
}