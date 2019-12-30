package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class QcmTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Qcm.class);
        Qcm qcm1 = new Qcm();
        qcm1.setId(1L);
        Qcm qcm2 = new Qcm();
        qcm2.setId(qcm1.getId());
        assertThat(qcm1).isEqualTo(qcm2);
        qcm2.setId(2L);
        assertThat(qcm1).isNotEqualTo(qcm2);
        qcm1.setId(null);
        assertThat(qcm1).isNotEqualTo(qcm2);
    }
}
