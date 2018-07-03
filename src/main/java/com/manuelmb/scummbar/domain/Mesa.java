package com.manuelmb.scummbar.domain;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mesa")
public class Mesa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name  = "id", unique = true, nullable = false)
	private Long id;
	
	@Column(name = "numero", nullable = false)
	private Integer numero;
	
	@Column(name = "capacidad", nullable = false)
	private Integer capacidad;
	
	public Mesa() {
		
	}
	
	public Mesa(Long id, Integer numero, Integer capacidad) {
		this.id = id;
		this.numero = numero;
		this.capacidad = capacidad;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public Integer getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(Integer capacidad) {
		this.capacidad = capacidad;
	}
	
	
}
