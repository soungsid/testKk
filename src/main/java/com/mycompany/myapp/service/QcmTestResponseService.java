package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.QcmTestResponse;
import com.mycompany.myapp.repository.QcmTestResponseRepository;
import com.mycompany.myapp.service.dto.QcmTestResponseDTO;
import com.mycompany.myapp.service.mapper.QcmTestResponseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link QcmTestResponse}.
 */
@Service
@Transactional
public class QcmTestResponseService {

    private final Logger log = LoggerFactory.getLogger(QcmTestResponseService.class);

    private final QcmTestResponseRepository qcmTestResponseRepository;

    private final QcmTestResponseMapper qcmTestResponseMapper;

    public QcmTestResponseService(QcmTestResponseRepository qcmTestResponseRepository, QcmTestResponseMapper qcmTestResponseMapper) {
        this.qcmTestResponseRepository = qcmTestResponseRepository;
        this.qcmTestResponseMapper = qcmTestResponseMapper;
    }

    /**
     * Save a qcmTestResponse.
     *
     * @param qcmTestResponseDTO the entity to save.
     * @return the persisted entity.
     */
    public QcmTestResponseDTO save(QcmTestResponseDTO qcmTestResponseDTO) {
        log.debug("Request to save QcmTestResponse : {}", qcmTestResponseDTO);
        QcmTestResponse qcmTestResponse = qcmTestResponseMapper.toEntity(qcmTestResponseDTO);
        qcmTestResponse = qcmTestResponseRepository.save(qcmTestResponse);
        return qcmTestResponseMapper.toDto(qcmTestResponse);
    }

    /**
     * Get all the qcmTestResponses.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<QcmTestResponseDTO> findAll(Pageable pageable) {
        log.debug("Request to get all QcmTestResponses");
        return qcmTestResponseRepository.findAll(pageable)
            .map(qcmTestResponseMapper::toDto);
    }


    /**
     * Get one qcmTestResponse by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<QcmTestResponseDTO> findOne(Long id) {
        log.debug("Request to get QcmTestResponse : {}", id);
        return qcmTestResponseRepository.findById(id)
            .map(qcmTestResponseMapper::toDto);
    }

    /**
     * Delete the qcmTestResponse by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete QcmTestResponse : {}", id);
        qcmTestResponseRepository.deleteById(id);
    }
}
