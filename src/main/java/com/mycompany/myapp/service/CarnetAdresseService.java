package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.CarnetAdresse;
import com.mycompany.myapp.repository.CarnetAdresseRepository;
import com.mycompany.myapp.service.dto.CarnetAdresseDTO;
import com.mycompany.myapp.service.mapper.CarnetAdresseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link CarnetAdresse}.
 */
@Service
@Transactional
public class CarnetAdresseService {

    private final Logger log = LoggerFactory.getLogger(CarnetAdresseService.class);

    private final CarnetAdresseRepository carnetAdresseRepository;

    private final CarnetAdresseMapper carnetAdresseMapper;

    public CarnetAdresseService(CarnetAdresseRepository carnetAdresseRepository, CarnetAdresseMapper carnetAdresseMapper) {
        this.carnetAdresseRepository = carnetAdresseRepository;
        this.carnetAdresseMapper = carnetAdresseMapper;
    }

    /**
     * Save a carnetAdresse.
     *
     * @param carnetAdresseDTO the entity to save.
     * @return the persisted entity.
     */
    public CarnetAdresseDTO save(CarnetAdresseDTO carnetAdresseDTO) {
        log.debug("Request to save CarnetAdresse : {}", carnetAdresseDTO);
        CarnetAdresse carnetAdresse = carnetAdresseMapper.toEntity(carnetAdresseDTO);
        carnetAdresse = carnetAdresseRepository.save(carnetAdresse);
        return carnetAdresseMapper.toDto(carnetAdresse);
    }

    /**
     * Get all the carnetAdresses.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<CarnetAdresseDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CarnetAdresses");
        return carnetAdresseRepository.findAll(pageable)
            .map(carnetAdresseMapper::toDto);
    }


    /**
     * Get one carnetAdresse by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CarnetAdresseDTO> findOne(Long id) {
        log.debug("Request to get CarnetAdresse : {}", id);
        return carnetAdresseRepository.findById(id)
            .map(carnetAdresseMapper::toDto);
    }

    /**
     * Delete the carnetAdresse by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete CarnetAdresse : {}", id);
        carnetAdresseRepository.deleteById(id);
    }
}
