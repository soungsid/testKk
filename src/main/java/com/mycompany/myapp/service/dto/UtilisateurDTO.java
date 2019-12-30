package com.mycompany.myapp.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.Utilisateur} entity.
 */
public class UtilisateurDTO implements Serializable {

    private Long id;

    private String lastname;

    private String firstname;

    private String email;

    private String linkedin;

    private String googleplus;

    private String role;


    private Long qcmId;

    private Long qcmTestId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLinkedin() {
        return linkedin;
    }

    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }

    public String getGoogleplus() {
        return googleplus;
    }

    public void setGoogleplus(String googleplus) {
        this.googleplus = googleplus;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Long getQcmId() {
        return qcmId;
    }

    public void setQcmId(Long qcmId) {
        this.qcmId = qcmId;
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

        UtilisateurDTO utilisateurDTO = (UtilisateurDTO) o;
        if (utilisateurDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), utilisateurDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "UtilisateurDTO{" +
            "id=" + getId() +
            ", lastname='" + getLastname() + "'" +
            ", firstname='" + getFirstname() + "'" +
            ", email='" + getEmail() + "'" +
            ", linkedin='" + getLinkedin() + "'" +
            ", googleplus='" + getGoogleplus() + "'" +
            ", role='" + getRole() + "'" +
            ", qcmId=" + getQcmId() +
            ", qcmTestId=" + getQcmTestId() +
            "}";
    }
}
