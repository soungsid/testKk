package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.QcmReponse;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the QcmReponse entity.
 */
@SuppressWarnings("unused")
@Repository
public interface QcmReponseRepository extends JpaRepository<QcmReponse, Long> {

}
