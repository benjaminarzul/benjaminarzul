package fr.softeam.togafquizz.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import fr.softeam.togafquizz.Application;
import fr.softeam.togafquizz.domain.Resultat;
import fr.softeam.togafquizz.repository.ResultatRepository;

/**
 * Test class for the ResultatResource REST controller.
 * 
 * @see ResultatResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class ResultatResourceTest {

	private static final int NUMBER_OF_INITIAL_RESULTATS = 3;

	private static final Integer DEFAULT_POURCENTAGE_REUSSITE = 0;
	private static final Integer UPDATED_POURCENTAGE_REUSSITE = 1;

	@Inject
	private ResultatRepository resultatRepository;

	private MockMvc restResultatMockMvc;

	private Resultat resultat;

	@PostConstruct
	public void setup() {
		MockitoAnnotations.initMocks(this);
		ResultatResource resultatResource = new ResultatResource();
		ReflectionTestUtils.setField(resultatResource, "resultatRepository",
				resultatRepository);
		this.restResultatMockMvc = MockMvcBuilders.standaloneSetup(
				resultatResource).build();
	}

	@Before
	public void initTest() {
		resultat = new Resultat();
		resultat.setPourcentageReussite(DEFAULT_POURCENTAGE_REUSSITE);
	}

	@Test
	@Transactional
	public void createResultat() throws Exception {
		// Validate the database is empty
		assertThat(resultatRepository.findAll()).hasSize(
				NUMBER_OF_INITIAL_RESULTATS);

		// Create the Resultat
		restResultatMockMvc.perform(
				post("/api/resultats").contentType(
						TestUtil.APPLICATION_JSON_UTF8).content(
						TestUtil.convertObjectToJsonBytes(resultat)))
				.andExpect(status().isOk());

		// Validate the Resultat in the database
		List<Resultat> resultats = resultatRepository.findAll();
		assertThat(resultats).hasSize(NUMBER_OF_INITIAL_RESULTATS + 1);

		Resultat testResultat = null;
		Iterator<Resultat> resultatIt = resultats.iterator();

		while (resultatIt.hasNext()) {
			testResultat = resultatIt.next();
		}

		assertThat(testResultat.getPourcentageReussite()).isEqualTo(
				DEFAULT_POURCENTAGE_REUSSITE);
	}

	@Test
	@Transactional
	public void getAllResultats() throws Exception {
		// Initialize the database
		resultatRepository.saveAndFlush(resultat);

		// Get all the resultats
		restResultatMockMvc
				.perform(get("/api/resultats"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(
						jsonPath("$.[" + NUMBER_OF_INITIAL_RESULTATS + "].id")
								.value(resultat.getId().intValue()))
				.andExpect(
						jsonPath(
								"$.[" + NUMBER_OF_INITIAL_RESULTATS
										+ "].pourcentageReussite").value(
								DEFAULT_POURCENTAGE_REUSSITE));
	}

	@Test
	@Transactional
	public void getResultat() throws Exception {
		// Initialize the database
		resultatRepository.saveAndFlush(resultat);

		// Get the resultat
		restResultatMockMvc
				.perform(get("/api/resultats/{id}", resultat.getId()))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id").value(resultat.getId().intValue()))
				.andExpect(
						jsonPath("$.pourcentageReussite").value(
								DEFAULT_POURCENTAGE_REUSSITE));
	}

	@Test
	@Transactional
	public void getNonExistingResultat() throws Exception {
		// Get the resultat
		restResultatMockMvc.perform(get("/api/resultats/{id}", 99L)).andExpect(
				status().isNotFound());
	}

	@Test
	@Transactional
	public void updateResultat() throws Exception {
		// Initialize the database
		resultatRepository.saveAndFlush(resultat);

		// Update the resultat
		resultat.setPourcentageReussite(UPDATED_POURCENTAGE_REUSSITE);
		restResultatMockMvc.perform(
				post("/api/resultats").contentType(
						TestUtil.APPLICATION_JSON_UTF8).content(
						TestUtil.convertObjectToJsonBytes(resultat)))
				.andExpect(status().isOk());

		// Validate the Resultat in the database
		List<Resultat> resultats = resultatRepository.findAll();
		assertThat(resultats).hasSize(NUMBER_OF_INITIAL_RESULTATS + 1);

		Resultat testResultat = null;
		Iterator<Resultat> resultatIt = resultats.iterator();

		while (resultatIt.hasNext()) {
			testResultat = resultatIt.next();
		}

		assertThat(testResultat.getPourcentageReussite()).isEqualTo(
				UPDATED_POURCENTAGE_REUSSITE);
	}

	@Test
	@Transactional
	public void deleteResultat() throws Exception {
		// Initialize the database
		resultatRepository.saveAndFlush(resultat);

		// Get the resultat
		restResultatMockMvc.perform(
				delete("/api/resultats/{id}", resultat.getId()).accept(
						TestUtil.APPLICATION_JSON_UTF8)).andExpect(
				status().isOk());

		// Validate the database is empty
		List<Resultat> resultats = resultatRepository.findAll();
		assertThat(resultats).hasSize(NUMBER_OF_INITIAL_RESULTATS);
	}
}
