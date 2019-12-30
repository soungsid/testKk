package com.mycompany.myapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class QcmReponseDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(QcmReponseDTO.class);
        QcmReponseDTO qcmReponseDTO1 = new QcmReponseDTO();
        qcmReponseDTO1.setId(1L);
        QcmReponseDTO qcmReponseDTO2 = new QcmReponseDTO();
        assertThat(qcmReponseDTO1).isNotEqualTo(qcmReponseDTO2);
        qcmReponseDTO2.setId(qcmReponseDTO1.getId());
        assertThat(qcmReponseDTO1).isEqualTo(qcmReponseDTO2);
        qcmReponseDTO2.setId(2L);
        assertThat(qcmReponseDTO1).isNotEqualTo(qcmReponseDTO2);
        qcmReponseDTO1.setId(null);
        assertThat(qcmReponseDTO1).isNotEqualTo(qcmReponseDTO2);
    }
}
