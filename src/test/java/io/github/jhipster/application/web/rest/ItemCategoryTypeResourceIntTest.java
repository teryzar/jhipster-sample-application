package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;

import io.github.jhipster.application.domain.ItemCategoryType;
import io.github.jhipster.application.repository.ItemCategoryTypeRepository;
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
 * Test class for the ItemCategoryTypeResource REST controller.
 *
 * @see ItemCategoryTypeResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class ItemCategoryTypeResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private ItemCategoryTypeRepository itemCategoryTypeRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restItemCategoryTypeMockMvc;

    private ItemCategoryType itemCategoryType;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ItemCategoryTypeResource itemCategoryTypeResource = new ItemCategoryTypeResource(itemCategoryTypeRepository);
        this.restItemCategoryTypeMockMvc = MockMvcBuilders.standaloneSetup(itemCategoryTypeResource)
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
    public static ItemCategoryType createEntity(EntityManager em) {
        ItemCategoryType itemCategoryType = new ItemCategoryType()
            .name(DEFAULT_NAME)
            .description(DEFAULT_DESCRIPTION);
        return itemCategoryType;
    }

    @Before
    public void initTest() {
        itemCategoryType = createEntity(em);
    }

    @Test
    @Transactional
    public void createItemCategoryType() throws Exception {
        int databaseSizeBeforeCreate = itemCategoryTypeRepository.findAll().size();

        // Create the ItemCategoryType
        restItemCategoryTypeMockMvc.perform(post("/api/item-category-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemCategoryType)))
            .andExpect(status().isCreated());

        // Validate the ItemCategoryType in the database
        List<ItemCategoryType> itemCategoryTypeList = itemCategoryTypeRepository.findAll();
        assertThat(itemCategoryTypeList).hasSize(databaseSizeBeforeCreate + 1);
        ItemCategoryType testItemCategoryType = itemCategoryTypeList.get(itemCategoryTypeList.size() - 1);
        assertThat(testItemCategoryType.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testItemCategoryType.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createItemCategoryTypeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = itemCategoryTypeRepository.findAll().size();

        // Create the ItemCategoryType with an existing ID
        itemCategoryType.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restItemCategoryTypeMockMvc.perform(post("/api/item-category-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemCategoryType)))
            .andExpect(status().isBadRequest());

        // Validate the ItemCategoryType in the database
        List<ItemCategoryType> itemCategoryTypeList = itemCategoryTypeRepository.findAll();
        assertThat(itemCategoryTypeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllItemCategoryTypes() throws Exception {
        // Initialize the database
        itemCategoryTypeRepository.saveAndFlush(itemCategoryType);

        // Get all the itemCategoryTypeList
        restItemCategoryTypeMockMvc.perform(get("/api/item-category-types?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(itemCategoryType.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())));
    }
    
    @Test
    @Transactional
    public void getItemCategoryType() throws Exception {
        // Initialize the database
        itemCategoryTypeRepository.saveAndFlush(itemCategoryType);

        // Get the itemCategoryType
        restItemCategoryTypeMockMvc.perform(get("/api/item-category-types/{id}", itemCategoryType.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(itemCategoryType.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingItemCategoryType() throws Exception {
        // Get the itemCategoryType
        restItemCategoryTypeMockMvc.perform(get("/api/item-category-types/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateItemCategoryType() throws Exception {
        // Initialize the database
        itemCategoryTypeRepository.saveAndFlush(itemCategoryType);

        int databaseSizeBeforeUpdate = itemCategoryTypeRepository.findAll().size();

        // Update the itemCategoryType
        ItemCategoryType updatedItemCategoryType = itemCategoryTypeRepository.findById(itemCategoryType.getId()).get();
        // Disconnect from session so that the updates on updatedItemCategoryType are not directly saved in db
        em.detach(updatedItemCategoryType);
        updatedItemCategoryType
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION);

        restItemCategoryTypeMockMvc.perform(put("/api/item-category-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedItemCategoryType)))
            .andExpect(status().isOk());

        // Validate the ItemCategoryType in the database
        List<ItemCategoryType> itemCategoryTypeList = itemCategoryTypeRepository.findAll();
        assertThat(itemCategoryTypeList).hasSize(databaseSizeBeforeUpdate);
        ItemCategoryType testItemCategoryType = itemCategoryTypeList.get(itemCategoryTypeList.size() - 1);
        assertThat(testItemCategoryType.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testItemCategoryType.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingItemCategoryType() throws Exception {
        int databaseSizeBeforeUpdate = itemCategoryTypeRepository.findAll().size();

        // Create the ItemCategoryType

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restItemCategoryTypeMockMvc.perform(put("/api/item-category-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemCategoryType)))
            .andExpect(status().isBadRequest());

        // Validate the ItemCategoryType in the database
        List<ItemCategoryType> itemCategoryTypeList = itemCategoryTypeRepository.findAll();
        assertThat(itemCategoryTypeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteItemCategoryType() throws Exception {
        // Initialize the database
        itemCategoryTypeRepository.saveAndFlush(itemCategoryType);

        int databaseSizeBeforeDelete = itemCategoryTypeRepository.findAll().size();

        // Get the itemCategoryType
        restItemCategoryTypeMockMvc.perform(delete("/api/item-category-types/{id}", itemCategoryType.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ItemCategoryType> itemCategoryTypeList = itemCategoryTypeRepository.findAll();
        assertThat(itemCategoryTypeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ItemCategoryType.class);
        ItemCategoryType itemCategoryType1 = new ItemCategoryType();
        itemCategoryType1.setId(1L);
        ItemCategoryType itemCategoryType2 = new ItemCategoryType();
        itemCategoryType2.setId(itemCategoryType1.getId());
        assertThat(itemCategoryType1).isEqualTo(itemCategoryType2);
        itemCategoryType2.setId(2L);
        assertThat(itemCategoryType1).isNotEqualTo(itemCategoryType2);
        itemCategoryType1.setId(null);
        assertThat(itemCategoryType1).isNotEqualTo(itemCategoryType2);
    }
}
