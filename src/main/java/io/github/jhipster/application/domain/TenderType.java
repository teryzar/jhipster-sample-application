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
 * A TenderType.
 */
@Entity
@Table(name = "tender_type")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class TenderType implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "tenderType")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Tender> tenderTypes = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Tender> getTenderTypes() {
        return tenderTypes;
    }

    public TenderType tenderTypes(Set<Tender> tenders) {
        this.tenderTypes = tenders;
        return this;
    }

    public TenderType addTenderTypes(Tender tender) {
        this.tenderTypes.add(tender);
        tender.setTenderType(this);
        return this;
    }

    public TenderType removeTenderTypes(Tender tender) {
        this.tenderTypes.remove(tender);
        tender.setTenderType(null);
        return this;
    }

    public void setTenderTypes(Set<Tender> tenders) {
        this.tenderTypes = tenders;
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
        TenderType tenderType = (TenderType) o;
        if (tenderType.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), tenderType.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TenderType{" +
            "id=" + getId() +
            "}";
    }
}
