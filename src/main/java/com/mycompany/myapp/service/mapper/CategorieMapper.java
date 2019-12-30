package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.CategorieDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Categorie} and its DTO {@link CategorieDTO}.
 */
@Mapper(componentModel = "spring", uses = {QcmMapper.class})
public interface CategorieMapper extends EntityMapper<CategorieDTO, Categorie> {

    @Mapping(source = "qcm.id", target = "qcmId")
    CategorieDTO toDto(Categorie categorie);

    @Mapping(source = "qcmId", target = "qcm")
    Categorie toEntity(CategorieDTO categorieDTO);

    default Categorie fromId(Long id) {
        if (id == null) {
            return null;
        }
        Categorie categorie = new Categorie();
        categorie.setId(id);
        return categorie;
    }
}
