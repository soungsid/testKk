package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.TestKkApp;
import com.mycompany.myapp.domain.QcmTest;
import com.mycompany.myapp.repository.QcmTestRepository;
import com.mycompany.myapp.service.QcmTestService;
import com.mycompany.myapp.service.dto.QcmTestDTO;
import com.mycompany.myapp.service.mapper.QcmTestMapper;
import com.mycompany.myapp.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static com.mycompany.myapp.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link QcmTestResource} REST controller.
 */
@SpringBootTest(classes = TestKkApp.class)
public class QcmTestResourceIT {

    private static final Instant DEFAULT_DATE_DEBUT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE_DEBUT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_DATE_FIN = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE_FIN = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final Double DEFAULT_SCORE = 1D;
    private static final Double UPDATED_SCORE = 2D;

    private static final Double DEFAULT_HIGH_SCORE = 1D;
    private static final Double UPDATED_HIGH_SCORE = 2D;

    @Autowired
    private QcmTestRepository qcmTestRepository;

    @Autowired
    private QcmTestMapper qcmTestMapper;

    @Autowired
    private QcmTestService qcmTestService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restQcmTestMockMvc;

    private QcmTest qcmTest;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final QcmTestResource qcmTestResource = new QcmTestResource(qcmTestService);
        this.restQcmTestMockMvc = MockMvcBuilders.standaloneSetup(qcmTestResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static QcmTest createEntity(EntityManager em) {
        QcmTest qcmTest = new QcmTest()
            .dateDebut(DEFAULT_DATE_DEBUT)
            .dateFin(DEFAULT_DATE_FIN)
            .email(DEFAULT_EMAIL)
            .score(DEFAULT_SCORE)
            .highScore(DEFAULT_HIGH_SCORE);
        return qcmTest;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static QcmTest createUpdatedEntity(EntityManager em) {
        QcmTest qcmTest = new QcmTest()
            .dateDebut(UPDATED_DATE_DEBUT)
            .dateFin(UPDATED_DATE_FIN)
            .email(UPDATED_EMAIL)
            .score(UPDATED_SCORE)
            .highScore(UPDATED_HIGH_SCORE);
        return qcmTest;
    }

    @BeforeEach
    public void initTest() {
        qcmTest = createEntity(em);
    }

    @Test
    @Transactional
    public void createQcmTest() throws Exception {
        int databaseSizeBeforeCreate = qcmTestRepository.findAll().size();

        // Create the QcmTest
        QcmTestDTO qcmTestDTO = qcmTestMapper.toDto(qcmTest);
        restQcmTestMockMvc.perform(post("/api/qcm-tests")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(qcmTestDTO)))
            .andExpect(status().isCreated());

        // Validate the QcmTest in the database
        List<QcmTest> qcmTestList = qcmTestRepository.findAll();
        assertThat(qcmTestList).hasSize(databaseSizeBeforeCreate + 1);
        QcmTest testQcmTest = qcmTestList.get(qcmTestList.size() - 1);
        assertThat(testQcmTest.getDateDebut()).isEqualTo(DEFAULT_DATE_DEBUT);
        assertThat(testQcmTest.getDateFin()).isEqualTo(DEFAULT_DATE_FIN);
        assertThat(testQcmTest.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testQcmTest.getScore()).isEqualTo(DEFAULT_SCORE);
        assertThat(testQcmTest.getHighScore()).isEqualTo(DEFAULT_HIGH_SCORE);
    }

    @Test
    @Transactional
    public void createQcmTestWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = qcmTestRepository.findAll().size();

        // Create the QcmTest with an existing ID
        qcmTest.setId(1L);
        QcmTestDTO qcmTestDTO = qcmTestMapper.toDto(qcmTest);

        // An entity with an existing ID cannot be created, so this API call must fail
        restQcmTestMockMvc.perform(post("/api/qcm-tests")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(qcmTestDTO)))
            .andExpect(status().isBadRequest());

