package io.github.jhipster.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.application.domain.ItemCategoryType;
import io.github.jhipster.application.repository.ItemCategoryTypeRepository;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing ItemCategoryType.
 */
@RestController
@RequestMapping("/api")
public class ItemCategoryTypeResource {

    private final Logger log = LoggerFactory.getLogger(ItemCategoryTypeResource.class);

    private static final String ENTITY_NAME = "itemCategoryType";

    private final ItemCategoryTypeRepository itemCategoryTypeRepository;

    public ItemCategoryTypeResource(ItemCategoryTypeRepository itemCategoryTypeRepository) {
        this.itemCategoryTypeRepository = itemCategoryTypeRepository;
    }

    /**
     * POST  /item-category-types : Create a new itemCategoryType.
     *
     * @param itemCategoryType the itemCategoryType to create
     * @return the ResponseEntity with status 201 (Created) and with body the new itemCategoryType, or with status 400 (Bad Request) if the itemCategoryType has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/item-category-types")
    @Timed
    public ResponseEntity<ItemCategoryType> createItemCategoryType(@RequestBody ItemCategoryType itemCategoryType) throws URISyntaxException {
        log.debug("REST request to save ItemCategoryType : {}", itemCategoryType);
        if (itemCategoryType.getId() != null) {
            throw new BadRequestAlertException("A new itemCategoryType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ItemCategoryType result = itemCategoryTypeRepository.save(itemCategoryType);
        return ResponseEntity.created(new URI("/api/item-category-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /item-category-types : Updates an existing itemCategoryType.
     *
     * @param itemCategoryType the itemCategoryType to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated itemCategoryType,
     * or with status 400 (Bad Request) if the itemCategoryType is not valid,
     * or with status 500 (Internal Server Error) if the itemCategoryType couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/item-category-types")
    @Timed
    public ResponseEntity<ItemCategoryType> updateItemCategoryType(@RequestBody ItemCategoryType itemCategoryType) throws URISyntaxException {
        log.debug("REST request to update ItemCategoryType : {}", itemCategoryType);
        if (itemCategoryType.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ItemCategoryType result = itemCategoryTypeRepository.save(itemCategoryType);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, itemCategoryType.getId().toString()))
            .body(result);
    }

    /**
     * GET  /item-category-types : get all the itemCategoryTypes.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of itemCategoryTypes in body
     */
    @GetMapping("/item-category-types")
    @Timed
    public List<ItemCategoryType> getAllItemCategoryTypes() {
        log.debug("REST request to get all ItemCategoryTypes");
        return itemCategoryTypeRepository.findAll();
    }

    /**
     * GET  /item-category-types/:id : get the "id" itemCategoryType.
     *
     * @param id the id of the itemCategoryType to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the itemCategoryType, or with status 404 (Not Found)
     */
    @GetMapping("/item-category-types/{id}")
    @Timed
    public ResponseEntity<ItemCategoryType> getItemCategoryType(@PathVariable Long id) {
        log.debug("REST request to get ItemCategoryType : {}", id);
        Optional<ItemCategoryType> itemCategoryType = itemCategoryTypeRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(itemCategoryType);
    }

    /**
     * DELETE  /item-category-types/:id : delete the "id" itemCategoryType.
     *
     * @param id the id of the itemCategoryType to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/item-category-types/{id}")
    @Timed
    public ResponseEntity<Void> deleteItemCategoryType(@PathVariable Long id) {
        log.debug("REST request to delete ItemCategoryType : {}", id);

        itemCategoryTypeRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
