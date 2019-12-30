package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.CarnetAdresse;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the CarnetAdresse entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CarnetAdresseRepository extends JpaRepository<CarnetAdresse, Long> {

}
