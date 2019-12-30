package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.QcmTestService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.QcmTestDTO;

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
 * REST controller for managing {@link com.mycompany.myapp.domain.QcmTest}.
 */
@RestController
@RequestMapping("/api")
public class QcmTestResource {

    private final Logger log = LoggerFactory.getLogger(QcmTestResource.class);

    private static final String ENTITY_NAME = "qcmTest";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final QcmTestService qcmTestService;

    public QcmTestResource(QcmTestService qcmTestService) {
        this.qcmTestService = qcmTestService;
    }

    /**
     * {@code POST  /qcm-tests} : Create a new qcmTest.
     *
     * @param qcmTestDTO the qcmTestDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new qcmTestDTO, or with status {@code 400 (Bad Request)} if the qcmTest has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/qcm-tests")
    public ResponseEntity<QcmTestDTO> createQcmTest(@RequestBody QcmTestDTO qcmTestDTO) throws URISyntaxException {
        log.debug("REST request to save QcmTest : {}", qcmTestDTO);
        if (qcmTestDTO.getId() != null) {
            throw new BadRequestAlertException("A new qcmTest cannot already have an ID", ENTITY_NAME, "idexists");
        }
        QcmTestDTO result = qcmTestService.save(qcmTestDTO);
        return ResponseEntity.created(new URI("/api/qcm-tests/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /qcm-tests} : Updates an existing qcmTest.
     *
     * @param qcmTestDTO the qcmTestDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated qcmTestDTO,
     * or with status {@code 400 (Bad Request)} if the qcmTestDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the qcmTestDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/qcm-tests")
    public ResponseEntity<QcmTestDTO> updateQcmTest(@RequestBody QcmTestDTO qcmTestDTO) throws URISyntaxException {
        log.debug("REST request to update QcmTest : {}", qcmTestDTO);
        if (qcmTestDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        QcmTestDTO result = qcmTestService.save(qcmTestDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, qcmTestDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /qcm-tests} : get all the qcmTests.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of qcmTests in body.
     */
    @GetMapping("/qcm-tests")
    public ResponseEntity<List<QcmTestDTO>> getAllQcmTests(Pageable pageable) {
        log.debug("REST request to get a page of QcmTests");
        Page<QcmTestDTO> page = qcmTestService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /qcm-tests/:id} : get the "id" qcmTest.
     *
     * @param id the id of the qcmTestDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the qcmTestDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/qcm-tests/{id}")
    public ResponseEntity<QcmTestDTO> getQcmTest(@PathVariable Long id) {
        log.debug("REST request to get QcmTest : {}", id);
        Optional<QcmTestDTO> qcmTestDTO = qcmTestService.findOne(id);
        return ResponseUtil.wrapOrNotFound(qcmTestDTO);
    }

    /**
     * {@code DELETE  /qcm-tests/:id} : delete the "id" qcmTest.
     *
     * @param id the id of the qcmTestDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/qcm-tests/{id}")
    public ResponseEntity<Void> deleteQcmTest(@PathVariable Long id) {
        log.debug("REST request to delete QcmTest : {}", id);
        qcmTestService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
