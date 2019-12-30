package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class CarnetAdresseTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CarnetAdresse.class);
        CarnetAdresse carnetAdresse1 = new CarnetAdresse();
        carnetAdresse1.setId(1L);
        CarnetAdresse carnetAdresse2 = new CarnetAdresse();
        carnetAdresse2.setId(carnetAdresse1.getId());
        assertThat(carnetAdresse1).isEqualTo(carnetAdresse2);
        carnetAdresse2.setId(2L);
        assertThat(carnetAdresse1).isNotEqualTo(carnetAdresse2);
        carnetAdresse1.setId(null);
        assertThat(carnetAdresse1).isNotEqualTo(carnetAdresse2);
    }
}
