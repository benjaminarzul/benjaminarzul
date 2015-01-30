package fr.softeam.togafquizz.web.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import fr.softeam.togafquizz.Application;
import fr.softeam.togafquizz.repository.ResultatRepository;
import fr.softeam.togafquizz.repository.UserRepository;

/**
 * Test class for the StagiaireResource REST controller.
 * 
 * @see StagiaireResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class StagiaireResourceTest {

	@Inject
	private UserRepository userRepository;

	@Inject
	private ResultatRepository resultatRepository;

	private MockMvc restStagiaireMockMvc;

	@PostConstruct
	public void setup() {
		MockitoAnnotations.initMocks(this);
		StagiaireResource stagiaireResource = new StagiaireResource();
		ReflectionTestUtils.setField(stagiaireResource, "userRepository",
				this.userRepository);
		ReflectionTestUtils.setField(stagiaireResource, "resultatRepository",
				this.resultatRepository);
		this.restStagiaireMockMvc = MockMvcBuilders.standaloneSetup(
				stagiaireResource).build();
	}

	@Test
	@Transactional
	public void getResultatsStagiaireUnknown() throws Exception {
		// Get the results of an unknown trainee
		this.restStagiaireMockMvc.perform(
				get("/stagiaire/resultats/{login}", "unknown")).andExpect(
				status().isNoContent());
	}

}
