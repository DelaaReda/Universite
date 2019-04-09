package Proj.Reda.Repository;

import Proj.Reda.Classes.Programme;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;


@Repository
@Transactional
public class ProgrammeRepository {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EntityManager em;

    public void insert(Programme programme) {
      em.persist(programme);
    }

    public Programme findById(String id) {
        return em.find(Programme.class, id);
    }

    public void deleteById(String id) {
        Programme programme = findById(id);
        em.remove(programme );
    }
    public Programme save(Programme programme) {

        if (programme.getId() == null) {
            em.persist(programme);
        } else {
            em.merge(programme);
        }

        return programme;
    }

    public List<Programme> retrieveAllProgrammes() {
        return em.createQuery("select e from Programme e", Programme.class).getResultList();
    }
}