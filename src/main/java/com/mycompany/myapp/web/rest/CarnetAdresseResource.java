package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.CarnetAdresseService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.CarnetAdresseDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.mycompany.myapp.domain.CarnetAdresse}.
 */
@RestController
@RequestMapping("/api")
public class CarnetAdresseResource {

    private final Logger log = LoggerFactory.getLogger(CarnetAdresseResource.class);

    private static final String ENTITY_NAME = "carnetAdresse";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CarnetAdresseService carnetAdresseService;

    public CarnetAdresseResource(CarnetAdresseService carnetAdresseService) {
        this.carnetAdresseService = carnetAdresseService;
    }

    /**
     * {@code POST  /carnet-adresses} : Create a new carnetAdresse.
     *
     * @param carnetAdresseDTO the carnetAdresseDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new carnetAdresseDTO, or with status {@code 400 (Bad Request)} if the carnetAdresse has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/carnet-adresses")
    public ResponseEntity<CarnetAdresseDTO> createCarnetAdresse(@RequestBody CarnetAdresseDTO carnetAdresseDTO) throws URISyntaxException {
        log.debug("REST request to save CarnetAdresse : {}", carnetAdresseDTO);
        if (carnetAdresseDTO.getId() != null) {
            throw new BadRequestAlertException("A new carnetAdresse cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CarnetAdresseDTO result = carnetAdresseService.save(carnetAdresseDTO);
        return ResponseEntity.created(new URI("/api/carnet-adresses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /carnet-adresses} : Updates an existing carnetAdresse.
     *
     * @param carnetAdresseDTO the carnetAdresseDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated carnetAdresseDTO,
     * or with status {@code 400 (Bad Request)} if the carnetAdresseDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the carnetAdresseDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/carnet-adresses")
    public ResponseEntity<CarnetAdresseDTO> updateCarnetAdresse(@RequestBody CarnetAdresseDTO carnetAdresseDTO) throws URISyntaxException {
        log.debug("REST request to update CarnetAdresse : {}", carnetAdresseDTO);
        if (carnetAdresseDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CarnetAdresseDTO result = carnetAdresseService.save(carnetAdresseDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, carnetAdresseDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /carnet-adresses} : get all the carnetAdresses.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of carnetAdresses in body.
     */
    @GetMapping("/carnet-adresses")
    public ResponseEntity<List<CarnetAdresseDTO>> getAllCarnetAdresses(Pageable pageable) {
        log.debug("REST request to get a page of CarnetAdresses");
        Page<CarnetAdresseDTO> page = carnetAdresseService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /carnet-adresses/:id} : get the "id" carnetAdresse.
     *
     * @param id the id of the carnetAdresseDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the carnetAdresseDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/carnet-adresses/{id}")
    public ResponseEntity<CarnetAdresseDTO> getCarnetAdresse(@PathVariable Long id) {
        log.debug("REST request to get CarnetAdresse : {}", id);
        Optional<CarnetAdresseDTO> carnetAdresseDTO = carnetAdresseService.findOne(id);
        return ResponseUtil.wrapOrNotFound(carnetAdresseDTO);
    }

    /**
     * {@code DELETE  /carnet-adresses/:id} : delete the "id" carnetAdresse.
     *
     * @param id the id of the carnetAdresseDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/carnet-adresses/{id}")
    public ResponseEntity<Void> deleteCarnetAdresse(@PathVariable Long id) {
        log.debug("REST request to delete CarnetAdresse : {}", id);
        carnetAdresseService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
