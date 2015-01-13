package fr.softeam.togafquizz.web.rest;

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

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.List;

import fr.softeam.togafquizz.Application;
import fr.softeam.togafquizz.domain.Quizz;
import fr.softeam.togafquizz.repository.QuizzRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the QuizzResource REST controller.
 *
 * @see QuizzResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class QuizzResourceTest {

    private static final String DEFAULT_LIBELLE = "SAMPLE_TEXT";
    private static final String UPDATED_LIBELLE = "UPDATED_TEXT";

    private static final Integer DEFAULT_NUMERO = 0;
    private static final Integer UPDATED_NUMERO = 1;

    private static final Integer DEFAULT_DUREE_EN_MINUTES = 0;
    private static final Integer UPDATED_DUREE_EN_MINUTES = 1;

    private static final Integer DEFAULT_TYPE = 0;
    private static final Integer UPDATED_TYPE = 1;

    @Inject
    private QuizzRepository quizzRepository;

    private MockMvc restQuizzMockMvc;

    private Quizz quizz;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        QuizzResource quizzResource = new QuizzResource();
        ReflectionTestUtils.setField(quizzResource, "quizzRepository", quizzRepository);
        this.restQuizzMockMvc = MockMvcBuilders.standaloneSetup(quizzResource).build();
    }

    @Before
    public void initTest() {
        quizz = new Quizz();
        quizz.setLibelle(DEFAULT_LIBELLE);
        quizz.setNumero(DEFAULT_NUMERO);
        quizz.setDureeEnMinutes(DEFAULT_DUREE_EN_MINUTES);
        quizz.setType(DEFAULT_TYPE);
    }

    @Test
    @Transactional
    public void createQuizz() throws Exception {
        // Validate the database is empty
        assertThat(quizzRepository.findAll()).hasSize(0);

        // Create the Quizz
        restQuizzMockMvc.perform(post("/api/quizzs")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(quizz)))
                .andExpect(status().isOk());

        // Validate the Quizz in the database
        List<Quizz> quizzs = quizzRepository.findAll();
        assertThat(quizzs).hasSize(1);
        Quizz testQuizz = quizzs.iterator().next();
        assertThat(testQuizz.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
        assertThat(testQuizz.getNumero()).isEqualTo(DEFAULT_NUMERO);
        assertThat(testQuizz.getDureeEnMinutes()).isEqualTo(DEFAULT_DUREE_EN_MINUTES);
        assertThat(testQuizz.getType()).isEqualTo(DEFAULT_TYPE);
    }

    @Test
    @Transactional
    public void getAllQuizzs() throws Exception {
        // Initialize the database
        quizzRepository.saveAndFlush(quizz);

        // Get all the quizzs
        restQuizzMockMvc.perform(get("/api/quizzs"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[0].id").value(quizz.getId().intValue()))
                .andExpect(jsonPath("$.[0].libelle").value(DEFAULT_LIBELLE.toString()))
                .andExpect(jsonPath("$.[0].numero").value(DEFAULT_NUMERO))
                .andExpect(jsonPath("$.[0].dureeEnMinutes").value(DEFAULT_DUREE_EN_MINUTES))
                .andExpect(jsonPath("$.[0].type").value(DEFAULT_TYPE));
    }

    @Test
    @Transactional
    public void getQuizz() throws Exception {
        // Initialize the database
        quizzRepository.saveAndFlush(quizz);

        // Get the quizz
        restQuizzMockMvc.perform(get("/api/quizzs/{id}", quizz.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(quizz.getId().intValue()))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE.toString()))
            .andExpect(jsonPath("$.numero").value(DEFAULT_NUMERO))
            .andExpect(jsonPath("$.dureeEnMinutes").value(DEFAULT_DUREE_EN_MINUTES))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE));
    }

    @Test
    @Transactional
    public void getNonExistingQuizz() throws Exception {
        // Get the quizz
        restQuizzMockMvc.perform(get("/api/quizzs/{id}", 1L))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateQuizz() throws Exception {
        // Initialize the database
        quizzRepository.saveAndFlush(quizz);

        // Update the quizz
        quizz.setLibelle(UPDATED_LIBELLE);
        quizz.setNumero(UPDATED_NUMERO);
        quizz.setDureeEnMinutes(UPDATED_DUREE_EN_MINUTES);
        quizz.setType(UPDATED_TYPE);
        restQuizzMockMvc.perform(post("/api/quizzs")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(quizz)))
                .andExpect(status().isOk());

        // Validate the Quizz in the database
        List<Quizz> quizzs = quizzRepository.findAll();
        assertThat(quizzs).hasSize(1);
        Quizz testQuizz = quizzs.iterator().next();
        assertThat(testQuizz.getLibelle()).isEqualTo(UPDATED_LIBELLE);
        assertThat(testQuizz.getNumero()).isEqualTo(UPDATED_NUMERO);
        assertThat(testQuizz.getDureeEnMinutes()).isEqualTo(UPDATED_DUREE_EN_MINUTES);
        assertThat(testQuizz.getType()).isEqualTo(UPDATED_TYPE);
    }

    @Test
    @Transactional
    public void deleteQuizz() throws Exception {
        // Initialize the database
        quizzRepository.saveAndFlush(quizz);

        // Get the quizz
        restQuizzMockMvc.perform(delete("/api/quizzs/{id}", quizz.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Quizz> quizzs = quizzRepository.findAll();
        assertThat(quizzs).hasSize(0);
    }
}
