package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.TestKkApp;
import com.mycompany.myapp.domain.QcmQuestion;
import com.mycompany.myapp.repository.QcmQuestionRepository;
import com.mycompany.myapp.service.QcmQuestionService;
import com.mycompany.myapp.service.dto.QcmQuestionDTO;
import com.mycompany.myapp.service.mapper.QcmQuestionMapper;
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
import org.springframework.util.Base64Utils;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;

import static com.mycompany.myapp.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.domain.enumeration.TypeQuestion;
/**
 * Integration tests for the {@link QcmQuestionResource} REST controller.
 */
@SpringBootTest(classes = TestKkApp.class)
public class QcmQuestionResourceIT {

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    private static final TypeQuestion DEFAULT_TYPE = TypeQuestion.YES_NO;
    private static final TypeQuestion UPDATED_TYPE = TypeQuestion.CHOIX_MULTIPLE;

    private static final String DEFAULT_EXPLICATION = "AAAAAAAAAA";
    private static final String UPDATED_EXPLICATION = "BBBBBBBBBB";

    @Autowired
    private QcmQuestionRepository qcmQuestionRepository;

    @Autowired
    private QcmQuestionMapper qcmQuestionMapper;

    @Autowired
    private QcmQuestionService qcmQuestionService;

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

    private MockMvc restQcmQuestionMockMvc;

