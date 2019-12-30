package com.mycompany.myapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class QcmTestResponseDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(QcmTestResponseDTO.class);
        QcmTestResponseDTO qcmTestResponseDTO1 = new QcmTestResponseDTO();
        qcmTestResponseDTO1.setId(1L);
        QcmTestResponseDTO qcmTestResponseDTO2 = new QcmTestResponseDTO();
        assertThat(qcmTestResponseDTO1).isNotEqualTo(qcmTestResponseDTO2);
        qcmTestResponseDTO2.setId(qcmTestResponseDTO1.getId());
        assertThat(qcmTestResponseDTO1).isEqualTo(qcmTestResponseDTO2);
        qcmTestResponseDTO2.setId(2L);
        assertThat(qcmTestResponseDTO1).isNotEqualTo(qcmTestResponseDTO2);
        qcmTestResponseDTO1.setId(null);
        assertThat(qcmTestResponseDTO1).isNotEqualTo(qcmTestResponseDTO2);
    }
}
