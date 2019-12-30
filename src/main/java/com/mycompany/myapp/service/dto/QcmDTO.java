package com.mycompany.myapp.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.Qcm} entity.
 */
public class QcmDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(min = 3)
    private String name;

    private Integer nbQuestion;

    private Boolean privee;


    private Long qcmQuestionId;

    private Long qcmTestId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNbQuestion() {
        return nbQuestion;
    }

    public void setNbQuestion(Integer nbQuestion) {
        this.nbQuestion = nbQuestion;
    }

    public Boolean isPrivee() {
        return privee;
    }

    public void setPrivee(Boolean privee) {
        this.privee = privee;
    }

    public Long getQcmQuestionId() {
        return qcmQuestionId;
    }

    public void setQcmQuestionId(Long qcmQuestionId) {
        this.qcmQuestionId = qcmQuestionId;
    }

    public Long getQcmTestId() {
        return qcmTestId;
    }

    public void setQcmTestId(Long qcmTestId) {
        this.qcmTestId = qcmTestId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        QcmDTO qcmDTO = (QcmDTO) o;
        if (qcmDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), qcmDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "QcmDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", nbQuestion=" + getNbQuestion() +
            ", privee='" + isPrivee() + "'" +
            ", qcmQuestionId=" + getQcmQuestionId() +
            ", qcmTestId=" + getQcmTestId() +
            "}";
    }
}
