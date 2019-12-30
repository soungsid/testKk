package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.QcmQuestionService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.QcmQuestionDTO;

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
 * REST controller for managing {@link com.mycompany.myapp.domain.QcmQuestion}.
 */
@RestController
@RequestMapping("/api")
public class QcmQuestionResource {

    private final Logger log = LoggerFactory.getLogger(QcmQuestionResource.class);

    private static final String ENTITY_NAME = "qcmQuestion";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final QcmQuestionService qcmQuestionService;

    public QcmQuestionResource(QcmQuestionService qcmQuestionService) {
        this.qcmQuestionService = qcmQuestionService;
    }

    /**
     * {@code POST  /qcm-questions} : Create a new qcmQuestion.
     *
     * @param qcmQuestionDTO the qcmQuestionDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new qcmQuestionDTO, or with status {@code 400 (Bad Request)} if the qcmQuestion has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/qcm-questions")
    public ResponseEntity<QcmQuestionDTO> createQcmQuestion(@Valid @RequestBody QcmQuestionDTO qcmQuestionDTO) throws URISyntaxException {
        log.debug("REST request to save QcmQuestion : {}", qcmQuestionDTO);
        if (qcmQuestionDTO.getId() != null) {
            throw new BadRequestAlertException("A new qcmQuestion cannot already have an ID", ENTITY_NAME, "idexists");
        }
        QcmQuestionDTO result = qcmQuestionService.save(qcmQuestionDTO);
        return ResponseEntity.created(new URI("/api/qcm-questions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /qcm-questions} : Updates an existing qcmQuestion.
     *
     * @param qcmQuestionDTO the qcmQuestionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated qcmQuestionDTO,
     * or with status {@code 400 (Bad Request)} if the qcmQuestionDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the qcmQuestionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/qcm-questions")
    public ResponseEntity<QcmQuestionDTO> updateQcmQuestion(@Valid @RequestBody QcmQuestionDTO qcmQuestionDTO) throws URISyntaxException {
        log.debug("REST request to update QcmQuestion : {}", qcmQuestionDTO);
        if (qcmQuestionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        QcmQuestionDTO result = qcmQuestionService.save(qcmQuestionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, qcmQuestionDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /qcm-questions} : get all the qcmQuestions.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of qcmQuestions in body.
     */
    @GetMapping("/qcm-questions")
    public ResponseEntity<List<QcmQuestionDTO>> getAllQcmQuestions(Pageable pageable) {
        log.debug("REST request to get a page of QcmQuestions");
        Page<QcmQuestionDTO> page = qcmQuestionService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /qcm-questions/:id} : get the "id" qcmQuestion.
     *
     * @param id the id of the qcmQuestionDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the qcmQuestionDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/qcm-questions/{id}")
    public ResponseEntity<QcmQuestionDTO> getQcmQuestion(@PathVariable Long id) {
        log.debug("REST request to get QcmQuestion : {}", id);
        Optional<QcmQuestionDTO> qcmQuestionDTO = qcmQuestionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(qcmQuestionDTO);
    }

    /**
     * {@code DELETE  /qcm-questions/:id} : delete the "id" qcmQuestion.
     *
     * @param id the id of the qcmQuestionDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/qcm-questions/{id}")
    public ResponseEntity<Void> deleteQcmQuestion(@PathVariable Long id) {
        log.debug("REST request to delete QcmQuestion : {}", id);
        qcmQuestionService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
