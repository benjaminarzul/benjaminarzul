package fr.softeam.togafquizz.web.rest;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;

import fr.softeam.togafquizz.domain.Resultat;
import fr.softeam.togafquizz.domain.User;
import fr.softeam.togafquizz.repository.ResultatRepository;
import fr.softeam.togafquizz.repository.UserRepository;
import fr.softeam.togafquizz.security.AuthoritiesConstants;

/**
 * REST contrôleur de gestion des ressources d'un stagiaire.
 */
@RestController
@RequestMapping("/api")
public class StagiaireResource {

	private final Logger log = LoggerFactory.getLogger(StagiaireResource.class);

	@Inject
	private UserRepository userRepository;

	@Inject
	private ResultatRepository resultatRepository;

	/**
	 * GET /stagiaire/resultats -> retourne les résultats aux quizzs du
	 * stagiaire dont le login est passé en paramètre
	 */
	@RequestMapping(value = "/stagiaire/resultats/{login}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	@RolesAllowed({ AuthoritiesConstants.ADMIN, AuthoritiesConstants.STAGIAIRE })
	public ResponseEntity<List<Resultat>> getResultatsStagiaire(
			@PathVariable String login) {
		log.debug(
				"réponse REST de la requête des résultats d'un stagiaire dont le login est {}",
				login);
		User user = this.userRepository.findOneByLogin(login);

		ResponseEntity<List<Resultat>> resultat = null;

		if (user == null) {
			resultat = new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			resultat = new ResponseEntity<>(
					this.resultatRepository.findByUser(user), HttpStatus.OK);
		}

		return resultat;
	}
}
