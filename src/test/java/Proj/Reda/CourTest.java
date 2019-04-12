//package Proj.Reda;
//
//import Proj.Reda.Classes.Cour;
//import Proj.Reda.Repository.CoursRep;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import static org.junit.Assert.assertEquals;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class CourTest {
//
//    @Autowired
//    CoursRep repository;
//
//    @Test
//    public void contextLoads() {
//        Long number  = repository.count();
//        assertEquals((Long) 2L, number);
//    }
//
//    @Test
//    public void findById_basic() {
//        Object cour = repository.findByIdLike("INF-1002");
//        assertEquals(44, cour.getNbCredits());
//    }
//
//}
