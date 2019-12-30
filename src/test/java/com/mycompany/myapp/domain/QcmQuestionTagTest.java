package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class QcmQuestionTagTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(QcmQuestionTag.class);
        QcmQuestionTag qcmQuestionTag1 = new QcmQuestionTag();
        qcmQuestionTag1.setId(1L);
        QcmQuestionTag qcmQuestionTag2 = new QcmQuestionTag();
        qcmQuestionTag2.setId(qcmQuestionTag1.getId());
        assertThat(qcmQuestionTag1).isEqualTo(qcmQuestionTag2);
        qcmQuestionTag2.setId(2L);
        assertThat(qcmQuestionTag1).isNotEqualTo(qcmQuestionTag2);
        qcmQuestionTag1.setId(null);
        assertThat(qcmQuestionTag1).isNotEqualTo(qcmQuestionTag2);
    }
}
