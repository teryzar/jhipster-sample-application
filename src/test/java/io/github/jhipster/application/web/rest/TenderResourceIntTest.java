package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;

import io.github.jhipster.application.domain.Tender;
import io.github.jhipster.application.repository.TenderRepository;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;


import static io.github.jhipster.application.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the TenderResource REST controller.
 *
 * @see TenderResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class TenderResourceIntTest {

    @Autowired
    private TenderRepository tenderRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restTenderMockMvc;

    private Tender tender;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TenderResource tenderResource = new TenderResource(tenderRepository);
        this.restTenderMockMvc = MockMvcBuilders.standaloneSetup(tenderResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Tender createEntity(EntityManager em) {
        Tender tender = new Tender();
        return tender;
    }

    @Before
    public void initTest() {
        tender = createEntity(em);
    }

    @Test
    @Transactional
    public void createTender() throws Exception {
        int databaseSizeBeforeCreate = tenderRepository.findAll().size();

        // Create the Tender
        restTenderMockMvc.perform(post("/api/tenders")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tender)))
            .andExpect(status().isCreated());

        // Validate the Tender in the database
        List<Tender> tenderList = tenderRepository.findAll();
        assertThat(tenderList).hasSize(databaseSizeBeforeCreate + 1);
        Tender testTender = tenderList.get(tenderList.size() - 1);
    }

    @Test
    @Transactional
    public void createTenderWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tenderRepository.findAll().size();

        // Create the Tender with an existing ID
        tender.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTenderMockMvc.perform(post("/api/tenders")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tender)))
            .andExpect(status().isBadRequest());

        // Validate the Tender in the database
        List<Tender> tenderList = tenderRepository.findAll();
        assertThat(tenderList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllTenders() throws Exception {
        // Initialize the database
        tenderRepository.saveAndFlush(tender);

        // Get all the tenderList
        restTenderMockMvc.perform(get("/api/tenders?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tender.getId().intValue())));
    }
    
    @Test
    @Transactional
    public void getTender() throws Exception {
        // Initialize the database
        tenderRepository.saveAndFlush(tender);

        // Get the tender
        restTenderMockMvc.perform(get("/api/tenders/{id}", tender.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(tender.getId().intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingTender() throws Exception {
        // Get the tender
        restTenderMockMvc.perform(get("/api/tenders/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTender() throws Exception {
        // Initialize the database
        tenderRepository.saveAndFlush(tender);

        int databaseSizeBeforeUpdate = tenderRepository.findAll().size();

        // Update the tender
        Tender updatedTender = tenderRepository.findById(tender.getId()).get();
        // Disconnect from session so that the updates on updatedTender are not directly saved in db
        em.detach(updatedTender);

        restTenderMockMvc.perform(put("/api/tenders")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedTender)))
            .andExpect(status().isOk());

        // Validate the Tender in the database
        List<Tender> tenderList = tenderRepository.findAll();
        assertThat(tenderList).hasSize(databaseSizeBeforeUpdate);
        Tender testTender = tenderList.get(tenderList.size() - 1);
    }

    @Test
    @Transactional
    public void updateNonExistingTender() throws Exception {
        int databaseSizeBeforeUpdate = tenderRepository.findAll().size();

        // Create the Tender

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTenderMockMvc.perform(put("/api/tenders")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tender)))
            .andExpect(status().isBadRequest());

        // Validate the Tender in the database
        List<Tender> tenderList = tenderRepository.findAll();
        assertThat(tenderList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTender() throws Exception {
        // Initialize the database
        tenderRepository.saveAndFlush(tender);

        int databaseSizeBeforeDelete = tenderRepository.findAll().size();

        // Get the tender
        restTenderMockMvc.perform(delete("/api/tenders/{id}", tender.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Tender> tenderList = tenderRepository.findAll();
        assertThat(tenderList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Tender.class);
        Tender tender1 = new Tender();
        tender1.setId(1L);
        Tender tender2 = new Tender();
        tender2.setId(tender1.getId());
        assertThat(tender1).isEqualTo(tender2);
        tender2.setId(2L);
        assertThat(tender1).isNotEqualTo(tender2);
        tender1.setId(null);
        assertThat(tender1).isNotEqualTo(tender2);
    }
}
