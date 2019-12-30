package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.TestKkApp;
import com.mycompany.myapp.domain.Qcm;
import com.mycompany.myapp.repository.QcmRepository;
import com.mycompany.myapp.service.QcmService;
import com.mycompany.myapp.service.dto.QcmDTO;
import com.mycompany.myapp.service.mapper.QcmMapper;
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
import java.util.List;

import static com.mycompany.myapp.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link QcmResource} REST controller.
 */
@SpringBootTest(classes = TestKkApp.class)
public class QcmResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_NB_QUESTION = 1;
    private static final Integer UPDATED_NB_QUESTION = 2;

    private static final Boolean DEFAULT_PRIVEE = false;
    private static final Boolean UPDATED_PRIVEE = true;

    @Autowired
    private QcmRepository qcmRepository;

    @Autowired
    private QcmMapper qcmMapper;

    @Autowired
    private QcmService qcmService;

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

    private MockMvc restQcmMockMvc;

    private Qcm qcm;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final QcmResource qcmResource = new QcmResource(qcmService);
        this.restQcmMockMvc = MockMvcBuilders.standaloneSetup(qcmResource)
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
    public static Qcm createEntity(EntityManager em) {
        Qcm qcm = new Qcm()
            .name(DEFAULT_NAME)
            .nbQuestion(DEFAULT_NB_QUESTION)
            .privee(DEFAULT_PRIVEE);
        return qcm;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Qcm createUpdatedEntity(EntityManager em) {
        Qcm qcm = new Qcm()
            .name(UPDATED_NAME)
            .nbQuestion(UPDATED_NB_QUESTION)
            .privee(UPDATED_PRIVEE);
        return qcm;
    }

    @BeforeEach
    public void initTest() {
        qcm = createEntity(em);
    }

    @Test
    @Transactional
    public void createQcm() throws Exception {
        int databaseSizeBeforeCreate = qcmRepository.findAll().size();

        // Create the Qcm
        QcmDTO qcmDTO = qcmMapper.toDto(qcm);
        restQcmMockMvc.perform(post("/api/qcms")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(qcmDTO)))
            .andExpect(status().isCreated());

        // Validate the Qcm in the database
        List<Qcm> qcmList = qcmRepository.findAll();
        assertThat(qcmList).hasSize(databaseSizeBeforeCreate + 1);
        Qcm testQcm = qcmList.get(qcmList.size() - 1);
        assertThat(testQcm.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testQcm.getNbQuestion()).isEqualTo(DEFAULT_NB_QUESTION);
        assertThat(testQcm.isPrivee()).isEqualTo(DEFAULT_PRIVEE);
    }

    @Test
    @Transactional
    public void createQcmWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = qcmRepository.findAll().size();

        // Create the Qcm with an existing ID
        qcm.setId(1L);
        QcmDTO qcmDTO = qcmMapper.toDto(qcm);

        // An entity with an existing ID cannot be created, so this API call must fail
        restQcmMockMvc.perform(post("/api/qcms")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(qcmDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Qcm in the database
        List<Qcm> qcmList = qcmRepository.findAll();
        assertThat(qcmList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = qcmRepository.findAll().size();
        // set the field null
        qcm.setName(null);

        // Create the Qcm, which fails.
        QcmDTO qcmDTO = qcmMapper.toDto(qcm);

        restQcmMockMvc.perform(post("/api/qcms")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(qcmDTO)))
            .andExpect(status().isBadRequest());

        List<Qcm> qcmList = qcmRepository.findAll();
        assertThat(qcmList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllQcms() throws Exception {
        // Initialize the database
        qcmRepository.saveAndFlush(qcm);

        // Get all the qcmList
        restQcmMockMvc.perform(get("/api/qcms?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(qcm.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].nbQuestion").value(hasItem(DEFAULT_NB_QUESTION)))
            .andExpect(jsonPath("$.[*].privee").value(hasItem(DEFAULT_PRIVEE.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getQcm() throws Exception {
        // Initialize the database
        qcmRepository.saveAndFlush(qcm);

        // Get the qcm
        restQcmMockMvc.perform(get("/api/qcms/{id}", qcm.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(qcm.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.nbQuestion").value(DEFAULT_NB_QUESTION))
            .andExpect(jsonPath("$.privee").value(DEFAULT_PRIVEE.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingQcm() throws Exception {
        // Get the qcm
        restQcmMockMvc.perform(get("/api/qcms/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateQcm() throws Exception {
        // Initialize the database
        qcmRepository.saveAndFlush(qcm);

        int databaseSizeBeforeUpdate = qcmRepository.findAll().size();

        // Update the qcm
        Qcm updatedQcm = qcmRepository.findById(qcm.getId()).get();
        // Disconnect from session so that the updates on updatedQcm are not directly saved in db
        em.detach(updatedQcm);
        updatedQcm
            .name(UPDATED_NAME)
            .nbQuestion(UPDATED_NB_QUESTION)
            .privee(UPDATED_PRIVEE);
        QcmDTO qcmDTO = qcmMapper.toDto(updatedQcm);

        restQcmMockMvc.perform(put("/api/qcms")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(qcmDTO)))
            .andExpect(status().isOk());

        // Validate the Qcm in the database
        List<Qcm> qcmList = qcmRepository.findAll();
        assertThat(qcmList).hasSize(databaseSizeBeforeUpdate);
        Qcm testQcm = qcmList.get(qcmList.size() - 1);
        assertThat(testQcm.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testQcm.getNbQuestion()).isEqualTo(UPDATED_NB_QUESTION);
        assertThat(testQcm.isPrivee()).isEqualTo(UPDATED_PRIVEE);
    }

    @Test
    @Transactional
    public void updateNonExistingQcm() throws Exception {
        int databaseSizeBeforeUpdate = qcmRepository.findAll().size();

        // Create the Qcm
        QcmDTO qcmDTO = qcmMapper.toDto(qcm);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restQcmMockMvc.perform(put("/api/qcms")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(qcmDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Qcm in the database
        List<Qcm> qcmList = qcmRepository.findAll();
        assertThat(qcmList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteQcm() throws Exception {
        // Initialize the database
        qcmRepository.saveAndFlush(qcm);

        int databaseSizeBeforeDelete = qcmRepository.findAll().size();

        // Delete the qcm
        restQcmMockMvc.perform(delete("/api/qcms/{id}", qcm.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Qcm> qcmList = qcmRepository.findAll();
        assertThat(qcmList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
