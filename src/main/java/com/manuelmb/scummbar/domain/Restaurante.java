package com.manuelmb.scummbar.domain;

import java.util.Date;
import java.util.List;
import java.util.function.ToIntFunction;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "restaurante")
public class Restaurante {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "nombre", nullable = false, length = 50)
	private String nombre;

	@Column(name = "direccion", nullable = false, length = 100)
	private String direccion;

	@Column(name = "descripcion", nullable = false, length = 500)
	private String descripcion;

	@JoinTable(name = "mesas_restaurante")
	@OneToMany
	private List<Mesa> mesas;
	
	@JoinTable(name = "reservas_restaurante")
	@OneToMany
	private List<Reserva> reservas;
	
	
	public Restaurante() {
		
	}
	
	public Restaurante(Long id, String nombre, String direccion, String descripcion, List<Mesa> mesas, List<Reserva> reservas) {
		this.id = id;
		this.nombre = nombre;
		this.direccion = direccion;
		this.descripcion = descripcion;
		this.mesas = mesas;
		this.reservas = reservas;
	}
	
	

	@Transient
	public Integer getTotalPlazas() {
		return mesas.stream().mapToInt(new ToIntFunction<Mesa>() {
			public int applyAsInt(Mesa k) {
				return k.getCapacidad();
			}
		}).sum();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<Mesa> getMesas() {
		return mesas;
	}

	public void setMesas(List<Mesa> mesas) {
		this.mesas = mesas;
	}

	public List<Reserva> getReservas() {
		return reservas;
	}

	public void setReservas(List<Reserva> reservas) {
		this.reservas = reservas;
	}

	@Transient
	public Integer getPlazasReservadas(Date fecha, Turno turno) {
		return 0;
	}
}
