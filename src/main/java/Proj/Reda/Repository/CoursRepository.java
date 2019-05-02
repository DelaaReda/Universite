package Proj.Reda.Repository;

import Proj.Reda.Classes.Cour;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;




public interface CoursRepository extends JpaRepository<Cour, Long> {

    @Query("select c from Cour c where c.id like :id")
    Cour findByIdLike(@Param("id") String id);

}
