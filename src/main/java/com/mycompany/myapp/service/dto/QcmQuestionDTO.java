package com.mycompany.myapp.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;
import com.mycompany.myapp.domain.enumeration.TypeQuestion;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.QcmQuestion} entity.
 */
public class QcmQuestionDTO implements Serializable {

    private Long id;

    @NotNull
    private String libelle;

    private TypeQuestion type;

    @Lob
    private String explication;


    private Long qcmQuestionTagId;

    private Long qcmReponseId;

    private Long qcmTestResponseId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public TypeQuestion getType() {
        return type;
    }

    public void setType(TypeQuestion type) {
        this.type = type;
    }

    public String getExplication() {
        return explication;
    }

    public void setExplication(String explication) {
        this.explication = explication;
    }

    public Long getQcmQuestionTagId() {
        return qcmQuestionTagId;
    }

    public void setQcmQuestionTagId(Long qcmQuestionTagId) {
        this.qcmQuestionTagId = qcmQuestionTagId;
    }

    public Long getQcmReponseId() {
        return qcmReponseId;
    }

    public void setQcmReponseId(Long qcmReponseId) {
        this.qcmReponseId = qcmReponseId;
    }

    public Long getQcmTestResponseId() {
        return qcmTestResponseId;
    }

    public void setQcmTestResponseId(Long qcmTestResponseId) {
        this.qcmTestResponseId = qcmTestResponseId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        QcmQuestionDTO qcmQuestionDTO = (QcmQuestionDTO) o;
        if (qcmQuestionDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), qcmQuestionDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "QcmQuestionDTO{" +
            "id=" + getId() +
            ", libelle='" + getLibelle() + "'" +
            ", type='" + getType() + "'" +
            ", explication='" + getExplication() + "'" +
            ", qcmQuestionTagId=" + getQcmQuestionTagId() +
            ", qcmReponseId=" + getQcmReponseId() +
            ", qcmTestResponseId=" + getQcmTestResponseId() +
            "}";
    }
}
