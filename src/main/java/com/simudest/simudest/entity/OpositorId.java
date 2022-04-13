package com.simudest.simudest.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class OpositorId implements Serializable {

	@Column(name = "id_user")
	private String idUsuario;

	@Column(name = "id_convo")
	private String idConvocatoria;

	public OpositorId() {
	}

	public OpositorId(String idUsuario, String idConvocatoria) {
		this.idUsuario = idUsuario;
		this.idConvocatoria = idConvocatoria;
	}

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getIdConvocatoria() {
		return idConvocatoria;
	}

	public void setIdConvocatoria(String idConvocatoria) {
		this.idConvocatoria = idConvocatoria;
	}
}
