package com.mycompany.myapp.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

/**
 * A QcmTest.
 */
@Entity
@Table(name = "qcm_test")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class QcmTest implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "date_debut")
    private Instant dateDebut;

    @Column(name = "date_fin")
    private Instant dateFin;

    @Column(name = "email")
    private String email;

    @Column(name = "score")
    private Double score;

    @Column(name = "high_score")
    private Double highScore;

    @OneToMany(mappedBy = "qcmTest")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Qcm> idQcms = new HashSet<>();

    @OneToMany(mappedBy = "qcmTest")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Utilisateur> idUtilisateurs = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("idQcmTests")
    private QcmTestResponse qcmTestResponse;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getDateDebut() {
        return dateDebut;
    }

    public QcmTest dateDebut(Instant dateDebut) {
        this.dateDebut = dateDebut;
        return this;
    }

    public void setDateDebut(Instant dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Instant getDateFin() {
        return dateFin;
    }

    public QcmTest dateFin(Instant dateFin) {
        this.dateFin = dateFin;
        return this;
    }

    public void setDateFin(Instant dateFin) {
        this.dateFin = dateFin;
    }

    public String getEmail() {
        return email;
    }

    public QcmTest email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getScore() {
        return score;
    }

    public QcmTest score(Double score) {
        this.score = score;
        return this;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Double getHighScore() {
        return highScore;
    }

    public QcmTest highScore(Double highScore) {
        this.highScore = highScore;
        return this;
    }

    public void setHighScore(Double highScore) {
        this.highScore = highScore;
    }

    public Set<Qcm> getIdQcms() {
        return idQcms;
    }

    public QcmTest idQcms(Set<Qcm> qcms) {
        this.idQcms = qcms;
        return this;
    }

    public QcmTest addIdQcm(Qcm qcm) {
        this.idQcms.add(qcm);
        qcm.setQcmTest(this);
        return this;
    }

    public QcmTest removeIdQcm(Qcm qcm) {
        this.idQcms.remove(qcm);
        qcm.setQcmTest(null);
        return this;
    }

    public void setIdQcms(Set<Qcm> qcms) {
        this.idQcms = qcms;
    }

    public Set<Utilisateur> getIdUtilisateurs() {
        return idUtilisateurs;
    }

    public QcmTest idUtilisateurs(Set<Utilisateur> utilisateurs) {
        this.idUtilisateurs = utilisateurs;
        return this;
    }

    public QcmTest addIdUtilisateur(Utilisateur utilisateur) {
        this.idUtilisateurs.add(utilisateur);
        utilisateur.setQcmTest(this);
        return this;
    }

    public QcmTest removeIdUtilisateur(Utilisateur utilisateur) {
        this.idUtilisateurs.remove(utilisateur);
        utilisateur.setQcmTest(null);
        return this;
    }

    public void setIdUtilisateurs(Set<Utilisateur> utilisateurs) {
        this.idUtilisateurs = utilisateurs;
    }

    public QcmTestResponse getQcmTestResponse() {
        return qcmTestResponse;
    }

    public QcmTest qcmTestResponse(QcmTestResponse qcmTestResponse) {
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
        if (!(o instanceof QcmTest)) {
            return false;
        }
        return id != null && id.equals(((QcmTest) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "QcmTest{" +
            "id=" + getId() +
            ", dateDebut='" + getDateDebut() + "'" +
            ", dateFin='" + getDateFin() + "'" +
            ", email='" + getEmail() + "'" +
            ", score=" + getScore() +
            ", highScore=" + getHighScore() +
            "}";
    }
}
