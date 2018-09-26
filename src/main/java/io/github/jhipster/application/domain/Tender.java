package io.github.jhipster.application.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Tender.
 */
@Entity
@Table(name = "tender")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Tender implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnoreProperties("tenderTypes")
    private TenderType tenderType;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TenderType getTenderType() {
        return tenderType;
    }

    public Tender tenderType(TenderType tenderType) {
        this.tenderType = tenderType;
        return this;
    }

    public void setTenderType(TenderType tenderType) {
        this.tenderType = tenderType;
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
        Tender tender = (Tender) o;
        if (tender.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), tender.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Tender{" +
            "id=" + getId() +
            "}";
    }
}
