package com.mycompany.myapp.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class SocieteAbonneMapperTest {

    private SocieteAbonneMapper societeAbonneMapper;

    @BeforeEach
    public void setUp() {
        societeAbonneMapper = new SocieteAbonneMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(societeAbonneMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(societeAbonneMapper.fromId(null)).isNull();
    }
}
