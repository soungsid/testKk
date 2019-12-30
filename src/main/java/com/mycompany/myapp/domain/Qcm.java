package com.mycompany.myapp.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Qcm.
 */
@Entity
@Table(name = "qcm")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Qcm implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(min = 3)
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "nb_question")
    private Integer nbQuestion;

    @Column(name = "privee")
    private Boolean privee;

    @OneToMany(mappedBy = "qcm")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Utilisateur> createBies = new HashSet<>();

    @OneToMany(mappedBy = "qcm")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Categorie> idCategories = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("idQcms")
    private QcmQuestion qcmQuestion;

    @ManyToOne
    @JsonIgnoreProperties("idQcms")
    private QcmTest qcmTest;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Qcm name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNbQuestion() {
        return nbQuestion;
    }

    public Qcm nbQuestion(Integer nbQuestion) {
        this.nbQuestion = nbQuestion;
        return this;
    }

    public void setNbQuestion(Integer nbQuestion) {
        this.nbQuestion = nbQuestion;
    }

    public Boolean isPrivee() {
        return privee;
    }

    public Qcm privee(Boolean privee) {
        this.privee = privee;
        return this;
    }

    public void setPrivee(Boolean privee) {
        this.privee = privee;
    }

    public Set<Utilisateur> getCreateBies() {
        return createBies;
    }

    public Qcm createBies(Set<Utilisateur> utilisateurs) {
        this.createBies = utilisateurs;
        return this;
    }

    public Qcm addCreateBy(Utilisateur utilisateur) {
        this.createBies.add(utilisateur);
        utilisateur.setQcm(this);
        return this;
    }

    public Qcm removeCreateBy(Utilisateur utilisateur) {
        this.createBies.remove(utilisateur);
        utilisateur.setQcm(null);
        return this;
    }

    public void setCreateBies(Set<Utilisateur> utilisateurs) {
        this.createBies = utilisateurs;
    }

    public Set<Categorie> getIdCategories() {
        return idCategories;
    }

    public Qcm idCategories(Set<Categorie> categories) {
        this.idCategories = categories;
        return this;
    }

    public Qcm addIdCategorie(Categorie categorie) {
        this.idCategories.add(categorie);
        categorie.setQcm(this);
        return this;
    }

    public Qcm removeIdCategorie(Categorie categorie) {
        this.idCategories.remove(categorie);
        categorie.setQcm(null);
        return this;
    }

    public void setIdCategories(Set<Categorie> categories) {
        this.idCategories = categories;
    }

    public QcmQuestion getQcmQuestion() {
        return qcmQuestion;
    }

    public Qcm qcmQuestion(QcmQuestion qcmQuestion) {
        this.qcmQuestion = qcmQuestion;
        return this;
    }

    public void setQcmQuestion(QcmQuestion qcmQuestion) {
        this.qcmQuestion = qcmQuestion;
    }

    public QcmTest getQcmTest() {
        return qcmTest;
    }

    public Qcm qcmTest(QcmTest qcmTest) {
        this.qcmTest = qcmTest;
        return this;
    }

    public void setQcmTest(QcmTest qcmTest) {
        this.qcmTest = qcmTest;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Qcm)) {
            return false;
        }
        return id != null && id.equals(((Qcm) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Qcm{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", nbQuestion=" + getNbQuestion() +
            ", privee='" + isPrivee() + "'" +
            "}";
    }
}
