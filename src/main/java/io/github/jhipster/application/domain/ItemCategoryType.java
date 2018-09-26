package io.github.jhipster.application.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A ItemCategoryType.
 */
@Entity
@Table(name = "item_category_type")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ItemCategoryType implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "itemCategoryType")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ItemCategory> categories = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public ItemCategoryType name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public ItemCategoryType description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<ItemCategory> getCategories() {
        return categories;
    }

    public ItemCategoryType categories(Set<ItemCategory> itemCategories) {
        this.categories = itemCategories;
        return this;
    }

    public ItemCategoryType addCategories(ItemCategory itemCategory) {
        this.categories.add(itemCategory);
        itemCategory.setItemCategoryType(this);
        return this;
    }

    public ItemCategoryType removeCategories(ItemCategory itemCategory) {
        this.categories.remove(itemCategory);
        itemCategory.setItemCategoryType(null);
        return this;
    }

    public void setCategories(Set<ItemCategory> itemCategories) {
        this.categories = itemCategories;
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
        ItemCategoryType itemCategoryType = (ItemCategoryType) o;
        if (itemCategoryType.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), itemCategoryType.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ItemCategoryType{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
