package io.github.jhipster.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.application.domain.ItemCategory;
import io.github.jhipster.application.repository.ItemCategoryRepository;
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
 * REST controller for managing ItemCategory.
 */
@RestController
@RequestMapping("/api")
public class ItemCategoryResource {

    private final Logger log = LoggerFactory.getLogger(ItemCategoryResource.class);

    private static final String ENTITY_NAME = "itemCategory";

    private final ItemCategoryRepository itemCategoryRepository;

    public ItemCategoryResource(ItemCategoryRepository itemCategoryRepository) {
        this.itemCategoryRepository = itemCategoryRepository;
    }

    /**
     * POST  /item-categories : Create a new itemCategory.
     *
     * @param itemCategory the itemCategory to create
     * @return the ResponseEntity with status 201 (Created) and with body the new itemCategory, or with status 400 (Bad Request) if the itemCategory has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/item-categories")
    @Timed
    public ResponseEntity<ItemCategory> createItemCategory(@RequestBody ItemCategory itemCategory) throws URISyntaxException {
        log.debug("REST request to save ItemCategory : {}", itemCategory);
        if (itemCategory.getId() != null) {
            throw new BadRequestAlertException("A new itemCategory cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ItemCategory result = itemCategoryRepository.save(itemCategory);
        return ResponseEntity.created(new URI("/api/item-categories/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /item-categories : Updates an existing itemCategory.
     *
     * @param itemCategory the itemCategory to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated itemCategory,
     * or with status 400 (Bad Request) if the itemCategory is not valid,
     * or with status 500 (Internal Server Error) if the itemCategory couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/item-categories")
    @Timed
    public ResponseEntity<ItemCategory> updateItemCategory(@RequestBody ItemCategory itemCategory) throws URISyntaxException {
        log.debug("REST request to update ItemCategory : {}", itemCategory);
        if (itemCategory.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ItemCategory result = itemCategoryRepository.save(itemCategory);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, itemCategory.getId().toString()))
            .body(result);
    }

    /**
     * GET  /item-categories : get all the itemCategories.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of itemCategories in body
     */
    @GetMapping("/item-categories")
    @Timed
    public List<ItemCategory> getAllItemCategories() {
        log.debug("REST request to get all ItemCategories");
        return itemCategoryRepository.findAll();
    }

    /**
     * GET  /item-categories/:id : get the "id" itemCategory.
     *
     * @param id the id of the itemCategory to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the itemCategory, or with status 404 (Not Found)
     */
    @GetMapping("/item-categories/{id}")
    @Timed
    public ResponseEntity<ItemCategory> getItemCategory(@PathVariable Long id) {
        log.debug("REST request to get ItemCategory : {}", id);
        Optional<ItemCategory> itemCategory = itemCategoryRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(itemCategory);
    }

    /**
     * DELETE  /item-categories/:id : delete the "id" itemCategory.
     *
     * @param id the id of the itemCategory to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/item-categories/{id}")
    @Timed
    public ResponseEntity<Void> deleteItemCategory(@PathVariable Long id) {
        log.debug("REST request to delete ItemCategory : {}", id);

        itemCategoryRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
