package com.mycompany.myapp.service.dto;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.QcmReponse} entity.
 */
public class QcmReponseDTO implements Serializable {

    private Long id;

    private String reponseText;

    @Lob
    private byte[] reponseImage;

    private String reponseImageContentType;
    private Double reponseNombre;

    private Boolean correct;

    private Long poids;


    private Long qcmTestResponseId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReponseText() {
        return reponseText;
    }

    public void setReponseText(String reponseText) {
        this.reponseText = reponseText;
    }

    public byte[] getReponseImage() {
        return reponseImage;
    }

    public void setReponseImage(byte[] reponseImage) {
        this.reponseImage = reponseImage;
    }

    public String getReponseImageContentType() {
        return reponseImageContentType;
    }

    public void setReponseImageContentType(String reponseImageContentType) {
        this.reponseImageContentType = reponseImageContentType;
    }

    public Double getReponseNombre() {
        return reponseNombre;
    }

    public void setReponseNombre(Double reponseNombre) {
        this.reponseNombre = reponseNombre;
    }

    public Boolean isCorrect() {
        return correct;
    }

    public void setCorrect(Boolean correct) {
        this.correct = correct;
    }

    public Long getPoids() {
        return poids;
    }

    public void setPoids(Long poids) {
        this.poids = poids;
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

        QcmReponseDTO qcmReponseDTO = (QcmReponseDTO) o;
        if (qcmReponseDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), qcmReponseDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "QcmReponseDTO{" +
            "id=" + getId() +
            ", reponseText='" + getReponseText() + "'" +
            ", reponseImage='" + getReponseImage() + "'" +
            ", reponseNombre=" + getReponseNombre() +
            ", correct='" + isCorrect() + "'" +
            ", poids=" + getPoids() +
            ", qcmTestResponseId=" + getQcmTestResponseId() +
            "}";
    }
}
