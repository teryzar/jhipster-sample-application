package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;

import io.github.jhipster.application.domain.TenderType;
import io.github.jhipster.application.repository.TenderTypeRepository;
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
 * Test class for the TenderTypeResource REST controller.
 *
 * @see TenderTypeResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class TenderTypeResourceIntTest {

    @Autowired
    private TenderTypeRepository tenderTypeRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restTenderTypeMockMvc;

    private TenderType tenderType;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TenderTypeResource tenderTypeResource = new TenderTypeResource(tenderTypeRepository);
        this.restTenderTypeMockMvc = MockMvcBuilders.standaloneSetup(tenderTypeResource)
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
    public static TenderType createEntity(EntityManager em) {
        TenderType tenderType = new TenderType();
        return tenderType;
    }

    @Before
    public void initTest() {
        tenderType = createEntity(em);
    }

    @Test
    @Transactional
    public void createTenderType() throws Exception {
        int databaseSizeBeforeCreate = tenderTypeRepository.findAll().size();

        // Create the TenderType
        restTenderTypeMockMvc.perform(post("/api/tender-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tenderType)))
            .andExpect(status().isCreated());

        // Validate the TenderType in the database
        List<TenderType> tenderTypeList = tenderTypeRepository.findAll();
        assertThat(tenderTypeList).hasSize(databaseSizeBeforeCreate + 1);
        TenderType testTenderType = tenderTypeList.get(tenderTypeList.size() - 1);
    }

    @Test
    @Transactional
    public void createTenderTypeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tenderTypeRepository.findAll().size();

        // Create the TenderType with an existing ID
        tenderType.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTenderTypeMockMvc.perform(post("/api/tender-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tenderType)))
            .andExpect(status().isBadRequest());

        // Validate the TenderType in the database
        List<TenderType> tenderTypeList = tenderTypeRepository.findAll();
        assertThat(tenderTypeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllTenderTypes() throws Exception {
        // Initialize the database
        tenderTypeRepository.saveAndFlush(tenderType);

        // Get all the tenderTypeList
        restTenderTypeMockMvc.perform(get("/api/tender-types?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tenderType.getId().intValue())));
    }
    
    @Test
    @Transactional
    public void getTenderType() throws Exception {
        // Initialize the database
        tenderTypeRepository.saveAndFlush(tenderType);

        // Get the tenderType
        restTenderTypeMockMvc.perform(get("/api/tender-types/{id}", tenderType.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(tenderType.getId().intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingTenderType() throws Exception {
        // Get the tenderType
        restTenderTypeMockMvc.perform(get("/api/tender-types/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTenderType() throws Exception {
        // Initialize the database
        tenderTypeRepository.saveAndFlush(tenderType);

        int databaseSizeBeforeUpdate = tenderTypeRepository.findAll().size();

        // Update the tenderType
        TenderType updatedTenderType = tenderTypeRepository.findById(tenderType.getId()).get();
        // Disconnect from session so that the updates on updatedTenderType are not directly saved in db
        em.detach(updatedTenderType);

        restTenderTypeMockMvc.perform(put("/api/tender-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedTenderType)))
            .andExpect(status().isOk());

        // Validate the TenderType in the database
        List<TenderType> tenderTypeList = tenderTypeRepository.findAll();
        assertThat(tenderTypeList).hasSize(databaseSizeBeforeUpdate);
        TenderType testTenderType = tenderTypeList.get(tenderTypeList.size() - 1);
    }

    @Test
    @Transactional
    public void updateNonExistingTenderType() throws Exception {
        int databaseSizeBeforeUpdate = tenderTypeRepository.findAll().size();

        // Create the TenderType

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTenderTypeMockMvc.perform(put("/api/tender-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tenderType)))
            .andExpect(status().isBadRequest());

        // Validate the TenderType in the database
        List<TenderType> tenderTypeList = tenderTypeRepository.findAll();
        assertThat(tenderTypeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTenderType() throws Exception {
        // Initialize the database
        tenderTypeRepository.saveAndFlush(tenderType);

        int databaseSizeBeforeDelete = tenderTypeRepository.findAll().size();

        // Get the tenderType
        restTenderTypeMockMvc.perform(delete("/api/tender-types/{id}", tenderType.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<TenderType> tenderTypeList = tenderTypeRepository.findAll();
        assertThat(tenderTypeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TenderType.class);
        TenderType tenderType1 = new TenderType();
        tenderType1.setId(1L);
        TenderType tenderType2 = new TenderType();
        tenderType2.setId(tenderType1.getId());
        assertThat(tenderType1).isEqualTo(tenderType2);
        tenderType2.setId(2L);
        assertThat(tenderType1).isNotEqualTo(tenderType2);
        tenderType1.setId(null);
        assertThat(tenderType1).isNotEqualTo(tenderType2);
    }
}
