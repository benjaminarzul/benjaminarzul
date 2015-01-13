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
import fr.softeam.togafquizz.domain.ReponseStagiaire;
import fr.softeam.togafquizz.repository.ReponseStagiaireRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the ReponseStagiaireResource REST controller.
 *
 * @see ReponseStagiaireResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class ReponseStagiaireResourceTest {


    @Inject
    private ReponseStagiaireRepository reponseStagiaireRepository;

    private MockMvc restReponseStagiaireMockMvc;

    private ReponseStagiaire reponseStagiaire;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ReponseStagiaireResource reponseStagiaireResource = new ReponseStagiaireResource();
        ReflectionTestUtils.setField(reponseStagiaireResource, "reponseStagiaireRepository", reponseStagiaireRepository);
        this.restReponseStagiaireMockMvc = MockMvcBuilders.standaloneSetup(reponseStagiaireResource).build();
    }

    @Before
    public void initTest() {
        reponseStagiaire = new ReponseStagiaire();
    }

    @Test
    @Transactional
    public void createReponseStagiaire() throws Exception {
        // Validate the database is empty
        assertThat(reponseStagiaireRepository.findAll()).hasSize(0);

        // Create the ReponseStagiaire
        restReponseStagiaireMockMvc.perform(post("/api/reponseStagiaires")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(reponseStagiaire)))
                .andExpect(status().isOk());

        // Validate the ReponseStagiaire in the database
        List<ReponseStagiaire> reponseStagiaires = reponseStagiaireRepository.findAll();
        assertThat(reponseStagiaires).hasSize(1);
        ReponseStagiaire testReponseStagiaire = reponseStagiaires.iterator().next();
    }

    @Test
    @Transactional
    public void getAllReponseStagiaires() throws Exception {
        // Initialize the database
        reponseStagiaireRepository.saveAndFlush(reponseStagiaire);

        // Get all the reponseStagiaires
        restReponseStagiaireMockMvc.perform(get("/api/reponseStagiaires"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[0].id").value(reponseStagiaire.getId().intValue()));
    }

    @Test
    @Transactional
    public void getReponseStagiaire() throws Exception {
        // Initialize the database
        reponseStagiaireRepository.saveAndFlush(reponseStagiaire);

        // Get the reponseStagiaire
        restReponseStagiaireMockMvc.perform(get("/api/reponseStagiaires/{id}", reponseStagiaire.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(reponseStagiaire.getId().intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingReponseStagiaire() throws Exception {
        // Get the reponseStagiaire
        restReponseStagiaireMockMvc.perform(get("/api/reponseStagiaires/{id}", 1L))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateReponseStagiaire() throws Exception {
        // Initialize the database
        reponseStagiaireRepository.saveAndFlush(reponseStagiaire);

        // Update the reponseStagiaire
        restReponseStagiaireMockMvc.perform(post("/api/reponseStagiaires")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(reponseStagiaire)))
                .andExpect(status().isOk());

        // Validate the ReponseStagiaire in the database
        List<ReponseStagiaire> reponseStagiaires = reponseStagiaireRepository.findAll();
        assertThat(reponseStagiaires).hasSize(1);
        ReponseStagiaire testReponseStagiaire = reponseStagiaires.iterator().next();
    }

    @Test
    @Transactional
    public void deleteReponseStagiaire() throws Exception {
        // Initialize the database
        reponseStagiaireRepository.saveAndFlush(reponseStagiaire);

        // Get the reponseStagiaire
        restReponseStagiaireMockMvc.perform(delete("/api/reponseStagiaires/{id}", reponseStagiaire.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<ReponseStagiaire> reponseStagiaires = reponseStagiaireRepository.findAll();
        assertThat(reponseStagiaires).hasSize(0);
    }
}
