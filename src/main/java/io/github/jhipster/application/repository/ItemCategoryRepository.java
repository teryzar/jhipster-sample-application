package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.ItemCategory;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ItemCategory entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ItemCategoryRepository extends JpaRepository<ItemCategory, Long> {

}
