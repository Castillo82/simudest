package com.simudest.simudest.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="opositor")
public class Opositor implements Serializable {

	@EmbeddedId
	private OpositorId id;

	@ManyToOne
	@JoinColumn(name = "id_user", nullable = false, insertable = false, updatable = false)
	private Usuario usuario;

	@ManyToOne
	@JoinColumn(name = "id_convo", nullable = false, insertable = false, updatable = false)
	private Convocatoria convocatoria;

	@Column(name="validado")
	private Boolean validado;

	@Column(name="orden")
	private Integer orden;

	public OpositorId getId() {
		return id;
	}

	public void setId(OpositorId id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Convocatoria getConvocatoria() {
		return convocatoria;
	}

	public void setConvocatoria(Convocatoria convocatoria) {
		this.convocatoria = convocatoria;
	}

	public Boolean getValidado() {
		return validado;
	}

	public void setValidado(Boolean validado) {
		this.validado = validado;
	}

	public Integer getOrden() {
		return orden;
	}

	public void setOrden(Integer orden) {
		this.orden = orden;
	}
}
