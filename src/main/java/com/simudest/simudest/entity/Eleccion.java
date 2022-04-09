package com.simudest.simudest.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="eleccion")
public class Eleccion implements Serializable {

	@Id
	@ManyToOne
	@JoinColumn(name = "id_user")
	private Usuario usuario;

	@Id
	@ManyToOne
	@JoinColumn(name = "id_plaza")
	private Plaza plaza;

	@Column(name="orden")
	private Integer orden;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Plaza getPlaza() {
		return plaza;
	}

	public void setPlaza(Plaza plaza) {
		this.plaza = plaza;
	}

	public Integer getOrden() {
		return orden;
	}

	public void setOrden(Integer orden) {
		this.orden = orden;
	}
}
