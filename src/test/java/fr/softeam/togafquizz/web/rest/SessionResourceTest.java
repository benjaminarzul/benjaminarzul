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

import org.joda.time.LocalDate;
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
import fr.softeam.togafquizz.domain.Session;
import fr.softeam.togafquizz.repository.SessionRepository;

/**
 * Test class for the SessionResource REST controller.
 * 
 * @see SessionResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class SessionResourceTest {

	private static final int NUMBER_OF_INITIAL_SESSIONS = 1;

	private static final LocalDate DEFAULT_DEBUT = new LocalDate(0L);
	private static final LocalDate UPDATED_DEBUT = new LocalDate();

	private static final LocalDate DEFAULT_FIN = new LocalDate(0L);
	private static final LocalDate UPDATED_FIN = new LocalDate();

	@Inject
	private SessionRepository sessionRepository;

	private MockMvc restSessionMockMvc;

	private Session session;

	@PostConstruct
	public void setup() {
		MockitoAnnotations.initMocks(this);
		SessionResource sessionResource = new SessionResource();
		ReflectionTestUtils.setField(sessionResource, "sessionRepository",
				sessionRepository);
		this.restSessionMockMvc = MockMvcBuilders.standaloneSetup(
				sessionResource).build();
	}

	@Before
	public void initTest() {
		session = new Session();
		session.setDebut(DEFAULT_DEBUT);
		session.setFin(DEFAULT_FIN);
	}

	@Test
	@Transactional
	public void createSession() throws Exception {
		// Validate the database is empty
		assertThat(sessionRepository.findAll()).hasSize(
				NUMBER_OF_INITIAL_SESSIONS);

		// Create the Session
		restSessionMockMvc.perform(
				post("/api/sessions").contentType(
						TestUtil.APPLICATION_JSON_UTF8).content(
						TestUtil.convertObjectToJsonBytes(session))).andExpect(
				status().isOk());

		// Validate the Session in the database
		List<Session> sessions = sessionRepository.findAll();
		assertThat(sessions).hasSize(NUMBER_OF_INITIAL_SESSIONS + 1);

		Session testSession = null;
		Iterator<Session> sessionIt = sessions.iterator();

		while (sessionIt.hasNext()) {
			testSession = sessionIt.next();
		}

		assertThat(testSession.getDebut()).isEqualTo(DEFAULT_DEBUT);
		assertThat(testSession.getFin()).isEqualTo(DEFAULT_FIN);
	}

	@Test
	@Transactional
	public void getAllSessions() throws Exception {
		// Initialize the database
		sessionRepository.saveAndFlush(session);

		// Get all the sessions
		restSessionMockMvc
				.perform(get("/api/sessions"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(
						jsonPath("$.[" + NUMBER_OF_INITIAL_SESSIONS + "].id")
								.value(session.getId().intValue()))
				.andExpect(
						jsonPath("$.[" + NUMBER_OF_INITIAL_SESSIONS + "].debut")
								.value(DEFAULT_DEBUT.toString()))
				.andExpect(
						jsonPath("$.[" + NUMBER_OF_INITIAL_SESSIONS + "].fin")
								.value(DEFAULT_FIN.toString()));
	}

	@Test
	@Transactional
	public void getSession() throws Exception {
		// Initialize the database
		sessionRepository.saveAndFlush(session);

		// Get the session
		restSessionMockMvc.perform(get("/api/sessions/{id}", session.getId()))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id").value(session.getId().intValue()))
				.andExpect(jsonPath("$.debut").value(DEFAULT_DEBUT.toString()))
				.andExpect(jsonPath("$.fin").value(DEFAULT_FIN.toString()));
	}

	@Test
	@Transactional
	public void getNonExistingSession() throws Exception {
		// Get the session
		restSessionMockMvc.perform(get("/api/sessions/{id}", 99L)).andExpect(
				status().isNotFound());
	}

	@Test
	@Transactional
	public void updateSession() throws Exception {
		// Initialize the database
		sessionRepository.saveAndFlush(session);

		// Update the session
		session.setDebut(UPDATED_DEBUT);
		session.setFin(UPDATED_FIN);
		restSessionMockMvc.perform(
				post("/api/sessions").contentType(
						TestUtil.APPLICATION_JSON_UTF8).content(
						TestUtil.convertObjectToJsonBytes(session))).andExpect(
				status().isOk());

		// Validate the Session in the database
		List<Session> sessions = sessionRepository.findAll();
		assertThat(sessions).hasSize(NUMBER_OF_INITIAL_SESSIONS + 1);

		Session testSession = null;
		Iterator<Session> sessionIt = sessions.iterator();

		while (sessionIt.hasNext()) {
			testSession = sessionIt.next();
		}

		assertThat(testSession.getDebut()).isEqualTo(UPDATED_DEBUT);
		assertThat(testSession.getFin()).isEqualTo(UPDATED_FIN);
	}

	@Test
	@Transactional
	public void deleteSession() throws Exception {
		// Initialize the database
		sessionRepository.saveAndFlush(session);

		// Get the session
		restSessionMockMvc.perform(
				delete("/api/sessions/{id}", session.getId()).accept(
						TestUtil.APPLICATION_JSON_UTF8)).andExpect(
				status().isOk());

		// Validate the database is empty
		List<Session> sessions = sessionRepository.findAll();
		assertThat(sessions).hasSize(NUMBER_OF_INITIAL_SESSIONS);
	}
}
