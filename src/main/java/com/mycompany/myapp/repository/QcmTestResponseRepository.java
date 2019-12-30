package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.QcmTestResponse;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the QcmTestResponse entity.
 */
@SuppressWarnings("unused")
@Repository
public interface QcmTestResponseRepository extends JpaRepository<QcmTestResponse, Long> {

}
