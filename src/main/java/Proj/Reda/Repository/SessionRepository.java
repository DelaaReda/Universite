package Proj.Reda.Repository;

import Proj.Reda.Classes.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;


@Repository
@Transactional
public class SessionRepository {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EntityManager em;

    public void insert(Session session) {
      em.persist(session);
    }

    public Session findById(String id) {
        return em.find(Session.class, id);
    }

    public void deleteById(String id) {
        Session session = findById(id);
        em.remove(session );
    }
    public Session save(Session session) {

        if (session.getId() == null) {
            em.persist(session);
        } else {
            em.merge(session);
        }

        return session;
    }

    public List<Session> retrieveAllSessions() {
        return em.createQuery("select e from Session e", Session.class).getResultList();
    }
}