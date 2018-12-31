package com.chenjie.brastlewark.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A Gnome.
 */
@Entity
@Table(name = "gnome")
public class Gnome implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "thumbmail")
    private String thumbmail;

    @Column(name = "weight", precision = 10, scale = 2)
    private BigDecimal weight;

    @Column(name = "height", precision = 10, scale = 2)
    private BigDecimal height;

    @Column(name = "hair_color")
    private String hair_color;

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

    public Gnome name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getThumbmail() {
        return thumbmail;
    }

    public Gnome thumbmail(String thumbmail) {
        this.thumbmail = thumbmail;
        return this;
    }

    public void setThumbmail(String thumbmail) {
        this.thumbmail = thumbmail;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public Gnome weight(BigDecimal weight) {
        this.weight = weight;
        return this;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public BigDecimal getHeight() {
        return height;
    }

    public Gnome height(BigDecimal height) {
        this.height = height;
        return this;
    }

    public void setHeight(BigDecimal height) {
        this.height = height;
    }

    public String getHair_color() {
        return hair_color;
    }

    public Gnome hair_color(String hair_color) {
        this.hair_color = hair_color;
        return this;
    }

    public void setHair_color(String hair_color) {
        this.hair_color = hair_color;
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
        Gnome gnome = (Gnome) o;
        if (gnome.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), gnome.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Gnome{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", thumbmail='" + getThumbmail() + "'" +
            ", weight=" + getWeight() +
            ", height=" + getHeight() +
            ", hair_color='" + getHair_color() + "'" +
            "}";
    }
}
