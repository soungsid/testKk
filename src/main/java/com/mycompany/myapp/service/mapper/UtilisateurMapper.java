package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.UtilisateurDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Utilisateur} and its DTO {@link UtilisateurDTO}.
 */
@Mapper(componentModel = "spring", uses = {QcmMapper.class, QcmTestMapper.class})
public interface UtilisateurMapper extends EntityMapper<UtilisateurDTO, Utilisateur> {

    @Mapping(source = "qcm.id", target = "qcmId")
    @Mapping(source = "qcmTest.id", target = "qcmTestId")
    UtilisateurDTO toDto(Utilisateur utilisateur);

    @Mapping(target = "idCountries", ignore = true)
    @Mapping(target = "removeIdCountry", ignore = true)
    @Mapping(target = "idSocietes", ignore = true)
    @Mapping(target = "removeIdSociete", ignore = true)
    @Mapping(source = "qcmId", target = "qcm")
    @Mapping(source = "qcmTestId", target = "qcmTest")
    Utilisateur toEntity(UtilisateurDTO utilisateurDTO);

    default Utilisateur fromId(Long id) {
        if (id == null) {
            return null;
        }
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(id);
        return utilisateur;
    }
}
