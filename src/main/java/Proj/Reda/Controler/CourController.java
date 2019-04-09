package Proj.Reda.Controler;


import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import Proj.Reda.Classes.Cour;
import Proj.Reda.Repository.CoursRep;

@RestController
@RequestMapping("/cours")
public class CourController {

    @Autowired
    private CoursRep repo;

    @RequestMapping(method = RequestMethod.GET)
    public List<Cour> findCours() {
        return repo.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public Cour addCour(@RequestBody Cour cour) {
        cour.setNbMaxEtudiants(33);
        cour.setNbCredits(5);
        cour.setObligatoire(true);
        return repo.saveAndFlush(cour);
    }

//    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
//    public Cour updateCour(@RequestBody Cour updatedCour, @PathVariable Integer id) {
//        Cour cour = repo.getOne(id);
//        cour.setChecked(updatedCour.isChecked());
//        cour.setDescription(updatedCour.getDescription());
//        return repo.saveAndFlush(cour);
//    }



    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {EmptyResultDataAccessException.class, EntityNotFoundException.class})
    public void handleNotFound() {
    }
}