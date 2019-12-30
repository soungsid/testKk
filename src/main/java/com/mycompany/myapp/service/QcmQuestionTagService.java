package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.QcmQuestionTag;
import com.mycompany.myapp.repository.QcmQuestionTagRepository;
import com.mycompany.myapp.service.dto.QcmQuestionTagDTO;
import com.mycompany.myapp.service.mapper.QcmQuestionTagMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link QcmQuestionTag}.
 */
@Service
@Transactional
public class QcmQuestionTagService {

    private final Logger log = LoggerFactory.getLogger(QcmQuestionTagService.class);

    private final QcmQuestionTagRepository qcmQuestionTagRepository;

    private final QcmQuestionTagMapper qcmQuestionTagMapper;

    public QcmQuestionTagService(QcmQuestionTagRepository qcmQuestionTagRepository, QcmQuestionTagMapper qcmQuestionTagMapper) {
        this.qcmQuestionTagRepository = qcmQuestionTagRepository;
        this.qcmQuestionTagMapper = qcmQuestionTagMapper;
    }

    /**
     * Save a qcmQuestionTag.
     *
     * @param qcmQuestionTagDTO the entity to save.
     * @return the persisted entity.
     */
    public QcmQuestionTagDTO save(QcmQuestionTagDTO qcmQuestionTagDTO) {
        log.debug("Request to save QcmQuestionTag : {}", qcmQuestionTagDTO);
        QcmQuestionTag qcmQuestionTag = qcmQuestionTagMapper.toEntity(qcmQuestionTagDTO);
        qcmQuestionTag = qcmQuestionTagRepository.save(qcmQuestionTag);
        return qcmQuestionTagMapper.toDto(qcmQuestionTag);
    }

    /**
     * Get all the qcmQuestionTags.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<QcmQuestionTagDTO> findAll(Pageable pageable) {
        log.debug("Request to get all QcmQuestionTags");
        return qcmQuestionTagRepository.findAll(pageable)
            .map(qcmQuestionTagMapper::toDto);
    }


    /**
     * Get one qcmQuestionTag by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<QcmQuestionTagDTO> findOne(Long id) {
        log.debug("Request to get QcmQuestionTag : {}", id);
        return qcmQuestionTagRepository.findById(id)
            .map(qcmQuestionTagMapper::toDto);
    }

    /**
     * Delete the qcmQuestionTag by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete QcmQuestionTag : {}", id);
        qcmQuestionTagRepository.deleteById(id);
    }
}
