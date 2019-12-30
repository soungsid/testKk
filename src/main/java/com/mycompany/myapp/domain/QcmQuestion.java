package com.mycompany.myapp.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.mycompany.myapp.domain.enumeration.TypeQuestion;

/**
 * A QcmQuestion.
 */
@Entity
@Table(name = "qcm_question")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class QcmQuestion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "libelle", nullable = false)
    private String libelle;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private TypeQuestion type;

    @Lob
    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "explication")
    private String explication;

    @OneToMany(mappedBy = "qcmQuestion")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Qcm> idQcms = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("idQcmQuestions")
    private QcmQuestionTag qcmQuestionTag;

    @ManyToOne
    @JsonIgnoreProperties("idQcmQuestions")
    private QcmReponse qcmReponse;

    @ManyToOne
    @JsonIgnoreProperties("idQcmQuestions")
    private QcmTestResponse qcmTestResponse;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public QcmQuestion libelle(String libelle) {
        this.libelle = libelle;
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public TypeQuestion getType() {
        return type;
    }

    public QcmQuestion type(TypeQuestion type) {
        this.type = type;
        return this;
    }

    public void setType(TypeQuestion type) {
        this.type = type;
    }

    public String getExplication() {
        return explication;
    }

    public QcmQuestion explication(String explication) {
        this.explication = explication;
        return this;
    }

    public void setExplication(String explication) {
        this.explication = explication;
    }

    public Set<Qcm> getIdQcms() {
        return idQcms;
    }

    public QcmQuestion idQcms(Set<Qcm> qcms) {
        this.idQcms = qcms;
        return this;
    }

    public QcmQuestion addIdQcm(Qcm qcm) {
        this.idQcms.add(qcm);
        qcm.setQcmQuestion(this);
        return this;
    }

    public QcmQuestion removeIdQcm(Qcm qcm) {
        this.idQcms.remove(qcm);
        qcm.setQcmQuestion(null);
        return this;
    }

    public void setIdQcms(Set<Qcm> qcms) {
        this.idQcms = qcms;
    }

    public QcmQuestionTag getQcmQuestionTag() {
        return qcmQuestionTag;
    }

    public QcmQuestion qcmQuestionTag(QcmQuestionTag qcmQuestionTag) {
        this.qcmQuestionTag = qcmQuestionTag;
        return this;
    }

    public void setQcmQuestionTag(QcmQuestionTag qcmQuestionTag) {
        this.qcmQuestionTag = qcmQuestionTag;
    }

    public QcmReponse getQcmReponse() {
        return qcmReponse;
    }

    public QcmQuestion qcmReponse(QcmReponse qcmReponse) {
        this.qcmReponse = qcmReponse;
        return this;
    }

    public void setQcmReponse(QcmReponse qcmReponse) {
        this.qcmReponse = qcmReponse;
    }

    public QcmTestResponse getQcmTestResponse() {
        return qcmTestResponse;
    }

    public QcmQuestion qcmTestResponse(QcmTestResponse qcmTestResponse) {
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
        if (!(o instanceof QcmQuestion)) {
            return false;
        }
        return id != null && id.equals(((QcmQuestion) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "QcmQuestion{" +
            "id=" + getId() +
            ", libelle='" + getLibelle() + "'" +
            ", type='" + getType() + "'" +
            ", explication='" + getExplication() + "'" +
            "}";
    }
}
