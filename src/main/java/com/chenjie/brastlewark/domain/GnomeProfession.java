package com.chenjie.brastlewark.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "gnome_profession")
public class GnomeProfession implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public GnomeProfession(Gnome gnome, Profession profession) {
		this.id = new GnomeProfessionId(gnome, profession);
	}

	@EmbeddedId
	private GnomeProfessionId id;
	
	@Column(name = "activo")
	private Boolean activo;

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public GnomeProfessionId getId() {
		return id;
	}

	public void setId(GnomeProfessionId id) {
		this.id = id;
	}
	
}
