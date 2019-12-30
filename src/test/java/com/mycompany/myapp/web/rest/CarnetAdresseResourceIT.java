package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.TestKkApp;
import com.mycompany.myapp.domain.CarnetAdresse;
import com.mycompany.myapp.repository.CarnetAdresseRepository;
import com.mycompany.myapp.service.CarnetAdresseService;
import com.mycompany.myapp.service.dto.CarnetAdresseDTO;
import com.mycompany.myapp.service.mapper.CarnetAdresseMapper;
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
 * Integration tests for the {@link CarnetAdresseResource} REST controller.
 */
@SpringBootTest(classes = TestKkApp.class)
public class CarnetAdresseResourceIT {

    private static final String DEFAULT_LASTNAME = "AAAAAAAAAA";
    private static final String UPDATED_LASTNAME = "BBBBBBBBBB";

    private static final String DEFAULT_FIRSTNAME = "AAAAAAAAAA";
    private static final String UPDATED_FIRSTNAME = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    @Autowired
    private CarnetAdresseRepository carnetAdresseRepository;

    @Autowired
    private CarnetAdresseMapper carnetAdresseMapper;

    @Autowired
    private CarnetAdresseService carnetAdresseService;

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

    private MockMvc restCarnetAdresseMockMvc;

    private CarnetAdresse carnetAdresse;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CarnetAdresseResource carnetAdresseResource = new CarnetAdresseResource(carnetAdresseService);
        this.restCarnetAdresseMockMvc = MockMvcBuilders.standaloneSetup(carnetAdresseResource)
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
    public static CarnetAdresse createEntity(EntityManager em) {
        CarnetAdresse carnetAdresse = new CarnetAdresse()
            .lastname(DEFAULT_LASTNAME)
            .firstname(DEFAULT_FIRSTNAME)
            .email(DEFAULT_EMAIL);
        return carnetAdresse;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CarnetAdresse createUpdatedEntity(EntityManager em) {
        CarnetAdresse carnetAdresse = new CarnetAdresse()
            .lastname(UPDATED_LASTNAME)
            .firstname(UPDATED_FIRSTNAME)
            .email(UPDATED_EMAIL);
        return carnetAdresse;
    }

    @BeforeEach
    public void initTest() {
        carnetAdresse = createEntity(em);
    }

    @Test
    @Transactional
    public void createCarnetAdresse() throws Exception {
        int databaseSizeBeforeCreate = carnetAdresseRepository.findAll().size();

        // Create the CarnetAdresse
        CarnetAdresseDTO carnetAdresseDTO = carnetAdresseMapper.toDto(carnetAdresse);
        restCarnetAdresseMockMvc.perform(post("/api/carnet-adresses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(carnetAdresseDTO)))
            .andExpect(status().isCreated());

        // Validate the CarnetAdresse in the database
        List<CarnetAdresse> carnetAdresseList = carnetAdresseRepository.findAll();
        assertThat(carnetAdresseList).hasSize(databaseSizeBeforeCreate + 1);
        CarnetAdresse testCarnetAdresse = carnetAdresseList.get(carnetAdresseList.size() - 1);
        assertThat(testCarnetAdresse.getLastname()).isEqualTo(DEFAULT_LASTNAME);
        assertThat(testCarnetAdresse.getFirstname()).isEqualTo(DEFAULT_FIRSTNAME);
        assertThat(testCarnetAdresse.getEmail()).isEqualTo(DEFAULT_EMAIL);
    }

    @Test
    @Transactional
    public void createCarnetAdresseWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = carnetAdresseRepository.findAll().size();

        // Create the CarnetAdresse with an existing ID
        carnetAdresse.setId(1L);
        CarnetAdresseDTO carnetAdresseDTO = carnetAdresseMapper.toDto(carnetAdresse);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCarnetAdresseMockMvc.perform(post("/api/carnet-adresses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(carnetAdresseDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CarnetAdresse in the database
        List<CarnetAdresse> carnetAdresseList = carnetAdresseRepository.findAll();
        assertThat(carnetAdresseList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllCarnetAdresses() throws Exception {
        // Initialize the database
        carnetAdresseRepository.saveAndFlush(carnetAdresse);

        // Get all the carnetAdresseList
        restCarnetAdresseMockMvc.perform(get("/api/carnet-adresses?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(carnetAdresse.getId().intValue())))
            .andExpect(jsonPath("$.[*].lastname").value(hasItem(DEFAULT_LASTNAME)))
            .andExpect(jsonPath("$.[*].firstname").value(hasItem(DEFAULT_FIRSTNAME)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)));
    }
    
    @Test
    @Transactional
    public void getCarnetAdresse() throws Exception {
        // Initialize the database
        carnetAdresseRepository.saveAndFlush(carnetAdresse);

        // Get the carnetAdresse
        restCarnetAdresseMockMvc.perform(get("/api/carnet-adresses/{id}", carnetAdresse.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(carnetAdresse.getId().intValue()))
            .andExpect(jsonPath("$.lastname").value(DEFAULT_LASTNAME))
            .andExpect(jsonPath("$.firstname").value(DEFAULT_FIRSTNAME))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL));
    }

    @Test
    @Transactional
    public void getNonExistingCarnetAdresse() throws Exception {
        // Get the carnetAdresse
        restCarnetAdresseMockMvc.perform(get("/api/carnet-adresses/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCarnetAdresse() throws Exception {
        // Initialize the database
        carnetAdresseRepository.saveAndFlush(carnetAdresse);

        int databaseSizeBeforeUpdate = carnetAdresseRepository.findAll().size();

        // Update the carnetAdresse
        CarnetAdresse updatedCarnetAdresse = carnetAdresseRepository.findById(carnetAdresse.getId()).get();
        // Disconnect from session so that the updates on updatedCarnetAdresse are not directly saved in db
        em.detach(updatedCarnetAdresse);
        updatedCarnetAdresse
            .lastname(UPDATED_LASTNAME)
            .firstname(UPDATED_FIRSTNAME)
            .email(UPDATED_EMAIL);
        CarnetAdresseDTO carnetAdresseDTO = carnetAdresseMapper.toDto(updatedCarnetAdresse);

        restCarnetAdresseMockMvc.perform(put("/api/carnet-adresses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(carnetAdresseDTO)))
            .andExpect(status().isOk());

        // Validate the CarnetAdresse in the database
        List<CarnetAdresse> carnetAdresseList = carnetAdresseRepository.findAll();
        assertThat(carnetAdresseList).hasSize(databaseSizeBeforeUpdate);
        CarnetAdresse testCarnetAdresse = carnetAdresseList.get(carnetAdresseList.size() - 1);
        assertThat(testCarnetAdresse.getLastname()).isEqualTo(UPDATED_LASTNAME);
        assertThat(testCarnetAdresse.getFirstname()).isEqualTo(UPDATED_FIRSTNAME);
        assertThat(testCarnetAdresse.getEmail()).isEqualTo(UPDATED_EMAIL);
    }

    @Test
    @Transactional
    public void updateNonExistingCarnetAdresse() throws Exception {
        int databaseSizeBeforeUpdate = carnetAdresseRepository.findAll().size();

        // Create the CarnetAdresse
        CarnetAdresseDTO carnetAdresseDTO = carnetAdresseMapper.toDto(carnetAdresse);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCarnetAdresseMockMvc.perform(put("/api/carnet-adresses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(carnetAdresseDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CarnetAdresse in the database
        List<CarnetAdresse> carnetAdresseList = carnetAdresseRepository.findAll();
        assertThat(carnetAdresseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCarnetAdresse() throws Exception {
        // Initialize the database
        carnetAdresseRepository.saveAndFlush(carnetAdresse);

        int databaseSizeBeforeDelete = carnetAdresseRepository.findAll().size();

        // Delete the carnetAdresse
        restCarnetAdresseMockMvc.perform(delete("/api/carnet-adresses/{id}", carnetAdresse.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CarnetAdresse> carnetAdresseList = carnetAdresseRepository.findAll();
        assertThat(carnetAdresseList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
