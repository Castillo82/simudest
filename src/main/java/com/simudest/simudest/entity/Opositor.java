package com.simudest.simudest.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="opositor")
public class Opositor implements Serializable {

	@Id
	@ManyToOne
	@JoinColumn(name = "id_user")
	private Usuario usuario;

	@Id
	@ManyToOne
	@JoinColumn(name = "id_convo")
	private Convocatoria convocatoria;

	@Column(name="validado")
	private Boolean validado;

	@Column(name="orden")
	private Integer orden;

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
