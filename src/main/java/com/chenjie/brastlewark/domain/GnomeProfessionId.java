package com.chenjie.brastlewark.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class GnomeProfessionId implements Serializable {
	public GnomeProfessionId(Gnome gnome, Profession profession) {
		this.gnome = gnome;
		this.profession = profession;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_gnome", nullable = false)
	private Gnome gnome;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_profession", nullable = false)
	private Profession profession;

	public Gnome getGnome() {
		return gnome;
	}

	public void setGnome(Gnome gnome) {
		this.gnome = gnome;
	}

	public Profession getProfession() {
		return profession;
	}

	public void setProfession(Profession profession) {
		this.profession = profession;
	}
	
	
}
