package com.manuelmb.scummbar.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.manuelmb.scummbar.dao.MesaDAO;
import com.manuelmb.scummbar.dao.ReservaDAO;
import com.manuelmb.scummbar.dao.RestauranteDAO;
import com.manuelmb.scummbar.dao.TurnoDAO;
import com.manuelmb.scummbar.domain.Mesa;
import com.manuelmb.scummbar.domain.Reserva;
import com.manuelmb.scummbar.domain.Restaurante;
import com.manuelmb.scummbar.domain.Turno;
import com.manuelmb.scummbar.service.RestauranteService;

@Service
@Transactional
public class RestauranteServiceImpl implements RestauranteService{

	@Autowired
	RestauranteDAO restauranteDAO;
	@Autowired
	MesaDAO mesaDAO;
	@Autowired
	ReservaDAO reservaDAO;
	@Autowired
	TurnoDAO turnoDAO;
	
	public RestauranteServiceImpl() {
		
	}
	
	public RestauranteServiceImpl(ReservaDAO reservaDAO, RestauranteDAO restauranteDAO) {
		this.reservaDAO = reservaDAO;
		this.restauranteDAO = restauranteDAO;
	}
	
	public void initialData() {
		List<Mesa> mesas1 = new ArrayList<Mesa>() {{
			add(new Mesa((long) 1,1,2));
			add(new Mesa((long) 2,2,4));
			add(new Mesa((long) 3,3,10));
			add(new Mesa((long) 4,4,2));
		}};
		
		List<String> s = Arrays.asList("a","b","c");
		List<Mesa> mesas2 = new ArrayList<Mesa>() {{
			add(new Mesa((long) 5,1,2));
			add(new Mesa((long) 6,2,4));
			add(new Mesa((long) 7,3,4));
		}};
		List<Mesa> mesas3 = new ArrayList<Mesa>() {{
			add(new Mesa((long) 8,1,4));
			add(new Mesa((long) 9,2,4));
			add(new Mesa((long) 10,3,4));
		}};
		
		if(mesaDAO.findAll().isEmpty()) {
			mesas1.stream().forEach(mesa -> mesaDAO.save(mesa));
			mesas2.stream().forEach(mesa -> mesaDAO.save(mesa));
			mesas3.stream().forEach(mesa -> mesaDAO.save(mesa));
		}
		
		if(turnoDAO.findAll().isEmpty()) {
			turnoDAO.save(new Turno(null,"De 8 a 9"));
			turnoDAO.save(new Turno(null,"De 9 a 10"));
			turnoDAO.save(new Turno(null,"De 12 a 13"));
			turnoDAO.save(new Turno(null,"De 13 a 14"));
			turnoDAO.save(new Turno(null,"De 14 a 15"));
			turnoDAO.save(new Turno(null,"De 15 a 16"));
			turnoDAO.save(new Turno(null,"De 20 a 21"));
			turnoDAO.save(new Turno(null,"De 21 a 22"));
			turnoDAO.save(new Turno(null,"De 22 a 23"));
			turnoDAO.save(new Turno(null,"De 23 a 0"));
			
		}
		
		if(restauranteDAO.findAll().isEmpty()){
			List<Reserva> reservas = new ArrayList<>();
			restauranteDAO.save(new Restaurante(null, "Belushis", "Carrer Bergara", "Bar/Hostal australiano con ambiente universitario y buenas hamburguesas.", mesas1, reservas));
			restauranteDAO.save(new Restaurante(null, "Bacoa", "Ronda Universidad", "Bar centrico especializado en hamburguesas.", mesas2, reservas));
			restauranteDAO.save(new Restaurante(null, "Dominos Pizza", "Carrer Bergara", "Restaurante de comida rapida especializado en pizzas.", mesas3, reservas));
		}
	}
	
	public void addRestaurante(Restaurante restaurante) {
		restauranteDAO.save(restaurante);
		
	}

	public void updateRestaurante(Restaurante restaurante) {
		restauranteDAO.save(restaurante);
		
	}

