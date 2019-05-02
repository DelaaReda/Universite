package Proj.Reda.Controler;


import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import Proj.Reda.Classes.*;
import Proj.Reda.Repository.CoursDonneRepository;
import Proj.Reda.Repository.PersonneRepository;
import Proj.Reda.Repository.UniversiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import Proj.Reda.Repository.CoursRepository;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class CourController {

    @Autowired
    private CoursRepository courRepository;

    @Autowired
    private UniversiteRepository universiteRepository;

    @Autowired
    private CoursDonneRepository coursDonneRepository;

    @Autowired
    private PersonneRepository personneRepository;

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
    @RequestMapping(value="/cours/{courId}", method=RequestMethod.PATCH)
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

    //#################################### New Code #####################################################################//


    // for test -> http://localhost:8080/cours/INF-1/sessions/6/professeurs
    @RequestMapping(value="/cours/{courId}/sessions/{sessionId}/professeurs", method=RequestMethod.GET)
    public ResponseEntity<?> getprofesseurFromCourAndSession(@PathVariable String courId, @PathVariable Long sessionId) {
        List<CoursDonne> courdonnees = coursDonneRepository.findByCour_IdAndSession_Id(courId,sessionId);
        List <Personne> professeurs = new ArrayList<>();
        for (CoursDonne partie : courdonnees) {
            String classNom = partie.getPersonne().getClass().getName();

           if (classNom.endsWith("Professeur")){

                Personne prof = (partie.getPersonne());
                professeurs.add(prof);
           }
        }
        return new ResponseEntity<> (professeurs, HttpStatus.OK);
    }

    // for test -> http://localhost:8080/cours/INF-1/sessions/6/etudiants
    @RequestMapping(value="/cours/{courId}/sessions/{sessionId}/etudiants", method=RequestMethod.GET)
    public ResponseEntity<?> getEtudiantsFromCourAndSession(@PathVariable String courId, @PathVariable Long sessionId) {
        List<CoursDonne> courdonnees = coursDonneRepository.findByCour_IdAndSession_Id(courId,sessionId);
        List <Personne> etudiants = new ArrayList<>();
        for (CoursDonne partie : courdonnees) {
            String classNom = partie.getPersonne().getClass().getName();

            if (classNom.endsWith("Etudiant")){

                Personne etudiant = (partie.getPersonne());
                etudiants.add(etudiant);
            }
        }
        return new ResponseEntity<> (etudiants, HttpStatus.OK);
    }

}