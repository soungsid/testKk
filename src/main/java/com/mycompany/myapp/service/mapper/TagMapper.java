package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.TagDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Tag} and its DTO {@link TagDTO}.
 */
@Mapper(componentModel = "spring", uses = {QcmQuestionTagMapper.class})
public interface TagMapper extends EntityMapper<TagDTO, Tag> {

    @Mapping(source = "qcmQuestionTag.id", target = "qcmQuestionTagId")
    TagDTO toDto(Tag tag);

    @Mapping(source = "qcmQuestionTagId", target = "qcmQuestionTag")
    Tag toEntity(TagDTO tagDTO);

    default Tag fromId(Long id) {
        if (id == null) {
            return null;
        }
        Tag tag = new Tag();
        tag.setId(id);
        return tag;
    }
}
