package Proj.Reda.Controler;


import java.net.URI;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import Proj.Reda.Classes.Programme;
import Proj.Reda.Classes.Session;
import Proj.Reda.Classes.Universite;
import Proj.Reda.Repository.ProgrammeRepository;
import Proj.Reda.Repository.SessionRepository;
import Proj.Reda.Repository.UniversiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import Proj.Reda.Classes.Universite;
import Proj.Reda.Repository.UniversiteRepository;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UniversiteController {


    @Autowired
    SessionRepository sessionRepository;
    @Autowired
    private UniversiteRepository universiteRepository;
    @Autowired
    private ProgrammeRepository programmeRepository;

    @RequestMapping(value = "/universites", method = RequestMethod.GET)
    public ResponseEntity<List<Universite>> getAllUniversites() {
        List<Universite> allUniversites = universiteRepository.retrieveAllUniversites();
        return new ResponseEntity<>(allUniversites, HttpStatus.OK);
    }

    @RequestMapping(value = "/universites/{universiteId}", method = RequestMethod.GET)
    public ResponseEntity<?> getUniversite(@PathVariable Long universiteId) {
        Universite universite = (universiteRepository.findById(universiteId));
        return new ResponseEntity<>(universite, HttpStatus.OK);
    }

    // for tests      -     {"nom":"UNIVERSITE du COLORADO"}
    @RequestMapping(value = "/universites", method = RequestMethod.POST)
    public ResponseEntity<?> createUniversite(@RequestBody Universite universite) {
        universite = universiteRepository.save(universite);


        // Set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newUniv = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(universite.getId()).toUri();
        responseHeaders.setLocation(newUniv);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    // INF-1  -> modification -> {"nom":"UNIVERSITE du mexique"}
    @RequestMapping(value = "/universites/{universiteId}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateUniversite(@RequestBody Universite universiteAupdater, @PathVariable Long universiteId) {
        // Save the entity - A revoir
        Universite universitebdd = universiteRepository.findById(universiteId);
        universitebdd.setNom(universiteAupdater.getNom());
        universiteRepository.save(universitebdd);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // tested -> attention ! supprime aussi les autres entites relié
    @RequestMapping(value = "/universites/{universiteId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteUniversite(@PathVariable Long universiteId) {
        Universite c = universiteRepository.findById(universiteId);
        universiteRepository.deleteById(universiteId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // {"nomProg":"Nouveau Programme Securité","type":"MAITRISE","totalCreditsobligatoires":23}
    @RequestMapping(value = "/universites/{universiteId}/add/programme", method = RequestMethod.POST)
    public ResponseEntity<?> addProgramme(@PathVariable Long universiteId, @RequestBody Programme programme) {
        Universite universite = universiteRepository.findById(universiteId);
        Programme nouveauprogramme = new Programme(programme.getNomProg(), programme.getType(), programme.getTotalCreditsobligatoires(), universite);
        universite.addProgramme(nouveauprogramme);
        programmeRepository.insert(nouveauprogramme);
        universiteRepository.save(universite);
        // Set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();

        URI newProgrammeUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(nouveauprogramme.getId()).toUri();
        responseHeaders.setLocation(newProgrammeUri);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    // {"annee":"2031","saison": "AUTOMNE", "dateDebut":"2013-10-10","dateFin":"2034-04-12"}
    @RequestMapping(value = "/universites/{universiteId}/add/session", method = RequestMethod.POST)
    public ResponseEntity<?> addSession(@PathVariable Long universiteId, @RequestBody Session session) {
        Universite universite = universiteRepository.findById(universiteId);
        Session newSession = new Session(session.getAnnee(), session.getSaison(), session.getDateDebut(), session.getDateFin(), universite);
        sessionRepository.insert(newSession);
        universiteRepository.save(universite);
        // Set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();

        URI newProgrammeUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newSession.getId()).toUri();
        responseHeaders.setLocation(newProgrammeUri);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }


}