package com.mycompany.myapp.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

/**
 * A QcmTestResponse.
 */
@Entity
@Table(name = "qcm_test_response")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class QcmTestResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "date_soumission")
    private Instant dateSoumission;

    @OneToMany(mappedBy = "qcmTestResponse")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<QcmTest> idQcmTests = new HashSet<>();

    @OneToMany(mappedBy = "qcmTestResponse")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<QcmQuestion> idQcmQuestions = new HashSet<>();

    @OneToMany(mappedBy = "qcmTestResponse")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<QcmReponse> idQcmReponses = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getDateSoumission() {
        return dateSoumission;
    }

    public QcmTestResponse dateSoumission(Instant dateSoumission) {
        this.dateSoumission = dateSoumission;
        return this;
    }

    public void setDateSoumission(Instant dateSoumission) {
        this.dateSoumission = dateSoumission;
    }

    public Set<QcmTest> getIdQcmTests() {
        return idQcmTests;
    }

    public QcmTestResponse idQcmTests(Set<QcmTest> qcmTests) {
        this.idQcmTests = qcmTests;
        return this;
    }

    public QcmTestResponse addIdQcmTest(QcmTest qcmTest) {
        this.idQcmTests.add(qcmTest);
        qcmTest.setQcmTestResponse(this);
        return this;
    }

    public QcmTestResponse removeIdQcmTest(QcmTest qcmTest) {
        this.idQcmTests.remove(qcmTest);
        qcmTest.setQcmTestResponse(null);
        return this;
    }

    public void setIdQcmTests(Set<QcmTest> qcmTests) {
        this.idQcmTests = qcmTests;
    }

    public Set<QcmQuestion> getIdQcmQuestions() {
        return idQcmQuestions;
    }

    public QcmTestResponse idQcmQuestions(Set<QcmQuestion> qcmQuestions) {
        this.idQcmQuestions = qcmQuestions;
        return this;
    }

    public QcmTestResponse addIdQcmQuestion(QcmQuestion qcmQuestion) {
        this.idQcmQuestions.add(qcmQuestion);
        qcmQuestion.setQcmTestResponse(this);
        return this;
    }

    public QcmTestResponse removeIdQcmQuestion(QcmQuestion qcmQuestion) {
        this.idQcmQuestions.remove(qcmQuestion);
        qcmQuestion.setQcmTestResponse(null);
        return this;
    }

    public void setIdQcmQuestions(Set<QcmQuestion> qcmQuestions) {
        this.idQcmQuestions = qcmQuestions;
    }

    public Set<QcmReponse> getIdQcmReponses() {
        return idQcmReponses;
    }

    public QcmTestResponse idQcmReponses(Set<QcmReponse> qcmReponses) {
        this.idQcmReponses = qcmReponses;
        return this;
    }

    public QcmTestResponse addIdQcmReponse(QcmReponse qcmReponse) {
        this.idQcmReponses.add(qcmReponse);
        qcmReponse.setQcmTestResponse(this);
        return this;
    }

    public QcmTestResponse removeIdQcmReponse(QcmReponse qcmReponse) {
        this.idQcmReponses.remove(qcmReponse);
        qcmReponse.setQcmTestResponse(null);
        return this;
    }

    public void setIdQcmReponses(Set<QcmReponse> qcmReponses) {
        this.idQcmReponses = qcmReponses;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof QcmTestResponse)) {
            return false;
        }
        return id != null && id.equals(((QcmTestResponse) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "QcmTestResponse{" +
            "id=" + getId() +
            ", dateSoumission='" + getDateSoumission() + "'" +
            "}";
    }
}
