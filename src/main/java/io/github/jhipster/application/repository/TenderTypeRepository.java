package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.TenderType;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the TenderType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TenderTypeRepository extends JpaRepository<TenderType, Long> {

}