    private QcmQuestion qcmQuestion;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final QcmQuestionResource qcmQuestionResource = new QcmQuestionResource(qcmQuestionService);
        this.restQcmQuestionMockMvc = MockMvcBuilders.standaloneSetup(qcmQuestionResource)
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
    public static QcmQuestion createEntity(EntityManager em) {
        QcmQuestion qcmQuestion = new QcmQuestion()
            .libelle(DEFAULT_LIBELLE)
            .type(DEFAULT_TYPE)
            .explication(DEFAULT_EXPLICATION);
        return qcmQuestion;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static QcmQuestion createUpdatedEntity(EntityManager em) {
        QcmQuestion qcmQuestion = new QcmQuestion()
            .libelle(UPDATED_LIBELLE)
            .type(UPDATED_TYPE)
            .explication(UPDATED_EXPLICATION);
        return qcmQuestion;
    }

    @BeforeEach
    public void initTest() {
        qcmQuestion = createEntity(em);
    }

    @Test
    @Transactional
    public void createQcmQuestion() throws Exception {
        int databaseSizeBeforeCreate = qcmQuestionRepository.findAll().size();

        // Create the QcmQuestion
        QcmQuestionDTO qcmQuestionDTO = qcmQuestionMapper.toDto(qcmQuestion);
        restQcmQuestionMockMvc.perform(post("/api/qcm-questions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(qcmQuestionDTO)))
            .andExpect(status().isCreated());

        // Validate the QcmQuestion in the database
        List<QcmQuestion> qcmQuestionList = qcmQuestionRepository.findAll();
        assertThat(qcmQuestionList).hasSize(databaseSizeBeforeCreate + 1);
        QcmQuestion testQcmQuestion = qcmQuestionList.get(qcmQuestionList.size() - 1);
        assertThat(testQcmQuestion.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
        assertThat(testQcmQuestion.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testQcmQuestion.getExplication()).isEqualTo(DEFAULT_EXPLICATION);
    }

    @Test
    @Transactional
    public void createQcmQuestionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = qcmQuestionRepository.findAll().size();

        // Create the QcmQuestion with an existing ID
        qcmQuestion.setId(1L);
        QcmQuestionDTO qcmQuestionDTO = qcmQuestionMapper.toDto(qcmQuestion);

        // An entity with an existing ID cannot be created, so this API call must fail
        restQcmQuestionMockMvc.perform(post("/api/qcm-questions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(qcmQuestionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the QcmQuestion in the database
        List<QcmQuestion> qcmQuestionList = qcmQuestionRepository.findAll();
        assertThat(qcmQuestionList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkLibelleIsRequired() throws Exception {
        int databaseSizeBeforeTest = qcmQuestionRepository.findAll().size();
        // set the field null
        qcmQuestion.setLibelle(null);

        // Create the QcmQuestion, which fails.
        QcmQuestionDTO qcmQuestionDTO = qcmQuestionMapper.toDto(qcmQuestion);

        restQcmQuestionMockMvc.perform(post("/api/qcm-questions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(qcmQuestionDTO)))
            .andExpect(status().isBadRequest());

        List<QcmQuestion> qcmQuestionList = qcmQuestionRepository.findAll();
        assertThat(qcmQuestionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllQcmQuestions() throws Exception {
        // Initialize the database
        qcmQuestionRepository.saveAndFlush(qcmQuestion);

        // Get all the qcmQuestionList
        restQcmQuestionMockMvc.perform(get("/api/qcm-questions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(qcmQuestion.getId().intValue())))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE)))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].explication").value(hasItem(DEFAULT_EXPLICATION.toString())));
    }
    
    @Test
    @Transactional
    public void getQcmQuestion() throws Exception {
        // Initialize the database
        qcmQuestionRepository.saveAndFlush(qcmQuestion);

        // Get the qcmQuestion
        restQcmQuestionMockMvc.perform(get("/api/qcm-questions/{id}", qcmQuestion.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(qcmQuestion.getId().intValue()))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()))
            .andExpect(jsonPath("$.explication").value(DEFAULT_EXPLICATION.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingQcmQuestion() throws Exception {
        // Get the qcmQuestion
        restQcmQuestionMockMvc.perform(get("/api/qcm-questions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateQcmQuestion() throws Exception {
        // Initialize the database
        qcmQuestionRepository.saveAndFlush(qcmQuestion);

        int databaseSizeBeforeUpdate = qcmQuestionRepository.findAll().size();

        // Update the qcmQuestion
        QcmQuestion updatedQcmQuestion = qcmQuestionRepository.findById(qcmQuestion.getId()).get();
        // Disconnect from session so that the updates on updatedQcmQuestion are not directly saved in db
        em.detach(updatedQcmQuestion);
        updatedQcmQuestion
            .libelle(UPDATED_LIBELLE)
            .type(UPDATED_TYPE)
            .explication(UPDATED_EXPLICATION);
        QcmQuestionDTO qcmQuestionDTO = qcmQuestionMapper.toDto(updatedQcmQuestion);

        restQcmQuestionMockMvc.perform(put("/api/qcm-questions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(qcmQuestionDTO)))
            .andExpect(status().isOk());

        // Validate the QcmQuestion in the database
        List<QcmQuestion> qcmQuestionList = qcmQuestionRepository.findAll();
        assertThat(qcmQuestionList).hasSize(databaseSizeBeforeUpdate);
        QcmQuestion testQcmQuestion = qcmQuestionList.get(qcmQuestionList.size() - 1);
        assertThat(testQcmQuestion.getLibelle()).isEqualTo(UPDATED_LIBELLE);
        assertThat(testQcmQuestion.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testQcmQuestion.getExplication()).isEqualTo(UPDATED_EXPLICATION);
    }

    @Test
    @Transactional
    public void updateNonExistingQcmQuestion() throws Exception {
        int databaseSizeBeforeUpdate = qcmQuestionRepository.findAll().size();

        // Create the QcmQuestion
        QcmQuestionDTO qcmQuestionDTO = qcmQuestionMapper.toDto(qcmQuestion);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restQcmQuestionMockMvc.perform(put("/api/qcm-questions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(qcmQuestionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the QcmQuestion in the database
        List<QcmQuestion> qcmQuestionList = qcmQuestionRepository.findAll();
        assertThat(qcmQuestionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteQcmQuestion() throws Exception {
        // Initialize the database
        qcmQuestionRepository.saveAndFlush(qcmQuestion);

        int databaseSizeBeforeDelete = qcmQuestionRepository.findAll().size();

        // Delete the qcmQuestion
        restQcmQuestionMockMvc.perform(delete("/api/qcm-questions/{id}", qcmQuestion.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<QcmQuestion> qcmQuestionList = qcmQuestionRepository.findAll();
        assertThat(qcmQuestionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
