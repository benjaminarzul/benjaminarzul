package fr.softeam.togafquizz.web.rest;

import java.io.InputStream;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;

import fr.softeam.togafquizz.domain.Quizz;
import fr.softeam.togafquizz.repository.QuizzRepository;
import fr.softeam.togafquizz.service.QuizzService;

/**
 * REST controller for managing Quizz.
 */
@RestController
@RequestMapping("/api")
public class QuizzResource {

	private final Logger log = LoggerFactory.getLogger(QuizzResource.class);

	@Inject
	private QuizzRepository quizzRepository;

	@Inject
	private QuizzService quizzService;

	/**
	 * POST /quizzs -> Create a new quizz.
	 */
	@RequestMapping(value = "/quizzs", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public void create(@RequestBody Quizz quizz) {
		log.debug("REST request to save Quizz : {}", quizz);
		quizzRepository.save(quizz);
	}

	/**
	 * GET /quizzs -> get all the quizzs.
	 */
	@RequestMapping(value = "/quizzs", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public List<Quizz> getAll() {
		log.debug("REST request to get all Quizzs");

		// trier par numéro croissant
		return quizzRepository.findAll(new Sort("numero"));
	}

	/**
	 * GET /quizzs/:id -> get the "id" quizz.
	 */
	@RequestMapping(value = "/quizzs/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public ResponseEntity<Quizz> get(@PathVariable Long id,
			HttpServletResponse response) {
		log.debug("REST request to get Quizz : {}", id);
		Quizz quizz = quizzRepository.findOne(id);
		if (quizz == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(quizz, HttpStatus.OK);
	}

	/**
	 * DELETE /quizzs/:id -> delete the "id" quizz.
	 */
	@RequestMapping(value = "/quizzs/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public void delete(@PathVariable Long id) {
		log.debug("REST request to delete Quizz : {}", id);
		quizzRepository.delete(id);
	}

	/**
	 * POST /quizzs/upload -> Upload CSV files
	 */
	@RequestMapping(value = "/quizzs/upload", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public List<Quizz> upload(InputStream uploadFile) {
		return quizzRepository.findAll();
		// TODO
		// log.error("REST request to upload CSV file {}", uploadFile);
		// try {
		// BufferedReader in = new BufferedReader(new
		// InputStreamReader(uploadFile));
		// String line = in.readLine();
		// log.error("ici {}", line);
		// while((line = in.readLine()) != null){
		// log.error("ici {}", line);
		// quizzRepository.save(quizzService.createQuizzFromCSV(line));
		// }
		// } catch (IOException e) {
		// log.error("");
		// }
	}
}
