package com.mycompany.myapp.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class QcmReponseMapperTest {

    private QcmReponseMapper qcmReponseMapper;

    @BeforeEach
    public void setUp() {
        qcmReponseMapper = new QcmReponseMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(qcmReponseMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(qcmReponseMapper.fromId(null)).isNull();
    }
}
