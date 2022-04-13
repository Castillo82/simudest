package com.simudest.simudest.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class EleccionId implements Serializable {

	@Column(name = "id_user")
	private String usuario;

	@Column(name = "id_plaza")
	private Integer plaza;

	public EleccionId() {
	}

	public EleccionId(String usuario, Integer plaza) {
		this.usuario = usuario;
		this.plaza = plaza;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Integer getPlaza() {
		return plaza;
	}

	public void setPlaza(Integer plaza) {
		this.plaza = plaza;
	}
}
