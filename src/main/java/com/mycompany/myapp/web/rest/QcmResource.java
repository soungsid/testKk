package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.QcmService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.QcmDTO;

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

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.mycompany.myapp.domain.Qcm}.
 */
@RestController
@RequestMapping("/api")
public class QcmResource {

    private final Logger log = LoggerFactory.getLogger(QcmResource.class);

    private static final String ENTITY_NAME = "qcm";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final QcmService qcmService;

    public QcmResource(QcmService qcmService) {
        this.qcmService = qcmService;
    }

    /**
     * {@code POST  /qcms} : Create a new qcm.
     *
     * @param qcmDTO the qcmDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new qcmDTO, or with status {@code 400 (Bad Request)} if the qcm has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/qcms")
    public ResponseEntity<QcmDTO> createQcm(@Valid @RequestBody QcmDTO qcmDTO) throws URISyntaxException {
        log.debug("REST request to save Qcm : {}", qcmDTO);
        if (qcmDTO.getId() != null) {
            throw new BadRequestAlertException("A new qcm cannot already have an ID", ENTITY_NAME, "idexists");
        }
        QcmDTO result = qcmService.save(qcmDTO);
        return ResponseEntity.created(new URI("/api/qcms/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /qcms} : Updates an existing qcm.
     *
     * @param qcmDTO the qcmDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated qcmDTO,
     * or with status {@code 400 (Bad Request)} if the qcmDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the qcmDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/qcms")
    public ResponseEntity<QcmDTO> updateQcm(@Valid @RequestBody QcmDTO qcmDTO) throws URISyntaxException {
        log.debug("REST request to update Qcm : {}", qcmDTO);
        if (qcmDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        QcmDTO result = qcmService.save(qcmDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, qcmDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /qcms} : get all the qcms.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of qcms in body.
     */
    @GetMapping("/qcms")
    public ResponseEntity<List<QcmDTO>> getAllQcms(Pageable pageable) {
        log.debug("REST request to get a page of Qcms");
        Page<QcmDTO> page = qcmService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /qcms/:id} : get the "id" qcm.
     *
     * @param id the id of the qcmDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the qcmDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/qcms/{id}")
    public ResponseEntity<QcmDTO> getQcm(@PathVariable Long id) {
        log.debug("REST request to get Qcm : {}", id);
        Optional<QcmDTO> qcmDTO = qcmService.findOne(id);
        return ResponseUtil.wrapOrNotFound(qcmDTO);
    }

    /**
     * {@code DELETE  /qcms/:id} : delete the "id" qcm.
     *
     * @param id the id of the qcmDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/qcms/{id}")
    public ResponseEntity<Void> deleteQcm(@PathVariable Long id) {
        log.debug("REST request to delete Qcm : {}", id);
        qcmService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
