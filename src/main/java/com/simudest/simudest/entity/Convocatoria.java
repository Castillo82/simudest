package com.simudest.simudest.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="convocatoria")
public class Convocatoria implements Serializable {

	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	@Column(name="id_convo")
	private String id;

	@ManyToOne
	@JoinColumn(name = "id_espec")
	private Especialidad especialidad;

	@ManyToOne
	@JoinColumn(name = "id_organ")
	private Organismo organismo;

	@ManyToOne
	@JoinColumn(name = "id_user")
	private Usuario usuario;

	@Column(name="nombre")
	private String nombre;

	@Column(name="estado")
	private String estado;

	@Column(name="nopositores")
	private Integer nopositores;

	@Column(name="nplazas")
	private Integer nplazas;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Especialidad getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(Especialidad especialidad) {
		this.especialidad = especialidad;
	}

	public Organismo getOrganismo() {
		return organismo;
	}

	public void setOrganismo(Organismo organismo) {
		this.organismo = organismo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Integer getNopositores() {
		return nopositores;
	}

	public void setNopositores(Integer nopositores) {
		this.nopositores = nopositores;
	}

	public Integer getNplazas() {
		return nplazas;
	}

	public void setNplazas(Integer nplazas) {
		this.nplazas = nplazas;
	}
}
