package fr.softeam.togafquizz.web.rest;

import com.codahale.metrics.annotation.Timed;
import fr.softeam.togafquizz.domain.ReponseStagiaire;
import fr.softeam.togafquizz.repository.ReponseStagiaireRepository;
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
 * REST controller for managing ReponseStagiaire.
 */
@RestController
@RequestMapping("/api")
public class ReponseStagiaireResource {

    private final Logger log = LoggerFactory.getLogger(ReponseStagiaireResource.class);

    @Inject
    private ReponseStagiaireRepository reponseStagiaireRepository;

    /**
     * POST  /reponseStagiaires -> Create a new reponseStagiaire.
     */
    @RequestMapping(value = "/reponseStagiaires",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void create(@RequestBody ReponseStagiaire reponseStagiaire) {
        log.debug("REST request to save ReponseStagiaire : {}", reponseStagiaire);
        reponseStagiaireRepository.save(reponseStagiaire);
    }

    /**
     * GET  /reponseStagiaires -> get all the reponseStagiaires.
     */
    @RequestMapping(value = "/reponseStagiaires",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<ReponseStagiaire> getAll() {
        log.debug("REST request to get all ReponseStagiaires");
        return reponseStagiaireRepository.findAll();
    }

    /**
     * GET  /reponseStagiaires/:id -> get the "id" reponseStagiaire.
     */
    @RequestMapping(value = "/reponseStagiaires/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<ReponseStagiaire> get(@PathVariable Long id, HttpServletResponse response) {
        log.debug("REST request to get ReponseStagiaire : {}", id);
        ReponseStagiaire reponseStagiaire = reponseStagiaireRepository.findOne(id);
        if (reponseStagiaire == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(reponseStagiaire, HttpStatus.OK);
    }

    /**
     * DELETE  /reponseStagiaires/:id -> delete the "id" reponseStagiaire.
     */
    @RequestMapping(value = "/reponseStagiaires/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void delete(@PathVariable Long id) {
        log.debug("REST request to delete ReponseStagiaire : {}", id);
        reponseStagiaireRepository.delete(id);
    }
}
