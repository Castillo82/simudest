package com.simudest.simudest.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="eleccion")
public class Eleccion implements Serializable {

	@EmbeddedId
	private EleccionId id;

	@ManyToOne
	@JoinColumn(name = "id_user", nullable = false, insertable = false, updatable = false)
	private Usuario usuario;

	@ManyToOne
	@JoinColumn(name = "id_plaza", nullable = false, insertable = false, updatable = false)
	private Plaza plaza;

	@Column(name="orden")
	private Integer orden;

	public EleccionId getId() {
		return id;
	}

	public void setId(EleccionId id) {
		this.id = id;
	}

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
