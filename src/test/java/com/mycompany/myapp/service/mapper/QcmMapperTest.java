package com.mycompany.myapp.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class QcmMapperTest {

    private QcmMapper qcmMapper;

    @BeforeEach
    public void setUp() {
        qcmMapper = new QcmMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(qcmMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(qcmMapper.fromId(null)).isNull();
    }
}
