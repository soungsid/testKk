package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.QcmTestDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link QcmTest} and its DTO {@link QcmTestDTO}.
 */
@Mapper(componentModel = "spring", uses = {QcmTestResponseMapper.class})
public interface QcmTestMapper extends EntityMapper<QcmTestDTO, QcmTest> {

    @Mapping(source = "qcmTestResponse.id", target = "qcmTestResponseId")
    QcmTestDTO toDto(QcmTest qcmTest);

    @Mapping(target = "idQcms", ignore = true)
    @Mapping(target = "removeIdQcm", ignore = true)
    @Mapping(target = "idUtilisateurs", ignore = true)
    @Mapping(target = "removeIdUtilisateur", ignore = true)
    @Mapping(source = "qcmTestResponseId", target = "qcmTestResponse")
    QcmTest toEntity(QcmTestDTO qcmTestDTO);

    default QcmTest fromId(Long id) {
        if (id == null) {
            return null;
        }
        QcmTest qcmTest = new QcmTest();
        qcmTest.setId(id);
        return qcmTest;
    }
}
