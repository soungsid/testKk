package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.TestKkApp;
import com.mycompany.myapp.domain.QcmTestResponse;
import com.mycompany.myapp.repository.QcmTestResponseRepository;
import com.mycompany.myapp.service.QcmTestResponseService;
import com.mycompany.myapp.service.dto.QcmTestResponseDTO;
import com.mycompany.myapp.service.mapper.QcmTestResponseMapper;
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
 * Integration tests for the {@link QcmTestResponseResource} REST controller.
 */
@SpringBootTest(classes = TestKkApp.class)
public class QcmTestResponseResourceIT {

    private static final Instant DEFAULT_DATE_SOUMISSION = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE_SOUMISSION = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private QcmTestResponseRepository qcmTestResponseRepository;

    @Autowired
    private QcmTestResponseMapper qcmTestResponseMapper;

    @Autowired
    private QcmTestResponseService qcmTestResponseService;

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

    private MockMvc restQcmTestResponseMockMvc;

    private QcmTestResponse qcmTestResponse;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final QcmTestResponseResource qcmTestResponseResource = new QcmTestResponseResource(qcmTestResponseService);
        this.restQcmTestResponseMockMvc = MockMvcBuilders.standaloneSetup(qcmTestResponseResource)
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
    public static QcmTestResponse createEntity(EntityManager em) {
        QcmTestResponse qcmTestResponse = new QcmTestResponse()
            .dateSoumission(DEFAULT_DATE_SOUMISSION);
        return qcmTestResponse;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static QcmTestResponse createUpdatedEntity(EntityManager em) {
        QcmTestResponse qcmTestResponse = new QcmTestResponse()
            .dateSoumission(UPDATED_DATE_SOUMISSION);
        return qcmTestResponse;
    }

    @BeforeEach
    public void initTest() {
        qcmTestResponse = createEntity(em);
    }

    @Test
    @Transactional
    public void createQcmTestResponse() throws Exception {
        int databaseSizeBeforeCreate = qcmTestResponseRepository.findAll().size();

        // Create the QcmTestResponse
        QcmTestResponseDTO qcmTestResponseDTO = qcmTestResponseMapper.toDto(qcmTestResponse);
        restQcmTestResponseMockMvc.perform(post("/api/qcm-test-responses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(qcmTestResponseDTO)))
            .andExpect(status().isCreated());

        // Validate the QcmTestResponse in the database
        List<QcmTestResponse> qcmTestResponseList = qcmTestResponseRepository.findAll();
        assertThat(qcmTestResponseList).hasSize(databaseSizeBeforeCreate + 1);
        QcmTestResponse testQcmTestResponse = qcmTestResponseList.get(qcmTestResponseList.size() - 1);
        assertThat(testQcmTestResponse.getDateSoumission()).isEqualTo(DEFAULT_DATE_SOUMISSION);
    }

    @Test
    @Transactional
    public void createQcmTestResponseWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = qcmTestResponseRepository.findAll().size();

        // Create the QcmTestResponse with an existing ID
        qcmTestResponse.setId(1L);
        QcmTestResponseDTO qcmTestResponseDTO = qcmTestResponseMapper.toDto(qcmTestResponse);

        // An entity with an existing ID cannot be created, so this API call must fail
        restQcmTestResponseMockMvc.perform(post("/api/qcm-test-responses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(qcmTestResponseDTO)))
            .andExpect(status().isBadRequest());

        // Validate the QcmTestResponse in the database
        List<QcmTestResponse> qcmTestResponseList = qcmTestResponseRepository.findAll();
        assertThat(qcmTestResponseList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllQcmTestResponses() throws Exception {
        // Initialize the database
        qcmTestResponseRepository.saveAndFlush(qcmTestResponse);

        // Get all the qcmTestResponseList
        restQcmTestResponseMockMvc.perform(get("/api/qcm-test-responses?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(qcmTestResponse.getId().intValue())))
            .andExpect(jsonPath("$.[*].dateSoumission").value(hasItem(DEFAULT_DATE_SOUMISSION.toString())));
    }
    
    @Test
    @Transactional
    public void getQcmTestResponse() throws Exception {
        // Initialize the database
        qcmTestResponseRepository.saveAndFlush(qcmTestResponse);

        // Get the qcmTestResponse
        restQcmTestResponseMockMvc.perform(get("/api/qcm-test-responses/{id}", qcmTestResponse.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(qcmTestResponse.getId().intValue()))
            .andExpect(jsonPath("$.dateSoumission").value(DEFAULT_DATE_SOUMISSION.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingQcmTestResponse() throws Exception {
        // Get the qcmTestResponse
        restQcmTestResponseMockMvc.perform(get("/api/qcm-test-responses/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateQcmTestResponse() throws Exception {
        // Initialize the database
        qcmTestResponseRepository.saveAndFlush(qcmTestResponse);

        int databaseSizeBeforeUpdate = qcmTestResponseRepository.findAll().size();

        // Update the qcmTestResponse
        QcmTestResponse updatedQcmTestResponse = qcmTestResponseRepository.findById(qcmTestResponse.getId()).get();
        // Disconnect from session so that the updates on updatedQcmTestResponse are not directly saved in db
        em.detach(updatedQcmTestResponse);
        updatedQcmTestResponse
            .dateSoumission(UPDATED_DATE_SOUMISSION);
        QcmTestResponseDTO qcmTestResponseDTO = qcmTestResponseMapper.toDto(updatedQcmTestResponse);

        restQcmTestResponseMockMvc.perform(put("/api/qcm-test-responses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(qcmTestResponseDTO)))
            .andExpect(status().isOk());

        // Validate the QcmTestResponse in the database
        List<QcmTestResponse> qcmTestResponseList = qcmTestResponseRepository.findAll();
        assertThat(qcmTestResponseList).hasSize(databaseSizeBeforeUpdate);
        QcmTestResponse testQcmTestResponse = qcmTestResponseList.get(qcmTestResponseList.size() - 1);
        assertThat(testQcmTestResponse.getDateSoumission()).isEqualTo(UPDATED_DATE_SOUMISSION);
    }

    @Test
    @Transactional
    public void updateNonExistingQcmTestResponse() throws Exception {
        int databaseSizeBeforeUpdate = qcmTestResponseRepository.findAll().size();

        // Create the QcmTestResponse
        QcmTestResponseDTO qcmTestResponseDTO = qcmTestResponseMapper.toDto(qcmTestResponse);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restQcmTestResponseMockMvc.perform(put("/api/qcm-test-responses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(qcmTestResponseDTO)))
            .andExpect(status().isBadRequest());

        // Validate the QcmTestResponse in the database
        List<QcmTestResponse> qcmTestResponseList = qcmTestResponseRepository.findAll();
        assertThat(qcmTestResponseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteQcmTestResponse() throws Exception {
        // Initialize the database
        qcmTestResponseRepository.saveAndFlush(qcmTestResponse);

        int databaseSizeBeforeDelete = qcmTestResponseRepository.findAll().size();

        // Delete the qcmTestResponse
        restQcmTestResponseMockMvc.perform(delete("/api/qcm-test-responses/{id}", qcmTestResponse.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<QcmTestResponse> qcmTestResponseList = qcmTestResponseRepository.findAll();
        assertThat(qcmTestResponseList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
