package com.mycompany.myapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class QcmQuestionTagDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(QcmQuestionTagDTO.class);
        QcmQuestionTagDTO qcmQuestionTagDTO1 = new QcmQuestionTagDTO();
        qcmQuestionTagDTO1.setId(1L);
        QcmQuestionTagDTO qcmQuestionTagDTO2 = new QcmQuestionTagDTO();
        assertThat(qcmQuestionTagDTO1).isNotEqualTo(qcmQuestionTagDTO2);
        qcmQuestionTagDTO2.setId(qcmQuestionTagDTO1.getId());
        assertThat(qcmQuestionTagDTO1).isEqualTo(qcmQuestionTagDTO2);
        qcmQuestionTagDTO2.setId(2L);
        assertThat(qcmQuestionTagDTO1).isNotEqualTo(qcmQuestionTagDTO2);
        qcmQuestionTagDTO1.setId(null);
        assertThat(qcmQuestionTagDTO1).isNotEqualTo(qcmQuestionTagDTO2);
    }
}
