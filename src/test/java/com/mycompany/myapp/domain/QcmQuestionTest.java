package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class QcmQuestionTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(QcmQuestion.class);
        QcmQuestion qcmQuestion1 = new QcmQuestion();
        qcmQuestion1.setId(1L);
        QcmQuestion qcmQuestion2 = new QcmQuestion();
        qcmQuestion2.setId(qcmQuestion1.getId());
        assertThat(qcmQuestion1).isEqualTo(qcmQuestion2);
        qcmQuestion2.setId(2L);
        assertThat(qcmQuestion1).isNotEqualTo(qcmQuestion2);
        qcmQuestion1.setId(null);
        assertThat(qcmQuestion1).isNotEqualTo(qcmQuestion2);
    }
}
