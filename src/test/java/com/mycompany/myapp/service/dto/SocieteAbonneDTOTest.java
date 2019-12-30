package com.mycompany.myapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class SocieteAbonneDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SocieteAbonneDTO.class);
        SocieteAbonneDTO societeAbonneDTO1 = new SocieteAbonneDTO();
        societeAbonneDTO1.setId(1L);
        SocieteAbonneDTO societeAbonneDTO2 = new SocieteAbonneDTO();
        assertThat(societeAbonneDTO1).isNotEqualTo(societeAbonneDTO2);
        societeAbonneDTO2.setId(societeAbonneDTO1.getId());
        assertThat(societeAbonneDTO1).isEqualTo(societeAbonneDTO2);
        societeAbonneDTO2.setId(2L);
        assertThat(societeAbonneDTO1).isNotEqualTo(societeAbonneDTO2);
        societeAbonneDTO1.setId(null);
        assertThat(societeAbonneDTO1).isNotEqualTo(societeAbonneDTO2);
    }
}
