package com.mycompany.myapp.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.CarnetAdresse} entity.
 */
public class CarnetAdresseDTO implements Serializable {

    private Long id;

    private String lastname;

    private String firstname;

    private String email;


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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CarnetAdresseDTO carnetAdresseDTO = (CarnetAdresseDTO) o;
        if (carnetAdresseDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), carnetAdresseDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CarnetAdresseDTO{" +
            "id=" + getId() +
            ", lastname='" + getLastname() + "'" +
            ", firstname='" + getFirstname() + "'" +
            ", email='" + getEmail() + "'" +
            "}";
    }
}
