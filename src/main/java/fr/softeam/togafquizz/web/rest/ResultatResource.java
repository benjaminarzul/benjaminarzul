package fr.softeam.togafquizz.web.rest;

import com.codahale.metrics.annotation.Timed;
import fr.softeam.togafquizz.domain.Resultat;
import fr.softeam.togafquizz.repository.ResultatRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * REST controller for managing Resultat.
 */
@RestController
@RequestMapping("/api")
public class ResultatResource {

    private final Logger log = LoggerFactory.getLogger(ResultatResource.class);

    @Inject
    private ResultatRepository resultatRepository;

    /**
     * POST  /resultats -> Create a new resultat.
     */
    @RequestMapping(value = "/resultats",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void create(@RequestBody Resultat resultat) {
        log.debug("REST request to save Resultat : {}", resultat);
        resultatRepository.save(resultat);
    }

    /**
     * GET  /resultats -> get all the resultats.
     */
    @RequestMapping(value = "/resultats",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<Resultat> getAll() {
        log.debug("REST request to get all Resultats");
        return resultatRepository.findAll();
    }

    /**
     * GET  /resultats/:id -> get the "id" resultat.
     */
    @RequestMapping(value = "/resultats/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Resultat> get(@PathVariable Long id, HttpServletResponse response) {
        log.debug("REST request to get Resultat : {}", id);
        Resultat resultat = resultatRepository.findOne(id);
        if (resultat == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(resultat, HttpStatus.OK);
    }

    /**
     * DELETE  /resultats/:id -> delete the "id" resultat.
     */
    @RequestMapping(value = "/resultats/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void delete(@PathVariable Long id) {
        log.debug("REST request to delete Resultat : {}", id);
        resultatRepository.delete(id);
    }
}
