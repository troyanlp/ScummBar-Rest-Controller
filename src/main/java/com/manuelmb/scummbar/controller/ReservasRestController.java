package com.manuelmb.scummbar.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manuelmb.scummbar.controller.dto.ReservaDTO;
import com.manuelmb.scummbar.controller.mapper.ReservaMapper;
import com.manuelmb.scummbar.domain.Mesa;
import com.manuelmb.scummbar.domain.Reserva;
import com.manuelmb.scummbar.domain.Restaurante;
import com.manuelmb.scummbar.domain.Turno;
import com.manuelmb.scummbar.service.RestauranteService;

@RestController
@RequestMapping(value = "/reserva")
public class ReservasRestController {

	@Autowired
	private RestauranteService service;
	
	@Autowired
	private ReservaMapper mapper;
	
	public ReservasRestController() {
		super();
	}
	
	public ReservasRestController(RestauranteService service) {
		this.service = service;
	}
	
	@GetMapping("/{localizador}")
	public ReservaDTO getReserva(@PathVariable String localizador) {
		service.initialData();
		return mapper.makeReservaDTO(service.getReservaByLocalizador(localizador));
	}
	
	@GetMapping
	public List<ReservaDTO> getAllReservas() {
		service.initialData();
		return mapper.makeReservaDTOList(service.getReservas());
	}
	
	@PostMapping
	public void addReserva(@RequestBody ReservaDTO reserva) {
		service.initialData();
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		System.out.println("hola");
		
        try {
            String localizador = service.createLocalizador();
            
            Date date = new Date();
            date = formatter.parse(reserva.getDia().getDate() + "/" + reserva.getDia().getMonth() + "/" + reserva.getDia().getYear());
            
            //Check if there are tables on that turn
    		Optional<Long> result = service.checkTablesByDayAndTurn(date, reserva.getPersonas(), 
    				(long) service.getRestauranteByNombre(reserva.getNombreRestaurante()).getId(), 
    				(long) service.getTurnoByName(reserva.getTurno()).getId());
    		if(result.isPresent()) {
    			Reserva reservaToAdd = new Reserva();
    			reservaToAdd = mapper.makeReserva(reserva);
    			reservaToAdd.setLocalizador(localizador);
    			reservaToAdd.setMesa(service.getMesa(result.get()));
    			service.addReserva(reservaToAdd);	
    		}else {
    			
    		}
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
	}
	
	@DeleteMapping("/{localizador}")
	public int deleteReserva(@PathVariable String localizador) {
		service.initialData();
		return service.deleteReservaByLocalizador(localizador);
	}
	
}
