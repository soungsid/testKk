package com.mycompany.myapp.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class QcmQuestionTagMapperTest {

    private QcmQuestionTagMapper qcmQuestionTagMapper;

    @BeforeEach
    public void setUp() {
        qcmQuestionTagMapper = new QcmQuestionTagMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(qcmQuestionTagMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(qcmQuestionTagMapper.fromId(null)).isNull();
    }
}
