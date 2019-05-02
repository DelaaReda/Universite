package Proj.Reda;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import javax.persistence.EntityManager;

import Proj.Reda.Classes.Universite;
import Proj.Reda.Repository.UniversiteRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;



@RunWith(SpringRunner.class)
@SpringBootTest(classes = RedaApplication.class)
public class UniversiteTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UniversiteRepository repository;

    @Autowired
    EntityManager em;


    @Test
    public void findById_basic() {
        Universite universite = repository.findById(3L).get();
        assertEquals("Free Universite", universite.getNom());
    }

    @Test
    @DirtiesContext
    public void deleteById_basic() {
        repository.deleteById(3L);
        assertNull(repository.findById(3L));
    }

    @Test
    @DirtiesContext
    public void save_basic() {
        // get a universite
        Universite universite = repository.findById(3L).get();
        assertEquals("Free Universite", universite.getNom());

        // update details
        universite.setNom("Free Universite name - Updated");
        repository.save(universite);

        // check the value
        Universite universite1 = repository.findById(3L).get();
        assertEquals("Free Universite name - Updated", universite1.getNom());
    }




}








