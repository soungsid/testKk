package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.TestKkApp;
import com.mycompany.myapp.domain.QcmReponse;
import com.mycompany.myapp.repository.QcmReponseRepository;
import com.mycompany.myapp.service.QcmReponseService;
import com.mycompany.myapp.service.dto.QcmReponseDTO;
import com.mycompany.myapp.service.mapper.QcmReponseMapper;
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

/**
 * Integration tests for the {@link QcmReponseResource} REST controller.
 */
@SpringBootTest(classes = TestKkApp.class)
public class QcmReponseResourceIT {

    private static final String DEFAULT_REPONSE_TEXT = "AAAAAAAAAA";
    private static final String UPDATED_REPONSE_TEXT = "BBBBBBBBBB";

    private static final byte[] DEFAULT_REPONSE_IMAGE = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_REPONSE_IMAGE = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_REPONSE_IMAGE_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_REPONSE_IMAGE_CONTENT_TYPE = "image/png";

    private static final Double DEFAULT_REPONSE_NOMBRE = 1D;
    private static final Double UPDATED_REPONSE_NOMBRE = 2D;

    private static final Boolean DEFAULT_CORRECT = false;
    private static final Boolean UPDATED_CORRECT = true;

    private static final Long DEFAULT_POIDS = 1L;
    private static final Long UPDATED_POIDS = 2L;

    @Autowired
    private QcmReponseRepository qcmReponseRepository;

    @Autowired
    private QcmReponseMapper qcmReponseMapper;

    @Autowired
    private QcmReponseService qcmReponseService;

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

    private MockMvc restQcmReponseMockMvc;

