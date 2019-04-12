package Proj.Reda.Repository;

import Proj.Reda.Classes.Cour;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;


public interface CoursRep extends JpaRepository<Cour, Long> {

    @Query("select c from Cour c where c.id like :id")
    Cour findByIdLike(@Param("id") String id);

    @Override
    @Query("select c from Cour c")
    List<Cour> findAll();


}
