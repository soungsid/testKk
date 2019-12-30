package com.mycompany.myapp.service.dto;
import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.SocieteAbonne} entity.
 */
public class SocieteAbonneDTO implements Serializable {

    private Long id;

    @NotNull
    private String name;

    private Instant dateAbonnement;

    @Lob
    private byte[] logo;

    private String logoContentType;

    private Long utilisateurId;

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

    public Instant getDateAbonnement() {
        return dateAbonnement;
    }

    public void setDateAbonnement(Instant dateAbonnement) {
        this.dateAbonnement = dateAbonnement;
    }

    public byte[] getLogo() {
        return logo;
    }

    public void setLogo(byte[] logo) {
        this.logo = logo;
    }

    public String getLogoContentType() {
        return logoContentType;
    }

    public void setLogoContentType(String logoContentType) {
        this.logoContentType = logoContentType;
    }

    public Long getUtilisateurId() {
        return utilisateurId;
    }

    public void setUtilisateurId(Long utilisateurId) {
        this.utilisateurId = utilisateurId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SocieteAbonneDTO societeAbonneDTO = (SocieteAbonneDTO) o;
        if (societeAbonneDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), societeAbonneDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SocieteAbonneDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", dateAbonnement='" + getDateAbonnement() + "'" +
            ", logo='" + getLogo() + "'" +
            ", utilisateurId=" + getUtilisateurId() +
            "}";
    }
}
