package io.github.jhipster.application.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A Item.
 */
@Entity
@Table(name = "item")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Item implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "article_nr")
    private Long articleNr;

    @Column(name = "article_name")
    private String articleName;

    @Column(name = "prime_cost", precision = 10, scale = 2)
    private BigDecimal primeCost;

    @Column(name = "price", precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "available_for_sale")
    private Boolean availableForSale;

    @Column(name = "scale_item")
    private Boolean scaleItem;

    @Column(name = "ean")
    private String ean;

    @Column(name = "account_rest")
    private Boolean accountRest;

    @Column(name = "rest", precision = 10, scale = 2)
    private BigDecimal rest;

    @Column(name = "low_rest", precision = 10, scale = 2)
    private BigDecimal lowRest;

    @Column(name = "parent_item")
    private Long parentItem;

    @ManyToOne
    @JsonIgnoreProperties("")
    private Item parentItem;

    @ManyToOne
    @JsonIgnoreProperties("itemCategories")
    private ItemCategory itemCategory;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getArticleNr() {
        return articleNr;
    }

    public Item articleNr(Long articleNr) {
        this.articleNr = articleNr;
        return this;
    }

    public void setArticleNr(Long articleNr) {
        this.articleNr = articleNr;
    }

    public String getArticleName() {
        return articleName;
    }

    public Item articleName(String articleName) {
        this.articleName = articleName;
        return this;
    }

    public void setArticleName(String articleName) {
        this.articleName = articleName;
    }

    public BigDecimal getPrimeCost() {
        return primeCost;
    }

    public Item primeCost(BigDecimal primeCost) {
        this.primeCost = primeCost;
        return this;
    }

    public void setPrimeCost(BigDecimal primeCost) {
        this.primeCost = primeCost;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Item price(BigDecimal price) {
        this.price = price;
        return this;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Boolean isAvailableForSale() {
        return availableForSale;
    }

    public Item availableForSale(Boolean availableForSale) {
        this.availableForSale = availableForSale;
        return this;
    }

    public void setAvailableForSale(Boolean availableForSale) {
        this.availableForSale = availableForSale;
    }

    public Boolean isScaleItem() {
        return scaleItem;
    }

    public Item scaleItem(Boolean scaleItem) {
        this.scaleItem = scaleItem;
        return this;
    }

    public void setScaleItem(Boolean scaleItem) {
        this.scaleItem = scaleItem;
    }

    public String getEan() {
        return ean;
    }

    public Item ean(String ean) {
        this.ean = ean;
        return this;
    }

    public void setEan(String ean) {
        this.ean = ean;
    }

    public Boolean isAccountRest() {
        return accountRest;
    }

    public Item accountRest(Boolean accountRest) {
        this.accountRest = accountRest;
        return this;
    }

    public void setAccountRest(Boolean accountRest) {
        this.accountRest = accountRest;
    }

    public BigDecimal getRest() {
        return rest;
    }

    public Item rest(BigDecimal rest) {
        this.rest = rest;
        return this;
    }

    public void setRest(BigDecimal rest) {
        this.rest = rest;
    }

    public BigDecimal getLowRest() {
        return lowRest;
    }

    public Item lowRest(BigDecimal lowRest) {
        this.lowRest = lowRest;
        return this;
    }

    public void setLowRest(BigDecimal lowRest) {
        this.lowRest = lowRest;
    }

    public Long getParentItem() {
        return parentItem;
    }

    public Item parentItem(Long parentItem) {
        this.parentItem = parentItem;
        return this;
    }

    public void setParentItem(Long parentItem) {
        this.parentItem = parentItem;
    }

    public Item getParentItem() {
        return parentItem;
    }

    public Item parentItem(Item item) {
        this.parentItem = item;
        return this;
    }

    public void setParentItem(Item item) {
        this.parentItem = item;
    }

    public ItemCategory getItemCategory() {
        return itemCategory;
    }

    public Item itemCategory(ItemCategory itemCategory) {
        this.itemCategory = itemCategory;
        return this;
    }

    public void setItemCategory(ItemCategory itemCategory) {
        this.itemCategory = itemCategory;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Item item = (Item) o;
        if (item.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), item.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Item{" +
            "id=" + getId() +
            ", articleNr=" + getArticleNr() +
            ", articleName='" + getArticleName() + "'" +
            ", primeCost=" + getPrimeCost() +
            ", price=" + getPrice() +
            ", availableForSale='" + isAvailableForSale() + "'" +
            ", scaleItem='" + isScaleItem() + "'" +
            ", ean='" + getEan() + "'" +
            ", accountRest='" + isAccountRest() + "'" +
            ", rest=" + getRest() +
            ", lowRest=" + getLowRest() +
            ", parentItem=" + getParentItem() +
            "}";
    }
}
