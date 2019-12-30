package com.mycompany.myapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class QcmTestDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(QcmTestDTO.class);
        QcmTestDTO qcmTestDTO1 = new QcmTestDTO();
        qcmTestDTO1.setId(1L);
        QcmTestDTO qcmTestDTO2 = new QcmTestDTO();
        assertThat(qcmTestDTO1).isNotEqualTo(qcmTestDTO2);
        qcmTestDTO2.setId(qcmTestDTO1.getId());
        assertThat(qcmTestDTO1).isEqualTo(qcmTestDTO2);
        qcmTestDTO2.setId(2L);
        assertThat(qcmTestDTO1).isNotEqualTo(qcmTestDTO2);
        qcmTestDTO1.setId(null);
        assertThat(qcmTestDTO1).isNotEqualTo(qcmTestDTO2);
    }
}
