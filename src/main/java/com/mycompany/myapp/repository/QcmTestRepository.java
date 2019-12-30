package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.QcmTest;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the QcmTest entity.
 */
@SuppressWarnings("unused")
@Repository
public interface QcmTestRepository extends JpaRepository<QcmTest, Long> {

}
