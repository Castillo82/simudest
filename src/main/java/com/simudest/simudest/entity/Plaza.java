package com.simudest.simudest.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="plaza")
public class Plaza implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_plaza")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "id_provi")
	private Provincia provincia;

	@ManyToOne
	@JoinColumn(name = "id_convo")
	private Convocatoria convocatoria;

	@Column(name="nombre")
	private String nombre;

	@Column(name="ministerio")
	private String ministerio;

	@Column(name="centro")
	private String centro;

	@Column(name="localidad")
	private String localidad;

	@Column(name="denominacion")
	private String denominacion;

	@Column(name="nivel")
	private String nivel;

	@Column(name="c_especifico")
	private String c_especifico;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Provincia getProvincia() {
		return provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}

	public Convocatoria getConvocatoria() {
		return convocatoria;
	}

	public void setConvocatoria(Convocatoria convocatoria) {
		this.convocatoria = convocatoria;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getMinisterio() {
		return ministerio;
	}

	public void setMinisterio(String ministerio) {
		this.ministerio = ministerio;
	}

	public String getCentro() {
		return centro;
	}

	public void setCentro(String centro) {
		this.centro = centro;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public String getDenominacion() {
		return denominacion;
	}

	public void setDenominacion(String denominacion) {
		this.denominacion = denominacion;
	}

	public String getNivel() {
		return nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}

	public String getC_especifico() {
		return c_especifico;
	}

	public void setC_especifico(String c_especifico) {
		this.c_especifico = c_especifico;
	}
}
