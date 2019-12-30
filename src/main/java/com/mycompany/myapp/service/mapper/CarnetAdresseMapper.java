package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.CarnetAdresseDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link CarnetAdresse} and its DTO {@link CarnetAdresseDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CarnetAdresseMapper extends EntityMapper<CarnetAdresseDTO, CarnetAdresse> {



    default CarnetAdresse fromId(Long id) {
        if (id == null) {
            return null;
        }
        CarnetAdresse carnetAdresse = new CarnetAdresse();
        carnetAdresse.setId(id);
        return carnetAdresse;
    }
}
