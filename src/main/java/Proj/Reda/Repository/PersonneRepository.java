package Proj.Reda.Repository;

import Proj.Reda.Classes.Etudiant;
import Proj.Reda.Classes.Personne;

import Proj.Reda.Classes.Professeur;
import Proj.Reda.Classes.Universite;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.List;


@Repository
@Transactional
public class PersonneRepository {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EntityManager em;

    public void insert(Personne personne) {
      em.persist(personne);
    }

    public Personne findById(Long id) {
        return em.find(Personne.class, id);
    }

    public String findUniversite(Long id){
        return em.find(Personne.class, id).getUniversite().getNom();
    }

    public void deleteById(Long id) {
        Personne personne = findById(id);
        em.remove(personne );
    }
    public Personne save(Personne personne) {

        if (personne.getId() == null) {
            em.persist(personne);
        } else {
            em.merge(personne);
        }

        return personne;
    }

    public List<Personne> retrouveAllPersonnes() {
        return em.createQuery("select e from Personne e", Personne.class).getResultList();
    }
    public List<Etudiant> retrouveEtudiants() {
        return em.createQuery("select stud from Etudiant stud", Etudiant.class).getResultList();
    }
    public List<Professeur> retrouveProfesseurs() {
        return em.createQuery("select prof from Professeur prof", Professeur.class).getResultList();
    }
}