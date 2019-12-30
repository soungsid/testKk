package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Qcm;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Qcm entity.
 */
@SuppressWarnings("unused")
@Repository
public interface QcmRepository extends JpaRepository<Qcm, Long> {

}
