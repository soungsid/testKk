package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.Utilisateur;
import com.mycompany.myapp.repository.UtilisateurRepository;
import com.mycompany.myapp.service.dto.UtilisateurDTO;
import com.mycompany.myapp.service.mapper.UtilisateurMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Utilisateur}.
 */
@Service
@Transactional
public class UtilisateurService {

    private final Logger log = LoggerFactory.getLogger(UtilisateurService.class);

    private final UtilisateurRepository utilisateurRepository;

    private final UtilisateurMapper utilisateurMapper;

    public UtilisateurService(UtilisateurRepository utilisateurRepository, UtilisateurMapper utilisateurMapper) {
        this.utilisateurRepository = utilisateurRepository;
        this.utilisateurMapper = utilisateurMapper;
    }

    /**
     * Save a utilisateur.
     *
     * @param utilisateurDTO the entity to save.
     * @return the persisted entity.
     */
    public UtilisateurDTO save(UtilisateurDTO utilisateurDTO) {
        log.debug("Request to save Utilisateur : {}", utilisateurDTO);
        Utilisateur utilisateur = utilisateurMapper.toEntity(utilisateurDTO);
        utilisateur = utilisateurRepository.save(utilisateur);
        return utilisateurMapper.toDto(utilisateur);
    }

    /**
     * Get all the utilisateurs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<UtilisateurDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Utilisateurs");
        return utilisateurRepository.findAll(pageable)
            .map(utilisateurMapper::toDto);
    }


    /**
     * Get one utilisateur by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<UtilisateurDTO> findOne(Long id) {
        log.debug("Request to get Utilisateur : {}", id);
        return utilisateurRepository.findById(id)
            .map(utilisateurMapper::toDto);
    }

    /**
     * Delete the utilisateur by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Utilisateur : {}", id);
        utilisateurRepository.deleteById(id);
    }
}
