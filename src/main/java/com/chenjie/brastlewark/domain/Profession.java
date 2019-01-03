package com.chenjie.brastlewark.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * A Profession.
 */
@Entity
@Table(name = "profession")
public class Profession implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "name")
    private String name;

	@OneToMany(mappedBy = "id.profession")
	private List<GnomeProfession> gnomeProfession;
    
    public List<GnomeProfession> getGnomeProfession() {
		return gnomeProfession;
	}

	public void setGnomeProfession(List<GnomeProfession> gnomeProfession) {
		this.gnomeProfession = gnomeProfession;
	}

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

    public Profession name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
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
        Profession profession = (Profession) o;
        if (profession.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), profession.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Profession{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
