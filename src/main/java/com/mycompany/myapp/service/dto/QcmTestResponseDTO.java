package com.mycompany.myapp.service.dto;
import java.time.Instant;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.QcmTestResponse} entity.
 */
public class QcmTestResponseDTO implements Serializable {

    private Long id;

    private Instant dateSoumission;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getDateSoumission() {
        return dateSoumission;
    }

    public void setDateSoumission(Instant dateSoumission) {
        this.dateSoumission = dateSoumission;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        QcmTestResponseDTO qcmTestResponseDTO = (QcmTestResponseDTO) o;
        if (qcmTestResponseDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), qcmTestResponseDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "QcmTestResponseDTO{" +
            "id=" + getId() +
            ", dateSoumission='" + getDateSoumission() + "'" +
            "}";
    }
}
