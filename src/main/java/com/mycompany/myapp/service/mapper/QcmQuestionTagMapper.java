package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.QcmQuestionTagDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link QcmQuestionTag} and its DTO {@link QcmQuestionTagDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface QcmQuestionTagMapper extends EntityMapper<QcmQuestionTagDTO, QcmQuestionTag> {


    @Mapping(target = "idQcmQuestions", ignore = true)
    @Mapping(target = "removeIdQcmQuestion", ignore = true)
    @Mapping(target = "idTags", ignore = true)
    @Mapping(target = "removeIdTag", ignore = true)
    QcmQuestionTag toEntity(QcmQuestionTagDTO qcmQuestionTagDTO);

    default QcmQuestionTag fromId(Long id) {
        if (id == null) {
            return null;
        }
        QcmQuestionTag qcmQuestionTag = new QcmQuestionTag();
        qcmQuestionTag.setId(id);
        return qcmQuestionTag;
    }
}
