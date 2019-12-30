package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.SocieteAbonneService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.SocieteAbonneDTO;

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
 * REST controller for managing {@link com.mycompany.myapp.domain.SocieteAbonne}.
 */
@RestController
@RequestMapping("/api")
public class SocieteAbonneResource {

    private final Logger log = LoggerFactory.getLogger(SocieteAbonneResource.class);

    private static final String ENTITY_NAME = "societeAbonne";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SocieteAbonneService societeAbonneService;

    public SocieteAbonneResource(SocieteAbonneService societeAbonneService) {
        this.societeAbonneService = societeAbonneService;
    }

    /**
     * {@code POST  /societe-abonnes} : Create a new societeAbonne.
     *
     * @param societeAbonneDTO the societeAbonneDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new societeAbonneDTO, or with status {@code 400 (Bad Request)} if the societeAbonne has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/societe-abonnes")
    public ResponseEntity<SocieteAbonneDTO> createSocieteAbonne(@Valid @RequestBody SocieteAbonneDTO societeAbonneDTO) throws URISyntaxException {
        log.debug("REST request to save SocieteAbonne : {}", societeAbonneDTO);
        if (societeAbonneDTO.getId() != null) {
            throw new BadRequestAlertException("A new societeAbonne cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SocieteAbonneDTO result = societeAbonneService.save(societeAbonneDTO);
        return ResponseEntity.created(new URI("/api/societe-abonnes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /societe-abonnes} : Updates an existing societeAbonne.
     *
     * @param societeAbonneDTO the societeAbonneDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated societeAbonneDTO,
     * or with status {@code 400 (Bad Request)} if the societeAbonneDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the societeAbonneDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/societe-abonnes")
    public ResponseEntity<SocieteAbonneDTO> updateSocieteAbonne(@Valid @RequestBody SocieteAbonneDTO societeAbonneDTO) throws URISyntaxException {
        log.debug("REST request to update SocieteAbonne : {}", societeAbonneDTO);
        if (societeAbonneDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SocieteAbonneDTO result = societeAbonneService.save(societeAbonneDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, societeAbonneDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /societe-abonnes} : get all the societeAbonnes.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of societeAbonnes in body.
     */
    @GetMapping("/societe-abonnes")
    public ResponseEntity<List<SocieteAbonneDTO>> getAllSocieteAbonnes(Pageable pageable) {
        log.debug("REST request to get a page of SocieteAbonnes");
        Page<SocieteAbonneDTO> page = societeAbonneService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /societe-abonnes/:id} : get the "id" societeAbonne.
     *
     * @param id the id of the societeAbonneDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the societeAbonneDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/societe-abonnes/{id}")
    public ResponseEntity<SocieteAbonneDTO> getSocieteAbonne(@PathVariable Long id) {
        log.debug("REST request to get SocieteAbonne : {}", id);
        Optional<SocieteAbonneDTO> societeAbonneDTO = societeAbonneService.findOne(id);
        return ResponseUtil.wrapOrNotFound(societeAbonneDTO);
    }

    /**
     * {@code DELETE  /societe-abonnes/:id} : delete the "id" societeAbonne.
     *
     * @param id the id of the societeAbonneDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/societe-abonnes/{id}")
    public ResponseEntity<Void> deleteSocieteAbonne(@PathVariable Long id) {
        log.debug("REST request to delete SocieteAbonne : {}", id);
        societeAbonneService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
