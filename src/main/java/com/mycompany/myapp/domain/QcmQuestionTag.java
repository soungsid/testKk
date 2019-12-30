package com.mycompany.myapp.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A QcmQuestionTag.
 */
@Entity
@Table(name = "qcm_question_tag")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class QcmQuestionTag implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @OneToMany(mappedBy = "qcmQuestionTag")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<QcmQuestion> idQcmQuestions = new HashSet<>();

    @OneToMany(mappedBy = "qcmQuestionTag")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Tag> idTags = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<QcmQuestion> getIdQcmQuestions() {
        return idQcmQuestions;
    }

    public QcmQuestionTag idQcmQuestions(Set<QcmQuestion> qcmQuestions) {
        this.idQcmQuestions = qcmQuestions;
        return this;
    }

    public QcmQuestionTag addIdQcmQuestion(QcmQuestion qcmQuestion) {
        this.idQcmQuestions.add(qcmQuestion);
        qcmQuestion.setQcmQuestionTag(this);
        return this;
    }

    public QcmQuestionTag removeIdQcmQuestion(QcmQuestion qcmQuestion) {
        this.idQcmQuestions.remove(qcmQuestion);
        qcmQuestion.setQcmQuestionTag(null);
        return this;
    }

    public void setIdQcmQuestions(Set<QcmQuestion> qcmQuestions) {
        this.idQcmQuestions = qcmQuestions;
    }

    public Set<Tag> getIdTags() {
        return idTags;
    }

    public QcmQuestionTag idTags(Set<Tag> tags) {
        this.idTags = tags;
        return this;
    }

    public QcmQuestionTag addIdTag(Tag tag) {
        this.idTags.add(tag);
        tag.setQcmQuestionTag(this);
        return this;
    }

    public QcmQuestionTag removeIdTag(Tag tag) {
        this.idTags.remove(tag);
        tag.setQcmQuestionTag(null);
        return this;
    }

    public void setIdTags(Set<Tag> tags) {
        this.idTags = tags;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof QcmQuestionTag)) {
            return false;
        }
        return id != null && id.equals(((QcmQuestionTag) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "QcmQuestionTag{" +
            "id=" + getId() +
            "}";
    }
}
