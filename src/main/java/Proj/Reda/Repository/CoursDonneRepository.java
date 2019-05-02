package Proj.Reda.Repository;

import Proj.Reda.Classes.CoursDonne;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;



public interface CoursDonneRepository extends JpaRepository<CoursDonne, Long> {
    List<CoursDonne> findByCour_IdAndSession_Id(String CourID, Long sessionID);
}
