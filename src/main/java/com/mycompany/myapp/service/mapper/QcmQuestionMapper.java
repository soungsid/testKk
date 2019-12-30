package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.QcmQuestionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link QcmQuestion} and its DTO {@link QcmQuestionDTO}.
 */
@Mapper(componentModel = "spring", uses = {QcmQuestionTagMapper.class, QcmReponseMapper.class, QcmTestResponseMapper.class})
public interface QcmQuestionMapper extends EntityMapper<QcmQuestionDTO, QcmQuestion> {

    @Mapping(source = "qcmQuestionTag.id", target = "qcmQuestionTagId")
    @Mapping(source = "qcmReponse.id", target = "qcmReponseId")
    @Mapping(source = "qcmTestResponse.id", target = "qcmTestResponseId")
    QcmQuestionDTO toDto(QcmQuestion qcmQuestion);

    @Mapping(target = "idQcms", ignore = true)
    @Mapping(target = "removeIdQcm", ignore = true)
    @Mapping(source = "qcmQuestionTagId", target = "qcmQuestionTag")
    @Mapping(source = "qcmReponseId", target = "qcmReponse")
    @Mapping(source = "qcmTestResponseId", target = "qcmTestResponse")
    QcmQuestion toEntity(QcmQuestionDTO qcmQuestionDTO);

    default QcmQuestion fromId(Long id) {
        if (id == null) {
            return null;
        }
        QcmQuestion qcmQuestion = new QcmQuestion();
        qcmQuestion.setId(id);
        return qcmQuestion;
    }
}
