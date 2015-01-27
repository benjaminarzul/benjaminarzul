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
import fr.softeam.togafquizz.domain.Question;
import fr.softeam.togafquizz.repository.QuestionRepository;

/**
 * Test class for the QuestionResource REST controller.
 * 
 * @see QuestionResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class QuestionResourceTest {

	private static final int NUMBER_OF_INITIAL_QUESTIONS = 12;

	private static final String DEFAULT_LIBELLE = "SAMPLE_TEXT";
	private static final String UPDATED_LIBELLE = "UPDATED_TEXT";

	private static final Integer DEFAULT_NUMERO = 0;
	private static final Integer UPDATED_NUMERO = 1;

	private static final String DEFAULT_SCENARIO = "SAMPLE_TEXT";
	private static final String UPDATED_SCENARIO = "UPDATED_TEXT";

	private static final String DEFAULT_EXPLICATION = "SAMPLE_TEXT";
	private static final String UPDATED_EXPLICATION = "UPDATED_TEXT";

	@Inject
	private QuestionRepository questionRepository;

	private MockMvc restQuestionMockMvc;

	private Question question;

	@PostConstruct
	public void setup() {
		MockitoAnnotations.initMocks(this);
		QuestionResource questionResource = new QuestionResource();
		ReflectionTestUtils.setField(questionResource, "questionRepository",
				questionRepository);
		this.restQuestionMockMvc = MockMvcBuilders.standaloneSetup(
				questionResource).build();
	}

	@Before
	public void initTest() {
		question = new Question();
		question.setLibelle(DEFAULT_LIBELLE);
		question.setNumero(DEFAULT_NUMERO);
		question.setScenario(DEFAULT_SCENARIO);
		question.setExplication(DEFAULT_EXPLICATION);
	}

	@Test
	@Transactional
	public void createQuestion() throws Exception {
		// Validate the database is empty
		assertThat(questionRepository.findAll()).hasSize(
				NUMBER_OF_INITIAL_QUESTIONS);

		// Create the Question
		restQuestionMockMvc.perform(
				post("/api/questions").contentType(
						TestUtil.APPLICATION_JSON_UTF8).content(
						TestUtil.convertObjectToJsonBytes(question)))
				.andExpect(status().isOk());

		// Validate the Question in the database
		List<Question> questions = questionRepository.findAll();
		assertThat(questions).hasSize(NUMBER_OF_INITIAL_QUESTIONS + 1);

		Question testQuestion = null;
		Iterator<Question> questionIt = questions.iterator();

		while (questionIt.hasNext()) {
			testQuestion = questionIt.next();
		}

		assertThat(testQuestion.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
		assertThat(testQuestion.getNumero()).isEqualTo(DEFAULT_NUMERO);
		assertThat(testQuestion.getScenario()).isEqualTo(DEFAULT_SCENARIO);
		assertThat(testQuestion.getExplication())
				.isEqualTo(DEFAULT_EXPLICATION);
	}

	@Test
	@Transactional
	public void getAllQuestions() throws Exception {
		// Initialize the database
		questionRepository.saveAndFlush(question);

		// Get all the questions
		restQuestionMockMvc
				.perform(get("/api/questions"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(
						jsonPath("$.[" + NUMBER_OF_INITIAL_QUESTIONS + "].id")
								.value(question.getId().intValue()))
				.andExpect(
						jsonPath(
								"$.[" + NUMBER_OF_INITIAL_QUESTIONS
										+ "].libelle").value(
								DEFAULT_LIBELLE.toString()))
				.andExpect(
						jsonPath(
								"$.[" + NUMBER_OF_INITIAL_QUESTIONS
										+ "].numero").value(DEFAULT_NUMERO))
				.andExpect(
						jsonPath(
								"$.[" + NUMBER_OF_INITIAL_QUESTIONS
										+ "].scenario").value(
								DEFAULT_SCENARIO.toString()))
				.andExpect(
						jsonPath(
								"$.[" + NUMBER_OF_INITIAL_QUESTIONS
										+ "].explication").value(
								DEFAULT_EXPLICATION.toString()));
	}

	@Test
	@Transactional
	public void getQuestion() throws Exception {
		// Initialize the database
		questionRepository.saveAndFlush(question);

		// Get the question
		restQuestionMockMvc
				.perform(get("/api/questions/{id}", question.getId()))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id").value(question.getId().intValue()))
				.andExpect(
						jsonPath("$.libelle").value(DEFAULT_LIBELLE.toString()))
				.andExpect(jsonPath("$.numero").value(DEFAULT_NUMERO))
				.andExpect(
						jsonPath("$.scenario").value(
								DEFAULT_SCENARIO.toString()))
				.andExpect(
						jsonPath("$.explication").value(
								DEFAULT_EXPLICATION.toString()));
	}

	@Test
	@Transactional
	public void getNonExistingQuestion() throws Exception {
		// Get the question
		restQuestionMockMvc.perform(get("/api/questions/{id}", 99L)).andExpect(
				status().isNotFound());
	}

	@Test
	@Transactional
	public void updateQuestion() throws Exception {
		// Initialize the database
		questionRepository.saveAndFlush(question);

		// Update the question
		question.setLibelle(UPDATED_LIBELLE);
		question.setNumero(UPDATED_NUMERO);
		question.setScenario(UPDATED_SCENARIO);
		question.setExplication(UPDATED_EXPLICATION);
		restQuestionMockMvc.perform(
				post("/api/questions").contentType(
						TestUtil.APPLICATION_JSON_UTF8).content(
						TestUtil.convertObjectToJsonBytes(question)))
				.andExpect(status().isOk());

		// Validate the Question in the database
		List<Question> questions = questionRepository.findAll();
		assertThat(questions).hasSize(NUMBER_OF_INITIAL_QUESTIONS + 1);

		Question testQuestion = null;
		Iterator<Question> questionIt = questions.iterator();

		while (questionIt.hasNext()) {
			testQuestion = questionIt.next();
		}

		assertThat(testQuestion.getLibelle()).isEqualTo(UPDATED_LIBELLE);
		assertThat(testQuestion.getNumero()).isEqualTo(UPDATED_NUMERO);
		assertThat(testQuestion.getScenario()).isEqualTo(UPDATED_SCENARIO);
		assertThat(testQuestion.getExplication())
				.isEqualTo(UPDATED_EXPLICATION);
	}

	@Test
	@Transactional
	public void deleteQuestion() throws Exception {
		// Initialize the database
		questionRepository.saveAndFlush(question);

		// Get the question
		restQuestionMockMvc.perform(
				delete("/api/questions/{id}", question.getId()).accept(
						TestUtil.APPLICATION_JSON_UTF8)).andExpect(
				status().isOk());

		// Validate the database is empty
		List<Question> questions = questionRepository.findAll();
		assertThat(questions).hasSize(NUMBER_OF_INITIAL_QUESTIONS);
	}
}
