package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;

import io.github.jhipster.application.domain.Item;
import io.github.jhipster.application.repository.ItemRepository;
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
import java.math.BigDecimal;
import java.util.List;


import static io.github.jhipster.application.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the ItemResource REST controller.
 *
 * @see ItemResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class ItemResourceIntTest {

    private static final Long DEFAULT_ARTICLE_NR = 1L;
    private static final Long UPDATED_ARTICLE_NR = 2L;

    private static final String DEFAULT_ARTICLE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ARTICLE_NAME = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_PRIME_COST = new BigDecimal(1);
    private static final BigDecimal UPDATED_PRIME_COST = new BigDecimal(2);

    private static final BigDecimal DEFAULT_PRICE = new BigDecimal(1);
    private static final BigDecimal UPDATED_PRICE = new BigDecimal(2);

    private static final Boolean DEFAULT_AVAILABLE_FOR_SALE = false;
    private static final Boolean UPDATED_AVAILABLE_FOR_SALE = true;

    private static final Boolean DEFAULT_SCALE_ITEM = false;
    private static final Boolean UPDATED_SCALE_ITEM = true;

    private static final String DEFAULT_EAN = "AAAAAAAAAA";
    private static final String UPDATED_EAN = "BBBBBBBBBB";

    private static final Boolean DEFAULT_ACCOUNT_REST = false;
    private static final Boolean UPDATED_ACCOUNT_REST = true;

    private static final BigDecimal DEFAULT_REST = new BigDecimal(1);
    private static final BigDecimal UPDATED_REST = new BigDecimal(2);

    private static final BigDecimal DEFAULT_LOW_REST = new BigDecimal(1);
    private static final BigDecimal UPDATED_LOW_REST = new BigDecimal(2);

    private static final Long DEFAULT_PARENT_ITEM = 1L;
    private static final Long UPDATED_PARENT_ITEM = 2L;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restItemMockMvc;

    private Item item;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ItemResource itemResource = new ItemResource(itemRepository);
        this.restItemMockMvc = MockMvcBuilders.standaloneSetup(itemResource)
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
    public static Item createEntity(EntityManager em) {
        Item item = new Item()
            .articleNr(DEFAULT_ARTICLE_NR)
            .articleName(DEFAULT_ARTICLE_NAME)
            .primeCost(DEFAULT_PRIME_COST)
            .price(DEFAULT_PRICE)
            .availableForSale(DEFAULT_AVAILABLE_FOR_SALE)
            .scaleItem(DEFAULT_SCALE_ITEM)
            .ean(DEFAULT_EAN)
            .accountRest(DEFAULT_ACCOUNT_REST)
            .rest(DEFAULT_REST)
            .lowRest(DEFAULT_LOW_REST)
            .parentItem(DEFAULT_PARENT_ITEM);
        return item;
    }

    @Before
    public void initTest() {
        item = createEntity(em);
    }

    @Test
    @Transactional
    public void createItem() throws Exception {
        int databaseSizeBeforeCreate = itemRepository.findAll().size();

        // Create the Item
        restItemMockMvc.perform(post("/api/items")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(item)))
            .andExpect(status().isCreated());

        // Validate the Item in the database
        List<Item> itemList = itemRepository.findAll();
        assertThat(itemList).hasSize(databaseSizeBeforeCreate + 1);
        Item testItem = itemList.get(itemList.size() - 1);
        assertThat(testItem.getArticleNr()).isEqualTo(DEFAULT_ARTICLE_NR);
        assertThat(testItem.getArticleName()).isEqualTo(DEFAULT_ARTICLE_NAME);
        assertThat(testItem.getPrimeCost()).isEqualTo(DEFAULT_PRIME_COST);
        assertThat(testItem.getPrice()).isEqualTo(DEFAULT_PRICE);
        assertThat(testItem.isAvailableForSale()).isEqualTo(DEFAULT_AVAILABLE_FOR_SALE);
        assertThat(testItem.isScaleItem()).isEqualTo(DEFAULT_SCALE_ITEM);
        assertThat(testItem.getEan()).isEqualTo(DEFAULT_EAN);
        assertThat(testItem.isAccountRest()).isEqualTo(DEFAULT_ACCOUNT_REST);
        assertThat(testItem.getRest()).isEqualTo(DEFAULT_REST);
        assertThat(testItem.getLowRest()).isEqualTo(DEFAULT_LOW_REST);
        assertThat(testItem.getParentItem()).isEqualTo(DEFAULT_PARENT_ITEM);
    }

    @Test
    @Transactional
    public void createItemWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = itemRepository.findAll().size();

        // Create the Item with an existing ID
        item.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restItemMockMvc.perform(post("/api/items")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(item)))
            .andExpect(status().isBadRequest());

        // Validate the Item in the database
        List<Item> itemList = itemRepository.findAll();
        assertThat(itemList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllItems() throws Exception {
        // Initialize the database
        itemRepository.saveAndFlush(item);

        // Get all the itemList
        restItemMockMvc.perform(get("/api/items?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(item.getId().intValue())))
            .andExpect(jsonPath("$.[*].articleNr").value(hasItem(DEFAULT_ARTICLE_NR.intValue())))
            .andExpect(jsonPath("$.[*].articleName").value(hasItem(DEFAULT_ARTICLE_NAME.toString())))
            .andExpect(jsonPath("$.[*].primeCost").value(hasItem(DEFAULT_PRIME_COST.intValue())))
            .andExpect(jsonPath("$.[*].price").value(hasItem(DEFAULT_PRICE.intValue())))
            .andExpect(jsonPath("$.[*].availableForSale").value(hasItem(DEFAULT_AVAILABLE_FOR_SALE.booleanValue())))
            .andExpect(jsonPath("$.[*].scaleItem").value(hasItem(DEFAULT_SCALE_ITEM.booleanValue())))
            .andExpect(jsonPath("$.[*].ean").value(hasItem(DEFAULT_EAN.toString())))
            .andExpect(jsonPath("$.[*].accountRest").value(hasItem(DEFAULT_ACCOUNT_REST.booleanValue())))
            .andExpect(jsonPath("$.[*].rest").value(hasItem(DEFAULT_REST.intValue())))
            .andExpect(jsonPath("$.[*].lowRest").value(hasItem(DEFAULT_LOW_REST.intValue())))
            .andExpect(jsonPath("$.[*].parentItem").value(hasItem(DEFAULT_PARENT_ITEM.intValue())));
    }
    
    @Test
    @Transactional
    public void getItem() throws Exception {
        // Initialize the database
        itemRepository.saveAndFlush(item);

        // Get the item
        restItemMockMvc.perform(get("/api/items/{id}", item.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(item.getId().intValue()))
            .andExpect(jsonPath("$.articleNr").value(DEFAULT_ARTICLE_NR.intValue()))
            .andExpect(jsonPath("$.articleName").value(DEFAULT_ARTICLE_NAME.toString()))
            .andExpect(jsonPath("$.primeCost").value(DEFAULT_PRIME_COST.intValue()))
            .andExpect(jsonPath("$.price").value(DEFAULT_PRICE.intValue()))
            .andExpect(jsonPath("$.availableForSale").value(DEFAULT_AVAILABLE_FOR_SALE.booleanValue()))
            .andExpect(jsonPath("$.scaleItem").value(DEFAULT_SCALE_ITEM.booleanValue()))
            .andExpect(jsonPath("$.ean").value(DEFAULT_EAN.toString()))
            .andExpect(jsonPath("$.accountRest").value(DEFAULT_ACCOUNT_REST.booleanValue()))
            .andExpect(jsonPath("$.rest").value(DEFAULT_REST.intValue()))
            .andExpect(jsonPath("$.lowRest").value(DEFAULT_LOW_REST.intValue()))
            .andExpect(jsonPath("$.parentItem").value(DEFAULT_PARENT_ITEM.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingItem() throws Exception {
        // Get the item
        restItemMockMvc.perform(get("/api/items/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateItem() throws Exception {
        // Initialize the database
        itemRepository.saveAndFlush(item);

        int databaseSizeBeforeUpdate = itemRepository.findAll().size();

        // Update the item
        Item updatedItem = itemRepository.findById(item.getId()).get();
        // Disconnect from session so that the updates on updatedItem are not directly saved in db
        em.detach(updatedItem);
        updatedItem
            .articleNr(UPDATED_ARTICLE_NR)
            .articleName(UPDATED_ARTICLE_NAME)
            .primeCost(UPDATED_PRIME_COST)
            .price(UPDATED_PRICE)
            .availableForSale(UPDATED_AVAILABLE_FOR_SALE)
            .scaleItem(UPDATED_SCALE_ITEM)
            .ean(UPDATED_EAN)
            .accountRest(UPDATED_ACCOUNT_REST)
            .rest(UPDATED_REST)
            .lowRest(UPDATED_LOW_REST)
            .parentItem(UPDATED_PARENT_ITEM);

        restItemMockMvc.perform(put("/api/items")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedItem)))
            .andExpect(status().isOk());

        // Validate the Item in the database
        List<Item> itemList = itemRepository.findAll();
        assertThat(itemList).hasSize(databaseSizeBeforeUpdate);
        Item testItem = itemList.get(itemList.size() - 1);
        assertThat(testItem.getArticleNr()).isEqualTo(UPDATED_ARTICLE_NR);
        assertThat(testItem.getArticleName()).isEqualTo(UPDATED_ARTICLE_NAME);
        assertThat(testItem.getPrimeCost()).isEqualTo(UPDATED_PRIME_COST);
        assertThat(testItem.getPrice()).isEqualTo(UPDATED_PRICE);
        assertThat(testItem.isAvailableForSale()).isEqualTo(UPDATED_AVAILABLE_FOR_SALE);
        assertThat(testItem.isScaleItem()).isEqualTo(UPDATED_SCALE_ITEM);
        assertThat(testItem.getEan()).isEqualTo(UPDATED_EAN);
        assertThat(testItem.isAccountRest()).isEqualTo(UPDATED_ACCOUNT_REST);
        assertThat(testItem.getRest()).isEqualTo(UPDATED_REST);
        assertThat(testItem.getLowRest()).isEqualTo(UPDATED_LOW_REST);
        assertThat(testItem.getParentItem()).isEqualTo(UPDATED_PARENT_ITEM);
    }

    @Test
    @Transactional
    public void updateNonExistingItem() throws Exception {
        int databaseSizeBeforeUpdate = itemRepository.findAll().size();

        // Create the Item

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restItemMockMvc.perform(put("/api/items")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(item)))
            .andExpect(status().isBadRequest());

        // Validate the Item in the database
        List<Item> itemList = itemRepository.findAll();
        assertThat(itemList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteItem() throws Exception {
        // Initialize the database
        itemRepository.saveAndFlush(item);

        int databaseSizeBeforeDelete = itemRepository.findAll().size();

        // Get the item
        restItemMockMvc.perform(delete("/api/items/{id}", item.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Item> itemList = itemRepository.findAll();
        assertThat(itemList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Item.class);
        Item item1 = new Item();
        item1.setId(1L);
        Item item2 = new Item();
        item2.setId(item1.getId());
        assertThat(item1).isEqualTo(item2);
        item2.setId(2L);
        assertThat(item1).isNotEqualTo(item2);
        item1.setId(null);
        assertThat(item1).isNotEqualTo(item2);
    }
}