    private QcmReponse qcmReponse;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final QcmReponseResource qcmReponseResource = new QcmReponseResource(qcmReponseService);
        this.restQcmReponseMockMvc = MockMvcBuilders.standaloneSetup(qcmReponseResource)
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
    public static QcmReponse createEntity(EntityManager em) {
        QcmReponse qcmReponse = new QcmReponse()
            .reponseText(DEFAULT_REPONSE_TEXT)
            .reponseImage(DEFAULT_REPONSE_IMAGE)
            .reponseImageContentType(DEFAULT_REPONSE_IMAGE_CONTENT_TYPE)
            .reponseNombre(DEFAULT_REPONSE_NOMBRE)
            .correct(DEFAULT_CORRECT)
            .poids(DEFAULT_POIDS);
        return qcmReponse;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static QcmReponse createUpdatedEntity(EntityManager em) {
        QcmReponse qcmReponse = new QcmReponse()
            .reponseText(UPDATED_REPONSE_TEXT)
            .reponseImage(UPDATED_REPONSE_IMAGE)
            .reponseImageContentType(UPDATED_REPONSE_IMAGE_CONTENT_TYPE)
            .reponseNombre(UPDATED_REPONSE_NOMBRE)
            .correct(UPDATED_CORRECT)
            .poids(UPDATED_POIDS);
        return qcmReponse;
    }

    @BeforeEach
    public void initTest() {
        qcmReponse = createEntity(em);
    }

    @Test
    @Transactional
    public void createQcmReponse() throws Exception {
        int databaseSizeBeforeCreate = qcmReponseRepository.findAll().size();

        // Create the QcmReponse
        QcmReponseDTO qcmReponseDTO = qcmReponseMapper.toDto(qcmReponse);
        restQcmReponseMockMvc.perform(post("/api/qcm-reponses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(qcmReponseDTO)))
            .andExpect(status().isCreated());

        // Validate the QcmReponse in the database
        List<QcmReponse> qcmReponseList = qcmReponseRepository.findAll();
        assertThat(qcmReponseList).hasSize(databaseSizeBeforeCreate + 1);
        QcmReponse testQcmReponse = qcmReponseList.get(qcmReponseList.size() - 1);
        assertThat(testQcmReponse.getReponseText()).isEqualTo(DEFAULT_REPONSE_TEXT);
        assertThat(testQcmReponse.getReponseImage()).isEqualTo(DEFAULT_REPONSE_IMAGE);
        assertThat(testQcmReponse.getReponseImageContentType()).isEqualTo(DEFAULT_REPONSE_IMAGE_CONTENT_TYPE);
        assertThat(testQcmReponse.getReponseNombre()).isEqualTo(DEFAULT_REPONSE_NOMBRE);
        assertThat(testQcmReponse.isCorrect()).isEqualTo(DEFAULT_CORRECT);
        assertThat(testQcmReponse.getPoids()).isEqualTo(DEFAULT_POIDS);
    }

    @Test
    @Transactional
    public void createQcmReponseWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = qcmReponseRepository.findAll().size();

        // Create the QcmReponse with an existing ID
        qcmReponse.setId(1L);
        QcmReponseDTO qcmReponseDTO = qcmReponseMapper.toDto(qcmReponse);

        // An entity with an existing ID cannot be created, so this API call must fail
        restQcmReponseMockMvc.perform(post("/api/qcm-reponses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(qcmReponseDTO)))
            .andExpect(status().isBadRequest());

        // Validate the QcmReponse in the database
        List<QcmReponse> qcmReponseList = qcmReponseRepository.findAll();
        assertThat(qcmReponseList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllQcmReponses() throws Exception {
        // Initialize the database
        qcmReponseRepository.saveAndFlush(qcmReponse);

        // Get all the qcmReponseList
        restQcmReponseMockMvc.perform(get("/api/qcm-reponses?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(qcmReponse.getId().intValue())))
            .andExpect(jsonPath("$.[*].reponseText").value(hasItem(DEFAULT_REPONSE_TEXT)))
            .andExpect(jsonPath("$.[*].reponseImageContentType").value(hasItem(DEFAULT_REPONSE_IMAGE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].reponseImage").value(hasItem(Base64Utils.encodeToString(DEFAULT_REPONSE_IMAGE))))
            .andExpect(jsonPath("$.[*].reponseNombre").value(hasItem(DEFAULT_REPONSE_NOMBRE.doubleValue())))
            .andExpect(jsonPath("$.[*].correct").value(hasItem(DEFAULT_CORRECT.booleanValue())))
            .andExpect(jsonPath("$.[*].poids").value(hasItem(DEFAULT_POIDS.intValue())));
    }
    
    @Test
    @Transactional
    public void getQcmReponse() throws Exception {
        // Initialize the database
        qcmReponseRepository.saveAndFlush(qcmReponse);

        // Get the qcmReponse
        restQcmReponseMockMvc.perform(get("/api/qcm-reponses/{id}", qcmReponse.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(qcmReponse.getId().intValue()))
            .andExpect(jsonPath("$.reponseText").value(DEFAULT_REPONSE_TEXT))
            .andExpect(jsonPath("$.reponseImageContentType").value(DEFAULT_REPONSE_IMAGE_CONTENT_TYPE))
            .andExpect(jsonPath("$.reponseImage").value(Base64Utils.encodeToString(DEFAULT_REPONSE_IMAGE)))
            .andExpect(jsonPath("$.reponseNombre").value(DEFAULT_REPONSE_NOMBRE.doubleValue()))
            .andExpect(jsonPath("$.correct").value(DEFAULT_CORRECT.booleanValue()))
            .andExpect(jsonPath("$.poids").value(DEFAULT_POIDS.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingQcmReponse() throws Exception {
        // Get the qcmReponse
        restQcmReponseMockMvc.perform(get("/api/qcm-reponses/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateQcmReponse() throws Exception {
        // Initialize the database
        qcmReponseRepository.saveAndFlush(qcmReponse);

        int databaseSizeBeforeUpdate = qcmReponseRepository.findAll().size();

        // Update the qcmReponse
        QcmReponse updatedQcmReponse = qcmReponseRepository.findById(qcmReponse.getId()).get();
        // Disconnect from session so that the updates on updatedQcmReponse are not directly saved in db
        em.detach(updatedQcmReponse);
        updatedQcmReponse
            .reponseText(UPDATED_REPONSE_TEXT)
            .reponseImage(UPDATED_REPONSE_IMAGE)
            .reponseImageContentType(UPDATED_REPONSE_IMAGE_CONTENT_TYPE)
            .reponseNombre(UPDATED_REPONSE_NOMBRE)
            .correct(UPDATED_CORRECT)
            .poids(UPDATED_POIDS);
        QcmReponseDTO qcmReponseDTO = qcmReponseMapper.toDto(updatedQcmReponse);

        restQcmReponseMockMvc.perform(put("/api/qcm-reponses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(qcmReponseDTO)))
            .andExpect(status().isOk());

        // Validate the QcmReponse in the database
        List<QcmReponse> qcmReponseList = qcmReponseRepository.findAll();
        assertThat(qcmReponseList).hasSize(databaseSizeBeforeUpdate);
        QcmReponse testQcmReponse = qcmReponseList.get(qcmReponseList.size() - 1);
        assertThat(testQcmReponse.getReponseText()).isEqualTo(UPDATED_REPONSE_TEXT);
        assertThat(testQcmReponse.getReponseImage()).isEqualTo(UPDATED_REPONSE_IMAGE);
        assertThat(testQcmReponse.getReponseImageContentType()).isEqualTo(UPDATED_REPONSE_IMAGE_CONTENT_TYPE);
        assertThat(testQcmReponse.getReponseNombre()).isEqualTo(UPDATED_REPONSE_NOMBRE);
        assertThat(testQcmReponse.isCorrect()).isEqualTo(UPDATED_CORRECT);
        assertThat(testQcmReponse.getPoids()).isEqualTo(UPDATED_POIDS);
    }

    @Test
    @Transactional
    public void updateNonExistingQcmReponse() throws Exception {
        int databaseSizeBeforeUpdate = qcmReponseRepository.findAll().size();

        // Create the QcmReponse
        QcmReponseDTO qcmReponseDTO = qcmReponseMapper.toDto(qcmReponse);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restQcmReponseMockMvc.perform(put("/api/qcm-reponses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(qcmReponseDTO)))
            .andExpect(status().isBadRequest());

        // Validate the QcmReponse in the database
        List<QcmReponse> qcmReponseList = qcmReponseRepository.findAll();
        assertThat(qcmReponseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteQcmReponse() throws Exception {
        // Initialize the database
        qcmReponseRepository.saveAndFlush(qcmReponse);

        int databaseSizeBeforeDelete = qcmReponseRepository.findAll().size();

        // Delete the qcmReponse
        restQcmReponseMockMvc.perform(delete("/api/qcm-reponses/{id}", qcmReponse.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<QcmReponse> qcmReponseList = qcmReponseRepository.findAll();
        assertThat(qcmReponseList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
