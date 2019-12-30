package com.mycompany.myapp.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A QcmReponse.
 */
@Entity
@Table(name = "qcm_reponse")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class QcmReponse implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "reponse_text")
    private String reponseText;

    @Lob
    @Column(name = "reponse_image")
    private byte[] reponseImage;

    @Column(name = "reponse_image_content_type")
    private String reponseImageContentType;

    @Column(name = "reponse_nombre")
    private Double reponseNombre;

    @Column(name = "correct")
    private Boolean correct;

    @Column(name = "poids")
    private Long poids;

    @OneToMany(mappedBy = "qcmReponse")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<QcmQuestion> idQcmQuestions = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("idQcmReponses")
    private QcmTestResponse qcmTestResponse;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReponseText() {
        return reponseText;
    }

    public QcmReponse reponseText(String reponseText) {
        this.reponseText = reponseText;
        return this;
    }

    public void setReponseText(String reponseText) {
        this.reponseText = reponseText;
    }

    public byte[] getReponseImage() {
        return reponseImage;
    }

    public QcmReponse reponseImage(byte[] reponseImage) {
        this.reponseImage = reponseImage;
        return this;
    }

    public void setReponseImage(byte[] reponseImage) {
        this.reponseImage = reponseImage;
    }

    public String getReponseImageContentType() {
        return reponseImageContentType;
    }

    public QcmReponse reponseImageContentType(String reponseImageContentType) {
        this.reponseImageContentType = reponseImageContentType;
        return this;
    }

    public void setReponseImageContentType(String reponseImageContentType) {
        this.reponseImageContentType = reponseImageContentType;
    }

    public Double getReponseNombre() {
        return reponseNombre;
    }

    public QcmReponse reponseNombre(Double reponseNombre) {
        this.reponseNombre = reponseNombre;
        return this;
    }

    public void setReponseNombre(Double reponseNombre) {
        this.reponseNombre = reponseNombre;
    }

    public Boolean isCorrect() {
        return correct;
    }

    public QcmReponse correct(Boolean correct) {
        this.correct = correct;
        return this;
    }

    public void setCorrect(Boolean correct) {
        this.correct = correct;
    }

    public Long getPoids() {
        return poids;
    }

    public QcmReponse poids(Long poids) {
        this.poids = poids;
        return this;
    }

    public void setPoids(Long poids) {
        this.poids = poids;
    }

    public Set<QcmQuestion> getIdQcmQuestions() {
        return idQcmQuestions;
    }

    public QcmReponse idQcmQuestions(Set<QcmQuestion> qcmQuestions) {
        this.idQcmQuestions = qcmQuestions;
        return this;
    }

    public QcmReponse addIdQcmQuestion(QcmQuestion qcmQuestion) {
        this.idQcmQuestions.add(qcmQuestion);
        qcmQuestion.setQcmReponse(this);
        return this;
    }

    public QcmReponse removeIdQcmQuestion(QcmQuestion qcmQuestion) {
        this.idQcmQuestions.remove(qcmQuestion);
        qcmQuestion.setQcmReponse(null);
        return this;
    }

    public void setIdQcmQuestions(Set<QcmQuestion> qcmQuestions) {
        this.idQcmQuestions = qcmQuestions;
    }

    public QcmTestResponse getQcmTestResponse() {
        return qcmTestResponse;
    }

    public QcmReponse qcmTestResponse(QcmTestResponse qcmTestResponse) {
        this.qcmTestResponse = qcmTestResponse;
        return this;
    }

    public void setQcmTestResponse(QcmTestResponse qcmTestResponse) {
        this.qcmTestResponse = qcmTestResponse;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof QcmReponse)) {
            return false;
        }
        return id != null && id.equals(((QcmReponse) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "QcmReponse{" +
            "id=" + getId() +
            ", reponseText='" + getReponseText() + "'" +
            ", reponseImage='" + getReponseImage() + "'" +
            ", reponseImageContentType='" + getReponseImageContentType() + "'" +
            ", reponseNombre=" + getReponseNombre() +
            ", correct='" + isCorrect() + "'" +
            ", poids=" + getPoids() +
            "}";
    }
}
