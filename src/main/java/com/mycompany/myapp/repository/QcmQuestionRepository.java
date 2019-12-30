package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.QcmQuestion;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the QcmQuestion entity.
 */
@SuppressWarnings("unused")
@Repository
public interface QcmQuestionRepository extends JpaRepository<QcmQuestion, Long> {

}
