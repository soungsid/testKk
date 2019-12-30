package com.mycompany.myapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class QcmDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(QcmDTO.class);
        QcmDTO qcmDTO1 = new QcmDTO();
        qcmDTO1.setId(1L);
        QcmDTO qcmDTO2 = new QcmDTO();
        assertThat(qcmDTO1).isNotEqualTo(qcmDTO2);
        qcmDTO2.setId(qcmDTO1.getId());
        assertThat(qcmDTO1).isEqualTo(qcmDTO2);
        qcmDTO2.setId(2L);
        assertThat(qcmDTO1).isNotEqualTo(qcmDTO2);
        qcmDTO1.setId(null);
        assertThat(qcmDTO1).isNotEqualTo(qcmDTO2);
    }
}
