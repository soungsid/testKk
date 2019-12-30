package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class SocieteAbonneTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SocieteAbonne.class);
        SocieteAbonne societeAbonne1 = new SocieteAbonne();
        societeAbonne1.setId(1L);
        SocieteAbonne societeAbonne2 = new SocieteAbonne();
        societeAbonne2.setId(societeAbonne1.getId());
        assertThat(societeAbonne1).isEqualTo(societeAbonne2);
        societeAbonne2.setId(2L);
        assertThat(societeAbonne1).isNotEqualTo(societeAbonne2);
        societeAbonne1.setId(null);
        assertThat(societeAbonne1).isNotEqualTo(societeAbonne2);
    }
}
