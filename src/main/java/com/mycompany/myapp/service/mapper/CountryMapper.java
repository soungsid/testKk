package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.CountryDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Country} and its DTO {@link CountryDTO}.
 */
@Mapper(componentModel = "spring", uses = {UtilisateurMapper.class})
public interface CountryMapper extends EntityMapper<CountryDTO, Country> {

    @Mapping(source = "utilisateur.id", target = "utilisateurId")
    CountryDTO toDto(Country country);

    @Mapping(source = "utilisateurId", target = "utilisateur")
    Country toEntity(CountryDTO countryDTO);

    default Country fromId(Long id) {
        if (id == null) {
            return null;
        }
        Country country = new Country();
        country.setId(id);
        return country;
    }
}