        // Validate the QcmTest in the database
        List<QcmTest> qcmTestList = qcmTestRepository.findAll();
        assertThat(qcmTestList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllQcmTests() throws Exception {
        // Initialize the database
        qcmTestRepository.saveAndFlush(qcmTest);

        // Get all the qcmTestList
        restQcmTestMockMvc.perform(get("/api/qcm-tests?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(qcmTest.getId().intValue())))
            .andExpect(jsonPath("$.[*].dateDebut").value(hasItem(DEFAULT_DATE_DEBUT.toString())))
            .andExpect(jsonPath("$.[*].dateFin").value(hasItem(DEFAULT_DATE_FIN.toString())))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].score").value(hasItem(DEFAULT_SCORE.doubleValue())))
            .andExpect(jsonPath("$.[*].highScore").value(hasItem(DEFAULT_HIGH_SCORE.doubleValue())));
    }
    
    @Test
    @Transactional
    public void getQcmTest() throws Exception {
        // Initialize the database
        qcmTestRepository.saveAndFlush(qcmTest);

        // Get the qcmTest
        restQcmTestMockMvc.perform(get("/api/qcm-tests/{id}", qcmTest.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(qcmTest.getId().intValue()))
            .andExpect(jsonPath("$.dateDebut").value(DEFAULT_DATE_DEBUT.toString()))
            .andExpect(jsonPath("$.dateFin").value(DEFAULT_DATE_FIN.toString()))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.score").value(DEFAULT_SCORE.doubleValue()))
            .andExpect(jsonPath("$.highScore").value(DEFAULT_HIGH_SCORE.doubleValue()));
    }

    @Test
    @Transactional
    public void getNonExistingQcmTest() throws Exception {
        // Get the qcmTest
        restQcmTestMockMvc.perform(get("/api/qcm-tests/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateQcmTest() throws Exception {
        // Initialize the database
        qcmTestRepository.saveAndFlush(qcmTest);

        int databaseSizeBeforeUpdate = qcmTestRepository.findAll().size();

        // Update the qcmTest
        QcmTest updatedQcmTest = qcmTestRepository.findById(qcmTest.getId()).get();
        // Disconnect from session so that the updates on updatedQcmTest are not directly saved in db
        em.detach(updatedQcmTest);
        updatedQcmTest
            .dateDebut(UPDATED_DATE_DEBUT)
            .dateFin(UPDATED_DATE_FIN)
            .email(UPDATED_EMAIL)
            .score(UPDATED_SCORE)
            .highScore(UPDATED_HIGH_SCORE);
        QcmTestDTO qcmTestDTO = qcmTestMapper.toDto(updatedQcmTest);

        restQcmTestMockMvc.perform(put("/api/qcm-tests")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(qcmTestDTO)))
            .andExpect(status().isOk());

        // Validate the QcmTest in the database
        List<QcmTest> qcmTestList = qcmTestRepository.findAll();
        assertThat(qcmTestList).hasSize(databaseSizeBeforeUpdate);
        QcmTest testQcmTest = qcmTestList.get(qcmTestList.size() - 1);
        assertThat(testQcmTest.getDateDebut()).isEqualTo(UPDATED_DATE_DEBUT);
        assertThat(testQcmTest.getDateFin()).isEqualTo(UPDATED_DATE_FIN);
        assertThat(testQcmTest.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testQcmTest.getScore()).isEqualTo(UPDATED_SCORE);
        assertThat(testQcmTest.getHighScore()).isEqualTo(UPDATED_HIGH_SCORE);
    }

    @Test
    @Transactional
    public void updateNonExistingQcmTest() throws Exception {
        int databaseSizeBeforeUpdate = qcmTestRepository.findAll().size();

        // Create the QcmTest
        QcmTestDTO qcmTestDTO = qcmTestMapper.toDto(qcmTest);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restQcmTestMockMvc.perform(put("/api/qcm-tests")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(qcmTestDTO)))
            .andExpect(status().isBadRequest());

        // Validate the QcmTest in the database
        List<QcmTest> qcmTestList = qcmTestRepository.findAll();
        assertThat(qcmTestList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteQcmTest() throws Exception {
        // Initialize the database
        qcmTestRepository.saveAndFlush(qcmTest);

        int databaseSizeBeforeDelete = qcmTestRepository.findAll().size();

        // Delete the qcmTest
        restQcmTestMockMvc.perform(delete("/api/qcm-tests/{id}", qcmTest.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<QcmTest> qcmTestList = qcmTestRepository.findAll();
        assertThat(qcmTestList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
