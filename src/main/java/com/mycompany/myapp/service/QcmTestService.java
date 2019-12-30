package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.QcmTest;
import com.mycompany.myapp.repository.QcmTestRepository;
import com.mycompany.myapp.service.dto.QcmTestDTO;
import com.mycompany.myapp.service.mapper.QcmTestMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link QcmTest}.
 */
@Service
@Transactional
public class QcmTestService {

    private final Logger log = LoggerFactory.getLogger(QcmTestService.class);

    private final QcmTestRepository qcmTestRepository;

    private final QcmTestMapper qcmTestMapper;

    public QcmTestService(QcmTestRepository qcmTestRepository, QcmTestMapper qcmTestMapper) {
        this.qcmTestRepository = qcmTestRepository;
        this.qcmTestMapper = qcmTestMapper;
    }

    /**
     * Save a qcmTest.
     *
     * @param qcmTestDTO the entity to save.
     * @return the persisted entity.
     */
    public QcmTestDTO save(QcmTestDTO qcmTestDTO) {
        log.debug("Request to save QcmTest : {}", qcmTestDTO);
        QcmTest qcmTest = qcmTestMapper.toEntity(qcmTestDTO);
        qcmTest = qcmTestRepository.save(qcmTest);
        return qcmTestMapper.toDto(qcmTest);
    }

    /**
     * Get all the qcmTests.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<QcmTestDTO> findAll(Pageable pageable) {
        log.debug("Request to get all QcmTests");
        return qcmTestRepository.findAll(pageable)
            .map(qcmTestMapper::toDto);
    }


    /**
     * Get one qcmTest by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<QcmTestDTO> findOne(Long id) {
        log.debug("Request to get QcmTest : {}", id);
        return qcmTestRepository.findById(id)
            .map(qcmTestMapper::toDto);
    }

    /**
     * Delete the qcmTest by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete QcmTest : {}", id);
        qcmTestRepository.deleteById(id);
    }
}
