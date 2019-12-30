package com.mycompany.myapp.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class QcmTestMapperTest {

    private QcmTestMapper qcmTestMapper;

    @BeforeEach
    public void setUp() {
        qcmTestMapper = new QcmTestMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(qcmTestMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(qcmTestMapper.fromId(null)).isNull();
    }
}
