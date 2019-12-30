package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.QcmReponseDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link QcmReponse} and its DTO {@link QcmReponseDTO}.
 */
@Mapper(componentModel = "spring", uses = {QcmTestResponseMapper.class})
public interface QcmReponseMapper extends EntityMapper<QcmReponseDTO, QcmReponse> {

    @Mapping(source = "qcmTestResponse.id", target = "qcmTestResponseId")
    QcmReponseDTO toDto(QcmReponse qcmReponse);

    @Mapping(target = "idQcmQuestions", ignore = true)
    @Mapping(target = "removeIdQcmQuestion", ignore = true)
    @Mapping(source = "qcmTestResponseId", target = "qcmTestResponse")
    QcmReponse toEntity(QcmReponseDTO qcmReponseDTO);

    default QcmReponse fromId(Long id) {
        if (id == null) {
            return null;
        }
        QcmReponse qcmReponse = new QcmReponse();
        qcmReponse.setId(id);
        return qcmReponse;
    }
}
