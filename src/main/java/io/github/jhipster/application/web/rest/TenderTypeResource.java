package io.github.jhipster.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.application.domain.TenderType;
import io.github.jhipster.application.repository.TenderTypeRepository;
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
 * REST controller for managing TenderType.
 */
@RestController
@RequestMapping("/api")
public class TenderTypeResource {

    private final Logger log = LoggerFactory.getLogger(TenderTypeResource.class);

    private static final String ENTITY_NAME = "tenderType";

    private final TenderTypeRepository tenderTypeRepository;

    public TenderTypeResource(TenderTypeRepository tenderTypeRepository) {
        this.tenderTypeRepository = tenderTypeRepository;
    }

    /**
     * POST  /tender-types : Create a new tenderType.
     *
     * @param tenderType the tenderType to create
     * @return the ResponseEntity with status 201 (Created) and with body the new tenderType, or with status 400 (Bad Request) if the tenderType has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/tender-types")
    @Timed
    public ResponseEntity<TenderType> createTenderType(@RequestBody TenderType tenderType) throws URISyntaxException {
        log.debug("REST request to save TenderType : {}", tenderType);
        if (tenderType.getId() != null) {
            throw new BadRequestAlertException("A new tenderType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TenderType result = tenderTypeRepository.save(tenderType);
        return ResponseEntity.created(new URI("/api/tender-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /tender-types : Updates an existing tenderType.
     *
     * @param tenderType the tenderType to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated tenderType,
     * or with status 400 (Bad Request) if the tenderType is not valid,
     * or with status 500 (Internal Server Error) if the tenderType couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/tender-types")
    @Timed
    public ResponseEntity<TenderType> updateTenderType(@RequestBody TenderType tenderType) throws URISyntaxException {
        log.debug("REST request to update TenderType : {}", tenderType);
        if (tenderType.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TenderType result = tenderTypeRepository.save(tenderType);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, tenderType.getId().toString()))
            .body(result);
    }

    /**
     * GET  /tender-types : get all the tenderTypes.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of tenderTypes in body
     */
    @GetMapping("/tender-types")
    @Timed
    public List<TenderType> getAllTenderTypes() {
        log.debug("REST request to get all TenderTypes");
        return tenderTypeRepository.findAll();
    }

    /**
     * GET  /tender-types/:id : get the "id" tenderType.
     *
     * @param id the id of the tenderType to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the tenderType, or with status 404 (Not Found)
     */
    @GetMapping("/tender-types/{id}")
    @Timed
    public ResponseEntity<TenderType> getTenderType(@PathVariable Long id) {
        log.debug("REST request to get TenderType : {}", id);
        Optional<TenderType> tenderType = tenderTypeRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(tenderType);
    }

    /**
     * DELETE  /tender-types/:id : delete the "id" tenderType.
     *
     * @param id the id of the tenderType to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/tender-types/{id}")
    @Timed
    public ResponseEntity<Void> deleteTenderType(@PathVariable Long id) {
        log.debug("REST request to delete TenderType : {}", id);

        tenderTypeRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
