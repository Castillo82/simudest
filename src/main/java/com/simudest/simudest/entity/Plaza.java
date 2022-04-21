package com.simudest.simudest.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name="plaza")
public class Plaza implements Serializable {

	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	@Column(name="id_plaza")
	private String id;

	@ManyToOne
	@JoinColumn(name = "id_provi")
	private Provincia provincia;

	@ManyToOne
	@JoinColumn(name = "id_convo")
	private Convocatoria convocatoria;

	@Column(name="codigo")
	private String codigo;

	@Column(name="ministerio")
	private String ministerio;

	@Column(name="centro")
	private String centro;

	@Column(name="localidad")
	private String localidad;

	@Column(name="denominacion")
	private String denominacion;

	@Column(name="nivel")
	private Integer nivel;

	@Column(name="c_especifico")
	private BigDecimal c_especifico;

	public String getId() {
		return id;
	}

	public void setId(String id) {
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

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
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

	public Integer getNivel() {
		return nivel;
	}

	public void setNivel(Integer nivel) {
		this.nivel = nivel;
	}

	public BigDecimal getC_especifico() {
		return c_especifico;
	}

	public void setC_especifico(BigDecimal c_especifico) {
		this.c_especifico = c_especifico;
	}
}
