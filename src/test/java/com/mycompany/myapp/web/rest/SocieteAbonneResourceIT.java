package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.TestKkApp;
import com.mycompany.myapp.domain.SocieteAbonne;
import com.mycompany.myapp.repository.SocieteAbonneRepository;
import com.mycompany.myapp.service.SocieteAbonneService;
import com.mycompany.myapp.service.dto.SocieteAbonneDTO;
import com.mycompany.myapp.service.mapper.SocieteAbonneMapper;
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
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static com.mycompany.myapp.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link SocieteAbonneResource} REST controller.
 */
@SpringBootTest(classes = TestKkApp.class)
public class SocieteAbonneResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Instant DEFAULT_DATE_ABONNEMENT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE_ABONNEMENT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final byte[] DEFAULT_LOGO = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_LOGO = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_LOGO_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_LOGO_CONTENT_TYPE = "image/png";

    @Autowired
    private SocieteAbonneRepository societeAbonneRepository;

    @Autowired
    private SocieteAbonneMapper societeAbonneMapper;

    @Autowired
    private SocieteAbonneService societeAbonneService;

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

    private MockMvc restSocieteAbonneMockMvc;

    private SocieteAbonne societeAbonne;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SocieteAbonneResource societeAbonneResource = new SocieteAbonneResource(societeAbonneService);
        this.restSocieteAbonneMockMvc = MockMvcBuilders.standaloneSetup(societeAbonneResource)
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
    public static SocieteAbonne createEntity(EntityManager em) {
        SocieteAbonne societeAbonne = new SocieteAbonne()
            .name(DEFAULT_NAME)
            .dateAbonnement(DEFAULT_DATE_ABONNEMENT)
            .logo(DEFAULT_LOGO)
            .logoContentType(DEFAULT_LOGO_CONTENT_TYPE);
        return societeAbonne;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SocieteAbonne createUpdatedEntity(EntityManager em) {
        SocieteAbonne societeAbonne = new SocieteAbonne()
            .name(UPDATED_NAME)
            .dateAbonnement(UPDATED_DATE_ABONNEMENT)
            .logo(UPDATED_LOGO)
            .logoContentType(UPDATED_LOGO_CONTENT_TYPE);
        return societeAbonne;
    }

    @BeforeEach
    public void initTest() {
        societeAbonne = createEntity(em);
    }

    @Test
    @Transactional
    public void createSocieteAbonne() throws Exception {
        int databaseSizeBeforeCreate = societeAbonneRepository.findAll().size();

        // Create the SocieteAbonne
        SocieteAbonneDTO societeAbonneDTO = societeAbonneMapper.toDto(societeAbonne);
        restSocieteAbonneMockMvc.perform(post("/api/societe-abonnes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(societeAbonneDTO)))
            .andExpect(status().isCreated());

        // Validate the SocieteAbonne in the database
        List<SocieteAbonne> societeAbonneList = societeAbonneRepository.findAll();
        assertThat(societeAbonneList).hasSize(databaseSizeBeforeCreate + 1);
        SocieteAbonne testSocieteAbonne = societeAbonneList.get(societeAbonneList.size() - 1);
        assertThat(testSocieteAbonne.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testSocieteAbonne.getDateAbonnement()).isEqualTo(DEFAULT_DATE_ABONNEMENT);
        assertThat(testSocieteAbonne.getLogo()).isEqualTo(DEFAULT_LOGO);
        assertThat(testSocieteAbonne.getLogoContentType()).isEqualTo(DEFAULT_LOGO_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void createSocieteAbonneWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = societeAbonneRepository.findAll().size();

        // Create the SocieteAbonne with an existing ID
        societeAbonne.setId(1L);
        SocieteAbonneDTO societeAbonneDTO = societeAbonneMapper.toDto(societeAbonne);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSocieteAbonneMockMvc.perform(post("/api/societe-abonnes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(societeAbonneDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SocieteAbonne in the database
        List<SocieteAbonne> societeAbonneList = societeAbonneRepository.findAll();
        assertThat(societeAbonneList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = societeAbonneRepository.findAll().size();
        // set the field null
        societeAbonne.setName(null);

        // Create the SocieteAbonne, which fails.
        SocieteAbonneDTO societeAbonneDTO = societeAbonneMapper.toDto(societeAbonne);

        restSocieteAbonneMockMvc.perform(post("/api/societe-abonnes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(societeAbonneDTO)))
            .andExpect(status().isBadRequest());

        List<SocieteAbonne> societeAbonneList = societeAbonneRepository.findAll();
        assertThat(societeAbonneList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllSocieteAbonnes() throws Exception {
        // Initialize the database
        societeAbonneRepository.saveAndFlush(societeAbonne);

        // Get all the societeAbonneList
        restSocieteAbonneMockMvc.perform(get("/api/societe-abonnes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(societeAbonne.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].dateAbonnement").value(hasItem(DEFAULT_DATE_ABONNEMENT.toString())))
            .andExpect(jsonPath("$.[*].logoContentType").value(hasItem(DEFAULT_LOGO_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].logo").value(hasItem(Base64Utils.encodeToString(DEFAULT_LOGO))));
    }
    
    @Test
    @Transactional
    public void getSocieteAbonne() throws Exception {
        // Initialize the database
        societeAbonneRepository.saveAndFlush(societeAbonne);

        // Get the societeAbonne
        restSocieteAbonneMockMvc.perform(get("/api/societe-abonnes/{id}", societeAbonne.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(societeAbonne.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.dateAbonnement").value(DEFAULT_DATE_ABONNEMENT.toString()))
            .andExpect(jsonPath("$.logoContentType").value(DEFAULT_LOGO_CONTENT_TYPE))
            .andExpect(jsonPath("$.logo").value(Base64Utils.encodeToString(DEFAULT_LOGO)));
    }

    @Test
    @Transactional
    public void getNonExistingSocieteAbonne() throws Exception {
        // Get the societeAbonne
        restSocieteAbonneMockMvc.perform(get("/api/societe-abonnes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSocieteAbonne() throws Exception {
        // Initialize the database
        societeAbonneRepository.saveAndFlush(societeAbonne);

        int databaseSizeBeforeUpdate = societeAbonneRepository.findAll().size();

        // Update the societeAbonne
        SocieteAbonne updatedSocieteAbonne = societeAbonneRepository.findById(societeAbonne.getId()).get();
        // Disconnect from session so that the updates on updatedSocieteAbonne are not directly saved in db
        em.detach(updatedSocieteAbonne);
        updatedSocieteAbonne
            .name(UPDATED_NAME)
            .dateAbonnement(UPDATED_DATE_ABONNEMENT)
            .logo(UPDATED_LOGO)
            .logoContentType(UPDATED_LOGO_CONTENT_TYPE);
        SocieteAbonneDTO societeAbonneDTO = societeAbonneMapper.toDto(updatedSocieteAbonne);

        restSocieteAbonneMockMvc.perform(put("/api/societe-abonnes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(societeAbonneDTO)))
            .andExpect(status().isOk());

        // Validate the SocieteAbonne in the database
        List<SocieteAbonne> societeAbonneList = societeAbonneRepository.findAll();
        assertThat(societeAbonneList).hasSize(databaseSizeBeforeUpdate);
        SocieteAbonne testSocieteAbonne = societeAbonneList.get(societeAbonneList.size() - 1);
        assertThat(testSocieteAbonne.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testSocieteAbonne.getDateAbonnement()).isEqualTo(UPDATED_DATE_ABONNEMENT);
        assertThat(testSocieteAbonne.getLogo()).isEqualTo(UPDATED_LOGO);
        assertThat(testSocieteAbonne.getLogoContentType()).isEqualTo(UPDATED_LOGO_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingSocieteAbonne() throws Exception {
        int databaseSizeBeforeUpdate = societeAbonneRepository.findAll().size();

        // Create the SocieteAbonne
        SocieteAbonneDTO societeAbonneDTO = societeAbonneMapper.toDto(societeAbonne);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSocieteAbonneMockMvc.perform(put("/api/societe-abonnes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(societeAbonneDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SocieteAbonne in the database
        List<SocieteAbonne> societeAbonneList = societeAbonneRepository.findAll();
        assertThat(societeAbonneList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSocieteAbonne() throws Exception {
        // Initialize the database
        societeAbonneRepository.saveAndFlush(societeAbonne);

        int databaseSizeBeforeDelete = societeAbonneRepository.findAll().size();

        // Delete the societeAbonne
        restSocieteAbonneMockMvc.perform(delete("/api/societe-abonnes/{id}", societeAbonne.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SocieteAbonne> societeAbonneList = societeAbonneRepository.findAll();
        assertThat(societeAbonneList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
