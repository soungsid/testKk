package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.SocieteAbonne;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SocieteAbonne entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SocieteAbonneRepository extends JpaRepository<SocieteAbonne, Long> {

}
