package com.manuelmb.scummbar.controller.dto;

import java.util.Date;

import com.manuelmb.scummbar.domain.Mesa;
import com.manuelmb.scummbar.domain.Restaurante;
import com.manuelmb.scummbar.domain.Turno;

//@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReservaDTO {

	private Long id;
	
	private Date dia;
	
	private Integer personas;
	
	private String localizador;
	
	private String turno;
	
	private String nombreRestaurante;
	
	private Integer numeroMesa;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDia() {
		return dia;
	}

	public void setDia(Date dia) {
		this.dia = dia;
	}

	public Integer getPersonas() {
		return personas;
	}

	public void setPersonas(Integer personas) {
		this.personas = personas;
	}

	public String getLocalizador() {
		return localizador;
	}

	public void setLocalizador(String localizador) {
		this.localizador = localizador;
	}

	public String getTurno() {
		return turno;
	}

	public void setTurno(String turno) {
		this.turno = turno;
	}

	public String getNombreRestaurante() {
		return nombreRestaurante;
	}

	public void setNombreRestaurante(String nombreRestaurante) {
		this.nombreRestaurante = nombreRestaurante;
	}

	public Integer getNumeroMesa() {
		return numeroMesa;
	}

	public void setNumeroMesa(Integer numeroMesa) {
		this.numeroMesa = numeroMesa;
	}


	
}
