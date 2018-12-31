package com.chenjie.brastlewark.service.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A DTO for the Gnome entity.
 */
public class GnomeDTO implements Serializable {

    private Long id;

    private String name;

    private String thumbmail;

    private BigDecimal weight;

    private BigDecimal height;

    private String hair_color;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getThumbmail() {
        return thumbmail;
    }

    public void setThumbmail(String thumbmail) {
        this.thumbmail = thumbmail;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public BigDecimal getHeight() {
        return height;
    }

    public void setHeight(BigDecimal height) {
        this.height = height;
    }

    public String getHair_color() {
        return hair_color;
    }

    public void setHair_color(String hair_color) {
        this.hair_color = hair_color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        GnomeDTO gnomeDTO = (GnomeDTO) o;
        if (gnomeDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), gnomeDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "GnomeDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", thumbmail='" + getThumbmail() + "'" +
            ", weight=" + getWeight() +
            ", height=" + getHeight() +
            ", hair_color='" + getHair_color() + "'" +
            "}";
    }
}
