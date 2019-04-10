package Proj.Reda.Repository;

import Proj.Reda.Classes.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import java.util.List;


@Repository
@Transactional
public class CoursDonneRepository {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EntityManager em;

    public void insert(CoursDonne coursDonne) {
        em.persist(coursDonne);
    }

    public CoursDonne findById(Long id) {
        return em.find(CoursDonne.class, id);
    }


    public void deleteById(Long id) {
        CoursDonne coursDonne = findById(id);
        em.remove(coursDonne );
    }
    public CoursDonne save(CoursDonne coursDonne) {

        if (coursDonne.getId() == null) {
            em.persist(coursDonne);
        } else {
            em.merge(coursDonne);
        }

        return coursDonne;
    }

    public List<CoursDonne> retrouveAllCoursDonnes() {
        return em.createQuery("select e from CoursDonne e", CoursDonne.class).getResultList();
    }
    public List<Etudiant> retrouveEtudiantsInscrits(String courid) {

        Query query = em.createQuery(
             "select e from Etudiant e LEFT JOIN CoursDonne c ON c.personne = e.id JOIN Personne p ON p.id = e.id where c.cour.id = :courId"
        ).setParameter("courId", courid);
        return query.getResultList();
    }
    public List<Professeur> retrouveProfesseursDuCour() {
        return em.createQuery("select prof from Professeur prof", Professeur.class).getResultList();
    }
}