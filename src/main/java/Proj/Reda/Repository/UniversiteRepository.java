package Proj.Reda.Repository;

import Proj.Reda.Classes.*;

import java.util.*;

import javax.persistence.EntityManager;
import javax.persistence.PostPersist;
import javax.persistence.Query;


import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional

public class UniversiteRepository {

    @Autowired
    EntityManager em;
    @Autowired
    SessionRepository sessionRepository;
    private Logger logger = LoggerFactory.getLogger(this.getClass());



    public ArrayList<Personne> recupererPersonnes(Long id) {
        ArrayList<Personne> NouvelleArrayList = new ArrayList();
        Collection<Personne> personnesRecuperes = this.findById(id).getPersonnes();
        NouvelleArrayList.addAll(personnesRecuperes);
        return NouvelleArrayList;
    }

    public List<Cour> recupererCours(Long id) {
        Query query = em.createQuery(
                "select c from Cour c, Universite u where u.id = :id", Cour.class
        ).setParameter("id", id);
        return query.getResultList();
    }

    public Set<Session> recupererSessions(Long id) {
        HashSet<Session> sessions = new HashSet<>();
        sessions.addAll(this.findById(id).getSessions());
        return sessions;
    }

    public void insert(Universite universite) {
        em.persist(universite);
    }

    public Universite findById(Long id) {
        return em.find(Universite.class, id);
    }

    public void deleteById(Long id) {
        Universite universite = findById(id);
        em.remove(universite);
    }

    public Universite save(Universite universite) {

        if (universite.getId() == null) {

            em.persist(universite);
        } else {
            em.merge(universite);
        }

        return universite;
    }

    public void update(Universite universite) {
        // modifications sur l'universite
        em.refresh(universite);
    }

    public List<Universite> retrieveAllUniversites() {
        return em.createQuery("select e from Universite e", Universite.class).getResultList();
    }
}