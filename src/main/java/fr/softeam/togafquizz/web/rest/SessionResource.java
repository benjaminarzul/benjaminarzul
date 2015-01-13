package fr.softeam.togafquizz.web.rest;

import com.codahale.metrics.annotation.Timed;
import fr.softeam.togafquizz.domain.Session;
import fr.softeam.togafquizz.repository.SessionRepository;
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
 * REST controller for managing Session.
 */
@RestController
@RequestMapping("/api")
public class SessionResource {

    private final Logger log = LoggerFactory.getLogger(SessionResource.class);

    @Inject
    private SessionRepository sessionRepository;

    /**
     * POST  /sessions -> Create a new session.
     */
    @RequestMapping(value = "/sessions",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void create(@RequestBody Session session) {
        log.debug("REST request to save Session : {}", session);
        sessionRepository.save(session);
    }

    /**
     * GET  /sessions -> get all the sessions.
     */
    @RequestMapping(value = "/sessions",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<Session> getAll() {
        log.debug("REST request to get all Sessions");
        return sessionRepository.findAll();
    }

    /**
     * GET  /sessions/:id -> get the "id" session.
     */
    @RequestMapping(value = "/sessions/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Session> get(@PathVariable Long id, HttpServletResponse response) {
        log.debug("REST request to get Session : {}", id);
        Session session = sessionRepository.findOne(id);
        if (session == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(session, HttpStatus.OK);
    }

    /**
     * DELETE  /sessions/:id -> delete the "id" session.
     */
    @RequestMapping(value = "/sessions/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void delete(@PathVariable Long id) {
        log.debug("REST request to delete Session : {}", id);
        sessionRepository.delete(id);
    }
}
