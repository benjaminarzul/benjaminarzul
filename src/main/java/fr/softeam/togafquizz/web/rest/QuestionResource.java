package fr.softeam.togafquizz.web.rest;

import com.codahale.metrics.annotation.Timed;
import fr.softeam.togafquizz.domain.Question;
import fr.softeam.togafquizz.repository.QuestionRepository;
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
 * REST controller for managing Question.
 */
@RestController
@RequestMapping("/api")
public class QuestionResource {

    private final Logger log = LoggerFactory.getLogger(QuestionResource.class);

    @Inject
    private QuestionRepository questionRepository;

    /**
     * POST  /questions -> Create a new question.
     */
    @RequestMapping(value = "/questions",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void create(@RequestBody Question question) {
        log.debug("REST request to save Question : {}", question);
        questionRepository.save(question);
    }

    /**
     * GET  /questions -> get all the questions.
     */
    @RequestMapping(value = "/questions",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<Question> getAll() {
        log.debug("REST request to get all Questions");
        return questionRepository.findAll();
    }

    /**
     * GET  /questions/:id -> get the "id" question.
     */
    @RequestMapping(value = "/questions/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Question> get(@PathVariable Long id, HttpServletResponse response) {
        log.debug("REST request to get Question : {}", id);
        Question question = questionRepository.findOne(id);
        if (question == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(question, HttpStatus.OK);
    }

    /**
     * DELETE  /questions/:id -> delete the "id" question.
     */
    @RequestMapping(value = "/questions/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void delete(@PathVariable Long id) {
        log.debug("REST request to delete Question : {}", id);
        questionRepository.delete(id);
    }
}
