package com.mycompany.myapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class QcmQuestionDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(QcmQuestionDTO.class);
        QcmQuestionDTO qcmQuestionDTO1 = new QcmQuestionDTO();
        qcmQuestionDTO1.setId(1L);
        QcmQuestionDTO qcmQuestionDTO2 = new QcmQuestionDTO();
        assertThat(qcmQuestionDTO1).isNotEqualTo(qcmQuestionDTO2);
        qcmQuestionDTO2.setId(qcmQuestionDTO1.getId());
        assertThat(qcmQuestionDTO1).isEqualTo(qcmQuestionDTO2);
        qcmQuestionDTO2.setId(2L);
        assertThat(qcmQuestionDTO1).isNotEqualTo(qcmQuestionDTO2);
        qcmQuestionDTO1.setId(null);
        assertThat(qcmQuestionDTO1).isNotEqualTo(qcmQuestionDTO2);
    }
}
