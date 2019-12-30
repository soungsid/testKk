package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class QcmTestTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(QcmTest.class);
        QcmTest qcmTest1 = new QcmTest();
        qcmTest1.setId(1L);
        QcmTest qcmTest2 = new QcmTest();
        qcmTest2.setId(qcmTest1.getId());
        assertThat(qcmTest1).isEqualTo(qcmTest2);
        qcmTest2.setId(2L);
        assertThat(qcmTest1).isNotEqualTo(qcmTest2);
        qcmTest1.setId(null);
        assertThat(qcmTest1).isNotEqualTo(qcmTest2);
    }
}
