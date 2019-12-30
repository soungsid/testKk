package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class QcmTestResponseTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(QcmTestResponse.class);
        QcmTestResponse qcmTestResponse1 = new QcmTestResponse();
        qcmTestResponse1.setId(1L);
        QcmTestResponse qcmTestResponse2 = new QcmTestResponse();
        qcmTestResponse2.setId(qcmTestResponse1.getId());
        assertThat(qcmTestResponse1).isEqualTo(qcmTestResponse2);
        qcmTestResponse2.setId(2L);
        assertThat(qcmTestResponse1).isNotEqualTo(qcmTestResponse2);
        qcmTestResponse1.setId(null);
        assertThat(qcmTestResponse1).isNotEqualTo(qcmTestResponse2);
    }
}
