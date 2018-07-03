package com.manuelmb.scummbar.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.manuelmb.scummbar.domain.Mesa;
import com.manuelmb.scummbar.domain.Reserva;
import com.manuelmb.scummbar.domain.Restaurante;
import com.manuelmb.scummbar.domain.Turno;


public interface RestauranteService {

	public void initialData();
	
	// Restaurante
	public void addRestaurante(Restaurante restaurante);
	public void updateRestaurante(Restaurante restaurante);
	public Restaurante getRestaurante(Long id);
	public Restaurante getRestauranteByNombre(String name);
	public void deleteRestaurante(Long id);
	public List<Restaurante> getRestaurantes();
	
	// Mesa
	public void addMesa(Mesa mesa);
	public void updateMesa(Mesa mesa);
	public Mesa getMesa(Long id);
	public void deleteMesa(Long id);
	public List<Mesa> getMesas();
	
	// Reserva
	public void addReserva(Reserva reserva);
	public void updateReserva(Reserva reserva);
	public Reserva getReserva(Long id);
	public Reserva getReservaByLocalizador(String localizador);
	public void deleteReserva(Long id);
	public int deleteReservaByLocalizador(String localizador);
	public List<Reserva> getReservas();
	
	// Turno
	public void addTurno(Turno turno);
	public void updateTurno(Turno turno);
	public Turno getTurno(Long id);
	public Turno getTurnoByName(String name);
	public void deleteTurno(Long id);
	public List<Turno> getTurnos();

	public Optional<Long> checkTablesByDayAndTurn(Date date, Integer personas, Long restauranteId, Long turno);
	public boolean isTableFreeOn(Mesa m, List<Reserva> reservas, int personas, Long turno);
	public String createLocalizador();
	public Reserva createReserva(Date date, Integer personas, long restauranteId, String localizador, long turno, long mesaId);
	
}
