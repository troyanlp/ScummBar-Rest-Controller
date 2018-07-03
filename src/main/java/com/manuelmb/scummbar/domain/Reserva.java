package com.manuelmb.scummbar.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "reserva")
public class Reserva {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name  = "id", unique = true, nullable = false)
	private Long id;
	
	@Column(name = "dia", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dia;
	
	@Column(name = "personas", nullable = false)
	private Integer personas;
	
	@Column(name = "localizador", nullable = false, length = 50)
	private String localizador;
	
	@OneToOne
	@JoinColumn(name = "turno_id", nullable = false, insertable=true)
	private Turno turno;
	
	@OneToOne
	@JoinColumn(name = "restaurante_id", nullable = false, insertable=true)
	private Restaurante restaurante;
	
	@OneToOne
	@JoinColumn(name = "mesa_id", nullable = false, insertable=true)
	private Mesa mesa;
	
	public Reserva() {
		
	}
	
	public Reserva(Long id, Date dia, Integer personas, String localizador, Long turnoId) {
		this.id = id;
		this.dia = dia;
		this.personas = personas;
		this.localizador = localizador;
		//this.turnoId = turnoId;
	}

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

	public Turno getTurno() {
		return turno;
	}

	public void setTurno(Turno turno) {
		this.turno = turno;
	}

	public Restaurante getRestaurante() {
		return restaurante;
	}

	public void setRestaurante(Restaurante restaurante) {
		this.restaurante = restaurante;
	}

	public Mesa getMesa() {
		return mesa;
	}

	public void setMesa(Mesa mesa) {
		this.mesa = mesa;
	}

		
}
