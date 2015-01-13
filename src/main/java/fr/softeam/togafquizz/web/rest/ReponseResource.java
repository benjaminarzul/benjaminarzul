package fr.softeam.togafquizz.web.rest;

import com.codahale.metrics.annotation.Timed;
import fr.softeam.togafquizz.domain.Reponse;
import fr.softeam.togafquizz.repository.ReponseRepository;
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
 * REST controller for managing Reponse.
 */
@RestController
@RequestMapping("/api")
public class ReponseResource {

    private final Logger log = LoggerFactory.getLogger(ReponseResource.class);

    @Inject
    private ReponseRepository reponseRepository;

    /**
     * POST  /reponses -> Create a new reponse.
     */
    @RequestMapping(value = "/reponses",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void create(@RequestBody Reponse reponse) {
        log.debug("REST request to save Reponse : {}", reponse);
        reponseRepository.save(reponse);
    }

    /**
     * GET  /reponses -> get all the reponses.
     */
    @RequestMapping(value = "/reponses",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<Reponse> getAll() {
        log.debug("REST request to get all Reponses");
        return reponseRepository.findAll();
    }

    /**
     * GET  /reponses/:id -> get the "id" reponse.
     */
    @RequestMapping(value = "/reponses/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Reponse> get(@PathVariable Long id, HttpServletResponse response) {
        log.debug("REST request to get Reponse : {}", id);
        Reponse reponse = reponseRepository.findOne(id);
        if (reponse == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(reponse, HttpStatus.OK);
    }

    /**
     * DELETE  /reponses/:id -> delete the "id" reponse.
     */
    @RequestMapping(value = "/reponses/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void delete(@PathVariable Long id) {
        log.debug("REST request to delete Reponse : {}", id);
        reponseRepository.delete(id);
    }
}
