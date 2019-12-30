package com.mycompany.myapp.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class QcmTestResponseMapperTest {

    private QcmTestResponseMapper qcmTestResponseMapper;

    @BeforeEach
    public void setUp() {
        qcmTestResponseMapper = new QcmTestResponseMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(qcmTestResponseMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(qcmTestResponseMapper.fromId(null)).isNull();
    }
}
