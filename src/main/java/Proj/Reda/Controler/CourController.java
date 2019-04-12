package Proj.Reda.Controler;


import java.net.URI;
import java.util.List;

import Proj.Reda.Repository.UniversiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import Proj.Reda.Classes.Cour;
import Proj.Reda.Repository.CoursRep;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class CourController {

    @Autowired
    private CoursRep courRepository;

    @Autowired
    private UniversiteRepository universiteRepository;

    @RequestMapping(value="/cours", method=RequestMethod.GET)
    public ResponseEntity<List<Cour>> getAllCours() {
        List<Cour> allCours = courRepository.findAll();
        return new ResponseEntity<>(allCours, HttpStatus.OK);
    }
    @RequestMapping(value="/cours/{courId}", method=RequestMethod.GET)
    public ResponseEntity<?> getCour(@PathVariable String courId) {
        Cour cour = (courRepository.findByIdLike(courId));
        return new ResponseEntity<> (cour, HttpStatus.OK);
    }

    // for tests      -     {"obligatoire":true,"nbMaxEtudiants":333,"nbCredits":559}
    @RequestMapping(value="/cours", method=RequestMethod.POST)
    public ResponseEntity<?> createCour(@RequestBody Cour cour) {
        cour = courRepository.saveAndFlush(cour);

        // Set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newCourUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cour.getId()).toUri();
        responseHeaders.setLocation(newCourUri);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }
    
    // INF-1  -> modification -> {"obligatoire":true,"nbMaxEtudiants":222,"nbCredits":559}
    @RequestMapping(value="/cours/{courId}", method=RequestMethod.PUT)
    public ResponseEntity<?> updateCour(@RequestBody Cour courAupdater, @PathVariable String courId) {
        // Save the entity - A revoir
        Cour courRecupere = courRepository.findByIdLike(courId);
        courRecupere.setNbCredits(courAupdater.getNbCredits());
        courRecupere.setObligatoire(courAupdater.isObligatoire());
        courRecupere.setNbMaxEtudiants(courAupdater.getNbMaxEtudiants());
        courRepository.save(courRecupere);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // will set the variable deleted to true
    @RequestMapping(value="/cours/{courId}", method=RequestMethod.DELETE)
    public ResponseEntity<?> deleteCour(@PathVariable String courId) {

        Cour c = courRepository.findByIdLike(courId);
        c.setDeleted(true);
        courRepository.save(c);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}