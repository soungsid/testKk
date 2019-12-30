package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.SocieteAbonne;
import com.mycompany.myapp.repository.SocieteAbonneRepository;
import com.mycompany.myapp.service.dto.SocieteAbonneDTO;
import com.mycompany.myapp.service.mapper.SocieteAbonneMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link SocieteAbonne}.
 */
@Service
@Transactional
public class SocieteAbonneService {

    private final Logger log = LoggerFactory.getLogger(SocieteAbonneService.class);

    private final SocieteAbonneRepository societeAbonneRepository;

    private final SocieteAbonneMapper societeAbonneMapper;

    public SocieteAbonneService(SocieteAbonneRepository societeAbonneRepository, SocieteAbonneMapper societeAbonneMapper) {
        this.societeAbonneRepository = societeAbonneRepository;
        this.societeAbonneMapper = societeAbonneMapper;
    }

    /**
     * Save a societeAbonne.
     *
     * @param societeAbonneDTO the entity to save.
     * @return the persisted entity.
     */
    public SocieteAbonneDTO save(SocieteAbonneDTO societeAbonneDTO) {
        log.debug("Request to save SocieteAbonne : {}", societeAbonneDTO);
        SocieteAbonne societeAbonne = societeAbonneMapper.toEntity(societeAbonneDTO);
        societeAbonne = societeAbonneRepository.save(societeAbonne);
        return societeAbonneMapper.toDto(societeAbonne);
    }

    /**
     * Get all the societeAbonnes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<SocieteAbonneDTO> findAll(Pageable pageable) {
        log.debug("Request to get all SocieteAbonnes");
        return societeAbonneRepository.findAll(pageable)
            .map(societeAbonneMapper::toDto);
    }


    /**
     * Get one societeAbonne by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<SocieteAbonneDTO> findOne(Long id) {
        log.debug("Request to get SocieteAbonne : {}", id);
        return societeAbonneRepository.findById(id)
            .map(societeAbonneMapper::toDto);
    }

    /**
     * Delete the societeAbonne by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete SocieteAbonne : {}", id);
        societeAbonneRepository.deleteById(id);
    }
}
