package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.QcmQuestionTagService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.QcmQuestionTagDTO;

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
 * REST controller for managing {@link com.mycompany.myapp.domain.QcmQuestionTag}.
 */
@RestController
@RequestMapping("/api")
public class QcmQuestionTagResource {

    private final Logger log = LoggerFactory.getLogger(QcmQuestionTagResource.class);

    private static final String ENTITY_NAME = "qcmQuestionTag";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final QcmQuestionTagService qcmQuestionTagService;

    public QcmQuestionTagResource(QcmQuestionTagService qcmQuestionTagService) {
        this.qcmQuestionTagService = qcmQuestionTagService;
    }

    /**
     * {@code POST  /qcm-question-tags} : Create a new qcmQuestionTag.
     *
     * @param qcmQuestionTagDTO the qcmQuestionTagDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new qcmQuestionTagDTO, or with status {@code 400 (Bad Request)} if the qcmQuestionTag has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/qcm-question-tags")
    public ResponseEntity<QcmQuestionTagDTO> createQcmQuestionTag(@RequestBody QcmQuestionTagDTO qcmQuestionTagDTO) throws URISyntaxException {
        log.debug("REST request to save QcmQuestionTag : {}", qcmQuestionTagDTO);
        if (qcmQuestionTagDTO.getId() != null) {
            throw new BadRequestAlertException("A new qcmQuestionTag cannot already have an ID", ENTITY_NAME, "idexists");
        }
        QcmQuestionTagDTO result = qcmQuestionTagService.save(qcmQuestionTagDTO);
        return ResponseEntity.created(new URI("/api/qcm-question-tags/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /qcm-question-tags} : Updates an existing qcmQuestionTag.
     *
     * @param qcmQuestionTagDTO the qcmQuestionTagDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated qcmQuestionTagDTO,
     * or with status {@code 400 (Bad Request)} if the qcmQuestionTagDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the qcmQuestionTagDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/qcm-question-tags")
    public ResponseEntity<QcmQuestionTagDTO> updateQcmQuestionTag(@RequestBody QcmQuestionTagDTO qcmQuestionTagDTO) throws URISyntaxException {
        log.debug("REST request to update QcmQuestionTag : {}", qcmQuestionTagDTO);
        if (qcmQuestionTagDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        QcmQuestionTagDTO result = qcmQuestionTagService.save(qcmQuestionTagDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, qcmQuestionTagDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /qcm-question-tags} : get all the qcmQuestionTags.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of qcmQuestionTags in body.
     */
    @GetMapping("/qcm-question-tags")
    public ResponseEntity<List<QcmQuestionTagDTO>> getAllQcmQuestionTags(Pageable pageable) {
        log.debug("REST request to get a page of QcmQuestionTags");
        Page<QcmQuestionTagDTO> page = qcmQuestionTagService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /qcm-question-tags/:id} : get the "id" qcmQuestionTag.
     *
     * @param id the id of the qcmQuestionTagDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the qcmQuestionTagDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/qcm-question-tags/{id}")
    public ResponseEntity<QcmQuestionTagDTO> getQcmQuestionTag(@PathVariable Long id) {
        log.debug("REST request to get QcmQuestionTag : {}", id);
        Optional<QcmQuestionTagDTO> qcmQuestionTagDTO = qcmQuestionTagService.findOne(id);
        return ResponseUtil.wrapOrNotFound(qcmQuestionTagDTO);
    }

    /**
     * {@code DELETE  /qcm-question-tags/:id} : delete the "id" qcmQuestionTag.
     *
     * @param id the id of the qcmQuestionTagDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/qcm-question-tags/{id}")
    public ResponseEntity<Void> deleteQcmQuestionTag(@PathVariable Long id) {
        log.debug("REST request to delete QcmQuestionTag : {}", id);
        qcmQuestionTagService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
