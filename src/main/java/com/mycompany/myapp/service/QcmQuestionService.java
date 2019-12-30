package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.QcmQuestion;
import com.mycompany.myapp.repository.QcmQuestionRepository;
import com.mycompany.myapp.service.dto.QcmQuestionDTO;
import com.mycompany.myapp.service.mapper.QcmQuestionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link QcmQuestion}.
 */
@Service
@Transactional
public class QcmQuestionService {

    private final Logger log = LoggerFactory.getLogger(QcmQuestionService.class);

    private final QcmQuestionRepository qcmQuestionRepository;

    private final QcmQuestionMapper qcmQuestionMapper;

    public QcmQuestionService(QcmQuestionRepository qcmQuestionRepository, QcmQuestionMapper qcmQuestionMapper) {
        this.qcmQuestionRepository = qcmQuestionRepository;
        this.qcmQuestionMapper = qcmQuestionMapper;
    }

    /**
     * Save a qcmQuestion.
     *
     * @param qcmQuestionDTO the entity to save.
     * @return the persisted entity.
     */
    public QcmQuestionDTO save(QcmQuestionDTO qcmQuestionDTO) {
        log.debug("Request to save QcmQuestion : {}", qcmQuestionDTO);
        QcmQuestion qcmQuestion = qcmQuestionMapper.toEntity(qcmQuestionDTO);
        qcmQuestion = qcmQuestionRepository.save(qcmQuestion);
        return qcmQuestionMapper.toDto(qcmQuestion);
    }

    /**
     * Get all the qcmQuestions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<QcmQuestionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all QcmQuestions");
        return qcmQuestionRepository.findAll(pageable)
            .map(qcmQuestionMapper::toDto);
    }


    /**
     * Get one qcmQuestion by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<QcmQuestionDTO> findOne(Long id) {
        log.debug("Request to get QcmQuestion : {}", id);
        return qcmQuestionRepository.findById(id)
            .map(qcmQuestionMapper::toDto);
    }

    /**
     * Delete the qcmQuestion by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete QcmQuestion : {}", id);
        qcmQuestionRepository.deleteById(id);
    }
}
