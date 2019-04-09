package Proj.Reda;

import Proj.Reda.Classes.*;
import Proj.Reda.Enum.SaisonSession;
import Proj.Reda.Enum.TypeProgramme;
import Proj.Reda.Repository.CoursRep;
import Proj.Reda.Repository.PersonneRepository;
import Proj.Reda.Repository.ProgrammeRepository;
import Proj.Reda.Repository.UniversiteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@SpringBootApplication
public class RedaApplication implements CommandLineRunner {

	private Logger logger = LoggerFactory.getLogger(this.getClass());


	@Autowired
	private UniversiteRepository universiteRepository;

	@Autowired
	private PersonneRepository personneRepository;

	@Autowired
    private CoursRep coursRep;

	@Autowired
	private ProgrammeRepository programmeRepository;

	public static void main(String[] args) {
		SpringApplication.run(RedaApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {

		// creation universite
		Universite universite = new Universite();
		universite.setNom("Universite du people");

        // creation universite
        Universite universite2 = new Universite();
        universite2.setNom("Universite de Montreal");

		// creation etudiants
		Personne etudiant = new Etudiant("Della", "Reda", new Date(), "reda@mail.dz", universite,"CODE400008IOP");
		Personne etudiant2 = new Etudiant("Mister", "You", new Date(), "mister@you.dz", universite,"CODE4008888YP");

		// creation professeur
		Personne professeur = new Professeur("Steph", "Architect", new Date(), "steph@boss.com", universite,111222333L);

		// creation programmes
		Programme programme1 = new Programme("Mathematique et Cryptographie",TypeProgramme.MINEUR, 67, universite);
		Programme programme2 = new Programme("Philosophie de Logiciel",TypeProgramme.BACCALAUREAT, 3, universite);
		Programme programme3 = new Programme("Informatique Architechturale",TypeProgramme.MAITRISE, 60, universite);

		// ajout programmes dans universite
		universite.addProgramme(programme1);
		universite.addProgramme(programme2);
		universite.getProgrammes().add(programme3);

		// creer sessions
        Session session1 = new Session(2030, SaisonSession.AUTOMNE, new Date(), new Date(), universite);
        Session session2 = new Session(2020, SaisonSession.ETE, new Date(), new Date(), universite);
        Session session3 = new Session(2019, SaisonSession.HIVER, new Date(), new Date(), universite);

        universite.addSession(session1);
        universite.addSession(session2);
        universite.addSession(session3);

		// persistence donnees dans la BDD
        universiteRepository.insert(universite);
		universiteRepository.insert(universite2);
		personneRepository.insert(etudiant);
		personneRepository.insert(etudiant2);
		personneRepository.insert(professeur);


		// access programmes a partir universite
		Programme programeRecupere= universite.getProgrammes().iterator().next();
		logger.info("iterer liste programmes a partir de universite -> {}", programeRecupere.getNomProg());

		// suppression du 1er programme recuperer a partir de universite
//		logger.info("programme a supprimer -> {}", programeRecupere.getNomProg());
//		universite.deleteProgramme(programeRecupere);
//		universiteRepository.save(universite);


		// recuperer personnes dans universite
        ArrayList <Personne> people = universiteRepository.recupererPersonnes(1L);
		logger.info("iterer personnes 1 dans universite -> {}", people.iterator().next().getNom());

		// recuperer universites d'un etudiant
        logger.info("recuperer universites d'un etudiant -> {}", personneRepository.findUniversite(9L));

        // creer un cours
        Cour cour1 = new Cour(true, 45, 89, universite);
        Cour cour2 = new Cour(true, 75, 66, universite);
        coursRep.save(cour1);
        coursRep.save(cour2);

        // recuperer cours de universite
        logger.info("recuperer cours de universite -> {}", universiteRepository.recupererCours(1L).iterator().next().getId());

        // recuperer session de universite
        logger.info("recuperer sessions de universite -> {}", universiteRepository.recupererSessions(1L).iterator().next().getId());

        // ajouter cours dans programme
		programme1.getCours().add(cour1);
		programme1.getCours().add(cour2);
		programmeRepository.save(programme1);





        // get all universites
		logger.info("All Universites -> {}", universiteRepository.retrieveAllUniversites());


	}


}