	public Restaurante getRestaurante(Long id) {
		return restauranteDAO.findById(id);
	}
	
	public Restaurante getRestauranteByNombre(String name) {
		return restauranteDAO.findByNombre(name);
	}

	public void deleteRestaurante(Long id) {
		restauranteDAO.delete(id);
		
	}

	public List<Restaurante> getRestaurantes() {
		return restauranteDAO.findAll();

	}

	public void addMesa(Mesa mesa) {
		mesaDAO.save(mesa);
		
	}

	public void updateMesa(Mesa mesa) {
		mesaDAO.save(mesa);
		
	}

	public Mesa getMesa(Long id) {
		return mesaDAO.findById(id);
	}

	public void deleteMesa(Long id) {
		mesaDAO.delete(id);
	}

	public List<Mesa> getMesas() {
		return mesaDAO.findAll();
	}

	public void addReserva(Reserva reserva) {
		reservaDAO.save(reserva);
		
	}

	public void updateReserva(Reserva reserva) {
		reservaDAO.save(reserva);
	}

	public Reserva getReserva(Long id) {
		return reservaDAO.findById(id);
	}
	
	public Reserva getReservaByLocalizador(String localizador) {
		return reservaDAO.findByLocalizador(localizador);
	}

	public void deleteReserva(Long id) {
		reservaDAO.delete(id);	
	}
	
	@Override
	public int deleteReservaByLocalizador(String localizador) {
		return reservaDAO.deleteByLocalizador(localizador);
	}

	public List<Reserva> getReservas() {
		return reservaDAO.findAll();
	}

	public void addTurno(Turno turno) {
		turnoDAO.save(turno);
		
	}

	public void updateTurno(Turno turno) {
		turnoDAO.save(turno);
		
	}

	public Turno getTurno(Long id) {
		return turnoDAO.findById(id);
	}

	public Turno getTurnoByName(String name) {
		Turno turno = turnoDAO.findByDescripcion(name);
		return turno;
	}

	public void deleteTurno(Long id) {
		turnoDAO.delete(id);
		
	}

	public List<Turno> getTurnos() {
		return turnoDAO.findAll();
	}

	@Override
	public Optional<Long> checkTablesByDayAndTurn(Date date, Integer personas, Long restauranteId, Long turno) {
		List<Reserva> reservas = reservaDAO.findByRestauranteAndDia(getRestaurante(restauranteId), date);
		Restaurante res = restauranteDAO.findById(restauranteId);
		// Check tables and only work with those whose capacity > number of persons
		List<Mesa> mesas = res.getMesas().stream().filter(j -> isTableFreeOn(j, reservas, personas, turno)).
				sorted((o1,o2) -> o1.getCapacidad().compareTo(o2.getCapacidad())).collect(Collectors.toList());
		
		if(!mesas.isEmpty()) {
			return Optional.of(mesas.get(0).getId());
		}else {
			return Optional.empty();
		}
	}
	
	public boolean isTableFreeOn(Mesa m, List<Reserva> reservas, int personas, Long turno){
		if(m.getCapacidad() < personas) {
			return false;
		}else {
			return reservas.stream().noneMatch(r -> r.getMesa().getId() == m.getId() && r.getTurno().getId() == turno);
		}
	}
	
	public List<Reserva> getReservasByDate(Date date){
		return reservaDAO.findByDia(date);
	}

	@Override
	public String createLocalizador() {
		return UUID.randomUUID().toString();
	}

	@Override
	public Reserva createReserva(Date date, Integer personas, long restauranteId, String localizador, long turno,
			long mesaId) {
		Reserva reserva = new Reserva();
		reserva.setDia(date);
		reserva.setPersonas(personas);
		reserva.setRestaurante(getRestaurante(restauranteId));
		reserva.setLocalizador(localizador);
		reserva.setTurno(getTurno(turno));
		reserva.setMesa(getMesa(mesaId));
		return reserva;
	}
	
}
