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
import fr.softeam.togafquizz.domain.Reponse;
import fr.softeam.togafquizz.repository.ReponseRepository;

/**
 * Test class for the ReponseResource REST controller.
 * 
 * @see ReponseResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class ReponseResourceTest {

	private static final int NUMBER_OF_INITIAL_REPONSES = 4;

	private static final String DEFAULT_LIBELLE = "SAMPLE_TEXT";
	private static final String UPDATED_LIBELLE = "UPDATED_TEXT";

	private static final Integer DEFAULT_NUMERO = 0;
	private static final Integer UPDATED_NUMERO = 1;

	@Inject
	private ReponseRepository reponseRepository;

	private MockMvc restReponseMockMvc;

	private Reponse reponse;

	@PostConstruct
	public void setup() {
		MockitoAnnotations.initMocks(this);
		ReponseResource reponseResource = new ReponseResource();
		ReflectionTestUtils.setField(reponseResource, "reponseRepository",
				reponseRepository);
		this.restReponseMockMvc = MockMvcBuilders.standaloneSetup(
				reponseResource).build();
	}

	@Before
	public void initTest() {
		reponse = new Reponse();
		reponse.setLibelle(DEFAULT_LIBELLE);
		reponse.setNumero(DEFAULT_NUMERO);
	}

	@Test
	@Transactional
	public void createReponse() throws Exception {
		// Validate the database is empty
		assertThat(reponseRepository.findAll()).hasSize(
				NUMBER_OF_INITIAL_REPONSES);

		// Create the Reponse
		restReponseMockMvc.perform(
				post("/api/reponses").contentType(
						TestUtil.APPLICATION_JSON_UTF8).content(
						TestUtil.convertObjectToJsonBytes(reponse))).andExpect(
				status().isOk());

		// Validate the Reponse in the database
		List<Reponse> reponses = reponseRepository.findAll();
		assertThat(reponses).hasSize(NUMBER_OF_INITIAL_REPONSES + 1);

		Reponse testReponse = null;
		Iterator<Reponse> reponseIt = reponses.iterator();

		while (reponseIt.hasNext()) {
			testReponse = reponseIt.next();
		}

		assertThat(testReponse.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
		assertThat(testReponse.getNumero()).isEqualTo(DEFAULT_NUMERO);
	}

	@Test
	@Transactional
	public void getAllReponses() throws Exception {
		// Initialize the database
		reponseRepository.saveAndFlush(reponse);

		// Get all the reponses
		restReponseMockMvc
				.perform(get("/api/reponses"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(
						jsonPath("$.[" + NUMBER_OF_INITIAL_REPONSES + "].id")
								.value(reponse.getId().intValue()))
				.andExpect(
						jsonPath(
								"$.[" + NUMBER_OF_INITIAL_REPONSES
										+ "].libelle").value(
								DEFAULT_LIBELLE.toString()))
				.andExpect(
						jsonPath(
								"$.[" + NUMBER_OF_INITIAL_REPONSES + "].numero")
								.value(DEFAULT_NUMERO));
	}

	@Test
	@Transactional
	public void getReponse() throws Exception {
		// Initialize the database
		reponseRepository.saveAndFlush(reponse);

		// Get the reponse
		restReponseMockMvc
				.perform(get("/api/reponses/{id}", reponse.getId()))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id").value(reponse.getId().intValue()))
				.andExpect(
						jsonPath("$.libelle").value(DEFAULT_LIBELLE.toString()))
				.andExpect(jsonPath("$.numero").value(DEFAULT_NUMERO));
	}

	@Test
	@Transactional
	public void getNonExistingReponse() throws Exception {
		// Get the reponse
		restReponseMockMvc.perform(get("/api/reponses/{id}", 99L)).andExpect(
				status().isNotFound());
	}

	@Test
	@Transactional
	public void updateReponse() throws Exception {
		// Initialize the database
		reponseRepository.saveAndFlush(reponse);

		// Update the reponse
		reponse.setLibelle(UPDATED_LIBELLE);
		reponse.setNumero(UPDATED_NUMERO);
		restReponseMockMvc.perform(
				post("/api/reponses").contentType(
						TestUtil.APPLICATION_JSON_UTF8).content(
						TestUtil.convertObjectToJsonBytes(reponse))).andExpect(
				status().isOk());

		// Validate the Reponse in the database
		List<Reponse> reponses = reponseRepository.findAll();
		assertThat(reponses).hasSize(NUMBER_OF_INITIAL_REPONSES + 1);

		Reponse testReponse = null;
		Iterator<Reponse> reponseIt = reponses.iterator();

		while (reponseIt.hasNext()) {
			testReponse = reponseIt.next();
		}

		assertThat(testReponse.getLibelle()).isEqualTo(UPDATED_LIBELLE);
		assertThat(testReponse.getNumero()).isEqualTo(UPDATED_NUMERO);
	}

	@Test
	@Transactional
	public void deleteReponse() throws Exception {
		// Initialize the database
		reponseRepository.saveAndFlush(reponse);

		// Get the reponse
		restReponseMockMvc.perform(
				delete("/api/reponses/{id}", reponse.getId()).accept(
						TestUtil.APPLICATION_JSON_UTF8)).andExpect(
				status().isOk());

		// Validate the database is empty
		List<Reponse> reponses = reponseRepository.findAll();
		assertThat(reponses).hasSize(NUMBER_OF_INITIAL_REPONSES);
	}
}
