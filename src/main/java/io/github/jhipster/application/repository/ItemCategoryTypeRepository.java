package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.ItemCategoryType;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ItemCategoryType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ItemCategoryTypeRepository extends JpaRepository<ItemCategoryType, Long> {

}
