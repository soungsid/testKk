package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.QcmReponse;
import com.mycompany.myapp.repository.QcmReponseRepository;
import com.mycompany.myapp.service.dto.QcmReponseDTO;
import com.mycompany.myapp.service.mapper.QcmReponseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link QcmReponse}.
 */
@Service
@Transactional
public class QcmReponseService {

    private final Logger log = LoggerFactory.getLogger(QcmReponseService.class);

    private final QcmReponseRepository qcmReponseRepository;

    private final QcmReponseMapper qcmReponseMapper;

    public QcmReponseService(QcmReponseRepository qcmReponseRepository, QcmReponseMapper qcmReponseMapper) {
        this.qcmReponseRepository = qcmReponseRepository;
        this.qcmReponseMapper = qcmReponseMapper;
    }

    /**
     * Save a qcmReponse.
     *
     * @param qcmReponseDTO the entity to save.
     * @return the persisted entity.
     */
    public QcmReponseDTO save(QcmReponseDTO qcmReponseDTO) {
        log.debug("Request to save QcmReponse : {}", qcmReponseDTO);
        QcmReponse qcmReponse = qcmReponseMapper.toEntity(qcmReponseDTO);
        qcmReponse = qcmReponseRepository.save(qcmReponse);
        return qcmReponseMapper.toDto(qcmReponse);
    }

    /**
     * Get all the qcmReponses.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<QcmReponseDTO> findAll(Pageable pageable) {
        log.debug("Request to get all QcmReponses");
        return qcmReponseRepository.findAll(pageable)
            .map(qcmReponseMapper::toDto);
    }


    /**
     * Get one qcmReponse by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<QcmReponseDTO> findOne(Long id) {
        log.debug("Request to get QcmReponse : {}", id);
        return qcmReponseRepository.findById(id)
            .map(qcmReponseMapper::toDto);
    }

    /**
     * Delete the qcmReponse by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete QcmReponse : {}", id);
        qcmReponseRepository.deleteById(id);
    }
}
