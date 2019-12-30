package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.Qcm;
import com.mycompany.myapp.repository.QcmRepository;
import com.mycompany.myapp.service.dto.QcmDTO;
import com.mycompany.myapp.service.mapper.QcmMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Qcm}.
 */
@Service
@Transactional
public class QcmService {

    private final Logger log = LoggerFactory.getLogger(QcmService.class);

    private final QcmRepository qcmRepository;

    private final QcmMapper qcmMapper;

    public QcmService(QcmRepository qcmRepository, QcmMapper qcmMapper) {
        this.qcmRepository = qcmRepository;
        this.qcmMapper = qcmMapper;
    }

    /**
     * Save a qcm.
     *
     * @param qcmDTO the entity to save.
     * @return the persisted entity.
     */
    public QcmDTO save(QcmDTO qcmDTO) {
        log.debug("Request to save Qcm : {}", qcmDTO);
        Qcm qcm = qcmMapper.toEntity(qcmDTO);
        qcm = qcmRepository.save(qcm);
        return qcmMapper.toDto(qcm);
    }

    /**
     * Get all the qcms.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<QcmDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Qcms");
        return qcmRepository.findAll(pageable)
            .map(qcmMapper::toDto);
    }


    /**
     * Get one qcm by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<QcmDTO> findOne(Long id) {
        log.debug("Request to get Qcm : {}", id);
        return qcmRepository.findById(id)
            .map(qcmMapper::toDto);
    }

    /**
     * Delete the qcm by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Qcm : {}", id);
        qcmRepository.deleteById(id);
    }
}
