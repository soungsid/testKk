package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.QcmQuestionTag;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the QcmQuestionTag entity.
 */
@SuppressWarnings("unused")
@Repository
public interface QcmQuestionTagRepository extends JpaRepository<QcmQuestionTag, Long> {

}
