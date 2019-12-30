package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.QcmTestResponseDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link QcmTestResponse} and its DTO {@link QcmTestResponseDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface QcmTestResponseMapper extends EntityMapper<QcmTestResponseDTO, QcmTestResponse> {


    @Mapping(target = "idQcmTests", ignore = true)
    @Mapping(target = "removeIdQcmTest", ignore = true)
    @Mapping(target = "idQcmQuestions", ignore = true)
    @Mapping(target = "removeIdQcmQuestion", ignore = true)
    @Mapping(target = "idQcmReponses", ignore = true)
    @Mapping(target = "removeIdQcmReponse", ignore = true)
    QcmTestResponse toEntity(QcmTestResponseDTO qcmTestResponseDTO);

    default QcmTestResponse fromId(Long id) {
        if (id == null) {
            return null;
        }
        QcmTestResponse qcmTestResponse = new QcmTestResponse();
        qcmTestResponse.setId(id);
        return qcmTestResponse;
    }
}
