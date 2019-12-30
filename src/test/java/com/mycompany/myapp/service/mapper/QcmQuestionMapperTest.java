package com.mycompany.myapp.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class QcmQuestionMapperTest {

    private QcmQuestionMapper qcmQuestionMapper;

    @BeforeEach
    public void setUp() {
        qcmQuestionMapper = new QcmQuestionMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(qcmQuestionMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(qcmQuestionMapper.fromId(null)).isNull();
    }
}
