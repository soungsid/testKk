package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.QcmTestResponseService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.QcmTestResponseDTO;

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
 * REST controller for managing {@link com.mycompany.myapp.domain.QcmTestResponse}.
 */
@RestController
@RequestMapping("/api")
public class QcmTestResponseResource {

    private final Logger log = LoggerFactory.getLogger(QcmTestResponseResource.class);

    private static final String ENTITY_NAME = "qcmTestResponse";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final QcmTestResponseService qcmTestResponseService;

    public QcmTestResponseResource(QcmTestResponseService qcmTestResponseService) {
        this.qcmTestResponseService = qcmTestResponseService;
    }

    /**
     * {@code POST  /qcm-test-responses} : Create a new qcmTestResponse.
     *
     * @param qcmTestResponseDTO the qcmTestResponseDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new qcmTestResponseDTO, or with status {@code 400 (Bad Request)} if the qcmTestResponse has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/qcm-test-responses")
    public ResponseEntity<QcmTestResponseDTO> createQcmTestResponse(@RequestBody QcmTestResponseDTO qcmTestResponseDTO) throws URISyntaxException {
        log.debug("REST request to save QcmTestResponse : {}", qcmTestResponseDTO);
        if (qcmTestResponseDTO.getId() != null) {
            throw new BadRequestAlertException("A new qcmTestResponse cannot already have an ID", ENTITY_NAME, "idexists");
        }
        QcmTestResponseDTO result = qcmTestResponseService.save(qcmTestResponseDTO);
        return ResponseEntity.created(new URI("/api/qcm-test-responses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /qcm-test-responses} : Updates an existing qcmTestResponse.
     *
     * @param qcmTestResponseDTO the qcmTestResponseDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated qcmTestResponseDTO,
     * or with status {@code 400 (Bad Request)} if the qcmTestResponseDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the qcmTestResponseDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/qcm-test-responses")
    public ResponseEntity<QcmTestResponseDTO> updateQcmTestResponse(@RequestBody QcmTestResponseDTO qcmTestResponseDTO) throws URISyntaxException {
        log.debug("REST request to update QcmTestResponse : {}", qcmTestResponseDTO);
        if (qcmTestResponseDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        QcmTestResponseDTO result = qcmTestResponseService.save(qcmTestResponseDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, qcmTestResponseDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /qcm-test-responses} : get all the qcmTestResponses.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of qcmTestResponses in body.
     */
    @GetMapping("/qcm-test-responses")
    public ResponseEntity<List<QcmTestResponseDTO>> getAllQcmTestResponses(Pageable pageable) {
        log.debug("REST request to get a page of QcmTestResponses");
        Page<QcmTestResponseDTO> page = qcmTestResponseService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /qcm-test-responses/:id} : get the "id" qcmTestResponse.
     *
     * @param id the id of the qcmTestResponseDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the qcmTestResponseDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/qcm-test-responses/{id}")
    public ResponseEntity<QcmTestResponseDTO> getQcmTestResponse(@PathVariable Long id) {
        log.debug("REST request to get QcmTestResponse : {}", id);
        Optional<QcmTestResponseDTO> qcmTestResponseDTO = qcmTestResponseService.findOne(id);
        return ResponseUtil.wrapOrNotFound(qcmTestResponseDTO);
    }

    /**
     * {@code DELETE  /qcm-test-responses/:id} : delete the "id" qcmTestResponse.
     *
     * @param id the id of the qcmTestResponseDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/qcm-test-responses/{id}")
    public ResponseEntity<Void> deleteQcmTestResponse(@PathVariable Long id) {
        log.debug("REST request to delete QcmTestResponse : {}", id);
        qcmTestResponseService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
