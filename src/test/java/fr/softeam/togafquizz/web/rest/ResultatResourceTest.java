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
import fr.softeam.togafquizz.domain.Resultat;
import fr.softeam.togafquizz.repository.ResultatRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
        ReflectionTestUtils.setField(resultatResource, "resultatRepository", resultatRepository);
        this.restResultatMockMvc = MockMvcBuilders.standaloneSetup(resultatResource).build();
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
        assertThat(resultatRepository.findAll()).hasSize(0);

        // Create the Resultat
        restResultatMockMvc.perform(post("/api/resultats")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(resultat)))
                .andExpect(status().isOk());

        // Validate the Resultat in the database
        List<Resultat> resultats = resultatRepository.findAll();
        assertThat(resultats).hasSize(1);
        Resultat testResultat = resultats.iterator().next();
        assertThat(testResultat.getPourcentageReussite()).isEqualTo(DEFAULT_POURCENTAGE_REUSSITE);
    }

    @Test
    @Transactional
    public void getAllResultats() throws Exception {
        // Initialize the database
        resultatRepository.saveAndFlush(resultat);

        // Get all the resultats
        restResultatMockMvc.perform(get("/api/resultats"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[0].id").value(resultat.getId().intValue()))
                .andExpect(jsonPath("$.[0].pourcentageReussite").value(DEFAULT_POURCENTAGE_REUSSITE));
    }

    @Test
    @Transactional
    public void getResultat() throws Exception {
        // Initialize the database
        resultatRepository.saveAndFlush(resultat);

        // Get the resultat
        restResultatMockMvc.perform(get("/api/resultats/{id}", resultat.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(resultat.getId().intValue()))
            .andExpect(jsonPath("$.pourcentageReussite").value(DEFAULT_POURCENTAGE_REUSSITE));
    }

    @Test
    @Transactional
    public void getNonExistingResultat() throws Exception {
        // Get the resultat
        restResultatMockMvc.perform(get("/api/resultats/{id}", 1L))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateResultat() throws Exception {
        // Initialize the database
        resultatRepository.saveAndFlush(resultat);

        // Update the resultat
        resultat.setPourcentageReussite(UPDATED_POURCENTAGE_REUSSITE);
        restResultatMockMvc.perform(post("/api/resultats")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(resultat)))
                .andExpect(status().isOk());

        // Validate the Resultat in the database
        List<Resultat> resultats = resultatRepository.findAll();
        assertThat(resultats).hasSize(1);
        Resultat testResultat = resultats.iterator().next();
        assertThat(testResultat.getPourcentageReussite()).isEqualTo(UPDATED_POURCENTAGE_REUSSITE);
    }

    @Test
    @Transactional
    public void deleteResultat() throws Exception {
        // Initialize the database
        resultatRepository.saveAndFlush(resultat);

        // Get the resultat
        restResultatMockMvc.perform(delete("/api/resultats/{id}", resultat.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Resultat> resultats = resultatRepository.findAll();
        assertThat(resultats).hasSize(0);
    }
}
