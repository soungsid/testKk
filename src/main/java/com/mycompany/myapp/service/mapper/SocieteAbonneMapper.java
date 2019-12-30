package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.SocieteAbonneDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link SocieteAbonne} and its DTO {@link SocieteAbonneDTO}.
 */
@Mapper(componentModel = "spring", uses = {UtilisateurMapper.class})
public interface SocieteAbonneMapper extends EntityMapper<SocieteAbonneDTO, SocieteAbonne> {

    @Mapping(source = "utilisateur.id", target = "utilisateurId")
    SocieteAbonneDTO toDto(SocieteAbonne societeAbonne);

    @Mapping(source = "utilisateurId", target = "utilisateur")
    SocieteAbonne toEntity(SocieteAbonneDTO societeAbonneDTO);

    default SocieteAbonne fromId(Long id) {
        if (id == null) {
            return null;
        }
        SocieteAbonne societeAbonne = new SocieteAbonne();
        societeAbonne.setId(id);
        return societeAbonne;
    }
}
