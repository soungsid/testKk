package com.mycompany.myapp.service.dto;
import java.time.Instant;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.QcmTest} entity.
 */
public class QcmTestDTO implements Serializable {

    private Long id;

    private Instant dateDebut;

    private Instant dateFin;

    private String email;

    private Double score;

    private Double highScore;


    private Long qcmTestResponseId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Instant dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Instant getDateFin() {
        return dateFin;
    }

    public void setDateFin(Instant dateFin) {
        this.dateFin = dateFin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Double getHighScore() {
        return highScore;
    }

    public void setHighScore(Double highScore) {
        this.highScore = highScore;
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

        QcmTestDTO qcmTestDTO = (QcmTestDTO) o;
        if (qcmTestDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), qcmTestDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "QcmTestDTO{" +
            "id=" + getId() +
            ", dateDebut='" + getDateDebut() + "'" +
            ", dateFin='" + getDateFin() + "'" +
            ", email='" + getEmail() + "'" +
            ", score=" + getScore() +
            ", highScore=" + getHighScore() +
            ", qcmTestResponseId=" + getQcmTestResponseId() +
            "}";
    }
}
