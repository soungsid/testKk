package com.mycompany.myapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class CarnetAdresseDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CarnetAdresseDTO.class);
        CarnetAdresseDTO carnetAdresseDTO1 = new CarnetAdresseDTO();
        carnetAdresseDTO1.setId(1L);
        CarnetAdresseDTO carnetAdresseDTO2 = new CarnetAdresseDTO();
        assertThat(carnetAdresseDTO1).isNotEqualTo(carnetAdresseDTO2);
        carnetAdresseDTO2.setId(carnetAdresseDTO1.getId());
        assertThat(carnetAdresseDTO1).isEqualTo(carnetAdresseDTO2);
        carnetAdresseDTO2.setId(2L);
        assertThat(carnetAdresseDTO1).isNotEqualTo(carnetAdresseDTO2);
        carnetAdresseDTO1.setId(null);
        assertThat(carnetAdresseDTO1).isNotEqualTo(carnetAdresseDTO2);
    }
}
