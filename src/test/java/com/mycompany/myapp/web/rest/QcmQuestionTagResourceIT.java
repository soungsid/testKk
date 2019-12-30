package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.TestKkApp;
import com.mycompany.myapp.domain.QcmQuestionTag;
import com.mycompany.myapp.repository.QcmQuestionTagRepository;
import com.mycompany.myapp.service.QcmQuestionTagService;
import com.mycompany.myapp.service.dto.QcmQuestionTagDTO;
import com.mycompany.myapp.service.mapper.QcmQuestionTagMapper;
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
 * Integration tests for the {@link QcmQuestionTagResource} REST controller.
 */
@SpringBootTest(classes = TestKkApp.class)
public class QcmQuestionTagResourceIT {

    @Autowired
    private QcmQuestionTagRepository qcmQuestionTagRepository;

    @Autowired
    private QcmQuestionTagMapper qcmQuestionTagMapper;

    @Autowired
    private QcmQuestionTagService qcmQuestionTagService;

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

    private MockMvc restQcmQuestionTagMockMvc;

    private QcmQuestionTag qcmQuestionTag;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final QcmQuestionTagResource qcmQuestionTagResource = new QcmQuestionTagResource(qcmQuestionTagService);
        this.restQcmQuestionTagMockMvc = MockMvcBuilders.standaloneSetup(qcmQuestionTagResource)
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
    public static QcmQuestionTag createEntity(EntityManager em) {
        QcmQuestionTag qcmQuestionTag = new QcmQuestionTag();
        return qcmQuestionTag;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static QcmQuestionTag createUpdatedEntity(EntityManager em) {
        QcmQuestionTag qcmQuestionTag = new QcmQuestionTag();
        return qcmQuestionTag;
    }

    @BeforeEach
    public void initTest() {
        qcmQuestionTag = createEntity(em);
    }

    @Test
    @Transactional
    public void createQcmQuestionTag() throws Exception {
        int databaseSizeBeforeCreate = qcmQuestionTagRepository.findAll().size();

        // Create the QcmQuestionTag
        QcmQuestionTagDTO qcmQuestionTagDTO = qcmQuestionTagMapper.toDto(qcmQuestionTag);
        restQcmQuestionTagMockMvc.perform(post("/api/qcm-question-tags")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(qcmQuestionTagDTO)))
            .andExpect(status().isCreated());

        // Validate the QcmQuestionTag in the database
        List<QcmQuestionTag> qcmQuestionTagList = qcmQuestionTagRepository.findAll();
        assertThat(qcmQuestionTagList).hasSize(databaseSizeBeforeCreate + 1);
        QcmQuestionTag testQcmQuestionTag = qcmQuestionTagList.get(qcmQuestionTagList.size() - 1);
    }

    @Test
    @Transactional
    public void createQcmQuestionTagWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = qcmQuestionTagRepository.findAll().size();

        // Create the QcmQuestionTag with an existing ID
        qcmQuestionTag.setId(1L);
        QcmQuestionTagDTO qcmQuestionTagDTO = qcmQuestionTagMapper.toDto(qcmQuestionTag);

        // An entity with an existing ID cannot be created, so this API call must fail
        restQcmQuestionTagMockMvc.perform(post("/api/qcm-question-tags")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(qcmQuestionTagDTO)))
            .andExpect(status().isBadRequest());

        // Validate the QcmQuestionTag in the database
        List<QcmQuestionTag> qcmQuestionTagList = qcmQuestionTagRepository.findAll();
        assertThat(qcmQuestionTagList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllQcmQuestionTags() throws Exception {
        // Initialize the database
        qcmQuestionTagRepository.saveAndFlush(qcmQuestionTag);

        // Get all the qcmQuestionTagList
        restQcmQuestionTagMockMvc.perform(get("/api/qcm-question-tags?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(qcmQuestionTag.getId().intValue())));
    }
    
    @Test
    @Transactional
    public void getQcmQuestionTag() throws Exception {
        // Initialize the database
        qcmQuestionTagRepository.saveAndFlush(qcmQuestionTag);

        // Get the qcmQuestionTag
        restQcmQuestionTagMockMvc.perform(get("/api/qcm-question-tags/{id}", qcmQuestionTag.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(qcmQuestionTag.getId().intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingQcmQuestionTag() throws Exception {
        // Get the qcmQuestionTag
        restQcmQuestionTagMockMvc.perform(get("/api/qcm-question-tags/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateQcmQuestionTag() throws Exception {
        // Initialize the database
        qcmQuestionTagRepository.saveAndFlush(qcmQuestionTag);

        int databaseSizeBeforeUpdate = qcmQuestionTagRepository.findAll().size();

        // Update the qcmQuestionTag
        QcmQuestionTag updatedQcmQuestionTag = qcmQuestionTagRepository.findById(qcmQuestionTag.getId()).get();
        // Disconnect from session so that the updates on updatedQcmQuestionTag are not directly saved in db
        em.detach(updatedQcmQuestionTag);
        QcmQuestionTagDTO qcmQuestionTagDTO = qcmQuestionTagMapper.toDto(updatedQcmQuestionTag);

        restQcmQuestionTagMockMvc.perform(put("/api/qcm-question-tags")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(qcmQuestionTagDTO)))
            .andExpect(status().isOk());

        // Validate the QcmQuestionTag in the database
        List<QcmQuestionTag> qcmQuestionTagList = qcmQuestionTagRepository.findAll();
        assertThat(qcmQuestionTagList).hasSize(databaseSizeBeforeUpdate);
        QcmQuestionTag testQcmQuestionTag = qcmQuestionTagList.get(qcmQuestionTagList.size() - 1);
    }

    @Test
    @Transactional
    public void updateNonExistingQcmQuestionTag() throws Exception {
        int databaseSizeBeforeUpdate = qcmQuestionTagRepository.findAll().size();

        // Create the QcmQuestionTag
        QcmQuestionTagDTO qcmQuestionTagDTO = qcmQuestionTagMapper.toDto(qcmQuestionTag);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restQcmQuestionTagMockMvc.perform(put("/api/qcm-question-tags")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(qcmQuestionTagDTO)))
            .andExpect(status().isBadRequest());

        // Validate the QcmQuestionTag in the database
        List<QcmQuestionTag> qcmQuestionTagList = qcmQuestionTagRepository.findAll();
        assertThat(qcmQuestionTagList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteQcmQuestionTag() throws Exception {
        // Initialize the database
        qcmQuestionTagRepository.saveAndFlush(qcmQuestionTag);

        int databaseSizeBeforeDelete = qcmQuestionTagRepository.findAll().size();

        // Delete the qcmQuestionTag
        restQcmQuestionTagMockMvc.perform(delete("/api/qcm-question-tags/{id}", qcmQuestionTag.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<QcmQuestionTag> qcmQuestionTagList = qcmQuestionTagRepository.findAll();
        assertThat(qcmQuestionTagList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
