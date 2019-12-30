package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class QcmReponseTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(QcmReponse.class);
        QcmReponse qcmReponse1 = new QcmReponse();
        qcmReponse1.setId(1L);
        QcmReponse qcmReponse2 = new QcmReponse();
        qcmReponse2.setId(qcmReponse1.getId());
        assertThat(qcmReponse1).isEqualTo(qcmReponse2);
        qcmReponse2.setId(2L);
        assertThat(qcmReponse1).isNotEqualTo(qcmReponse2);
        qcmReponse1.setId(null);
        assertThat(qcmReponse1).isNotEqualTo(qcmReponse2);
    }
}
