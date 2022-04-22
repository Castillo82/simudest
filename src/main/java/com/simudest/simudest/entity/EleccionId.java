package com.simudest.simudest.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class EleccionId implements Serializable {

	@Column(name = "id_user")
	private String usuario;

	@Column(name = "id_plaza")
	private String plaza;

	public EleccionId() {
	}

	public EleccionId(String usuario, String plaza) {
		this.usuario = usuario;
		this.plaza = plaza;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPlaza() {
		return plaza;
	}

	public void setPlaza(String plaza) {
		this.plaza = plaza;
	}
}
