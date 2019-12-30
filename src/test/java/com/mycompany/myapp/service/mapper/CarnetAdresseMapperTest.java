package com.mycompany.myapp.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class CarnetAdresseMapperTest {

    private CarnetAdresseMapper carnetAdresseMapper;

    @BeforeEach
    public void setUp() {
        carnetAdresseMapper = new CarnetAdresseMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(carnetAdresseMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(carnetAdresseMapper.fromId(null)).isNull();
    }
}
