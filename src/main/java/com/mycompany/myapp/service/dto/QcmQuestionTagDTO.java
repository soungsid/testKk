package com.mycompany.myapp.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.QcmQuestionTag} entity.
 */
public class QcmQuestionTagDTO implements Serializable {

    private Long id;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        QcmQuestionTagDTO qcmQuestionTagDTO = (QcmQuestionTagDTO) o;
        if (qcmQuestionTagDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), qcmQuestionTagDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "QcmQuestionTagDTO{" +
            "id=" + getId() +
            "}";
    }
}
