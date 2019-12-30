package com.mycompany.myapp.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Utilisateur.
 */
@Entity
@Table(name = "utilisateur")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Utilisateur implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "email")
    private String email;

    @Column(name = "linkedin")
    private String linkedin;

    @Column(name = "googleplus")
    private String googleplus;

    @Column(name = "role")
    private String role;

    @OneToMany(mappedBy = "utilisateur")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Country> idCountries = new HashSet<>();

    @OneToMany(mappedBy = "utilisateur")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<SocieteAbonne> idSocietes = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("createBies")
    private Qcm qcm;

    @ManyToOne
    @JsonIgnoreProperties("idUtilisateurs")
    private QcmTest qcmTest;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastname() {
        return lastname;
    }

    public Utilisateur lastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public Utilisateur firstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getEmail() {
        return email;
    }

    public Utilisateur email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLinkedin() {
        return linkedin;
    }

    public Utilisateur linkedin(String linkedin) {
        this.linkedin = linkedin;
        return this;
    }

    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }

    public String getGoogleplus() {
        return googleplus;
    }

    public Utilisateur googleplus(String googleplus) {
        this.googleplus = googleplus;
        return this;
    }

    public void setGoogleplus(String googleplus) {
        this.googleplus = googleplus;
    }

    public String getRole() {
        return role;
    }

    public Utilisateur role(String role) {
        this.role = role;
        return this;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Set<Country> getIdCountries() {
        return idCountries;
    }

    public Utilisateur idCountries(Set<Country> countries) {
        this.idCountries = countries;
        return this;
    }

    public Utilisateur addIdCountry(Country country) {
        this.idCountries.add(country);
        country.setUtilisateur(this);
        return this;
    }

    public Utilisateur removeIdCountry(Country country) {
        this.idCountries.remove(country);
        country.setUtilisateur(null);
        return this;
    }

    public void setIdCountries(Set<Country> countries) {
        this.idCountries = countries;
    }

    public Set<SocieteAbonne> getIdSocietes() {
        return idSocietes;
    }

    public Utilisateur idSocietes(Set<SocieteAbonne> societeAbonnes) {
        this.idSocietes = societeAbonnes;
        return this;
    }

    public Utilisateur addIdSociete(SocieteAbonne societeAbonne) {
        this.idSocietes.add(societeAbonne);
        societeAbonne.setUtilisateur(this);
        return this;
    }

    public Utilisateur removeIdSociete(SocieteAbonne societeAbonne) {
        this.idSocietes.remove(societeAbonne);
        societeAbonne.setUtilisateur(null);
        return this;
    }

    public void setIdSocietes(Set<SocieteAbonne> societeAbonnes) {
        this.idSocietes = societeAbonnes;
    }

    public Qcm getQcm() {
        return qcm;
    }

    public Utilisateur qcm(Qcm qcm) {
        this.qcm = qcm;
        return this;
    }

    public void setQcm(Qcm qcm) {
        this.qcm = qcm;
    }

    public QcmTest getQcmTest() {
        return qcmTest;
    }

    public Utilisateur qcmTest(QcmTest qcmTest) {
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
        if (!(o instanceof Utilisateur)) {
            return false;
        }
        return id != null && id.equals(((Utilisateur) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Utilisateur{" +
            "id=" + getId() +
            ", lastname='" + getLastname() + "'" +
            ", firstname='" + getFirstname() + "'" +
            ", email='" + getEmail() + "'" +
            ", linkedin='" + getLinkedin() + "'" +
            ", googleplus='" + getGoogleplus() + "'" +
            ", role='" + getRole() + "'" +
            "}";
    }
}
