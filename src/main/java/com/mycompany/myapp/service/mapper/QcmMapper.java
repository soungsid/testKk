package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.QcmDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Qcm} and its DTO {@link QcmDTO}.
 */
@Mapper(componentModel = "spring", uses = {QcmQuestionMapper.class, QcmTestMapper.class})
public interface QcmMapper extends EntityMapper<QcmDTO, Qcm> {

    @Mapping(source = "qcmQuestion.id", target = "qcmQuestionId")
    @Mapping(source = "qcmTest.id", target = "qcmTestId")
    QcmDTO toDto(Qcm qcm);

    @Mapping(target = "createBies", ignore = true)
    @Mapping(target = "removeCreateBy", ignore = true)
    @Mapping(target = "idCategories", ignore = true)
    @Mapping(target = "removeIdCategorie", ignore = true)
    @Mapping(source = "qcmQuestionId", target = "qcmQuestion")
    @Mapping(source = "qcmTestId", target = "qcmTest")
    Qcm toEntity(QcmDTO qcmDTO);

    default Qcm fromId(Long id) {
        if (id == null) {
            return null;
        }
        Qcm qcm = new Qcm();
        qcm.setId(id);
        return qcm;
    }
}
