package com.manuelmb.scummbar.controller.mapper;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.manuelmb.scummbar.controller.dto.ReservaDTO;
import com.manuelmb.scummbar.domain.Reserva;
import com.manuelmb.scummbar.domain.Turno;
import com.manuelmb.scummbar.service.RestauranteService;

@Component
public class ReservaMapper {
	
	@Autowired
	private RestauranteService service;
	
	public Reserva makeReserva(ReservaDTO reservaDTO) {
		Reserva reserva = new Reserva();
        reserva.setId(reservaDTO.getId());
    	reserva.setDia(reservaDTO.getDia());
    	reserva.setPersonas(reservaDTO.getPersonas());
    	reserva.setRestaurante((service.getRestauranteByNombre(reservaDTO.getNombreRestaurante())));
    	reserva.setLocalizador(reservaDTO.getLocalizador());
    	Turno turno = service.getTurnoByName(reservaDTO.getTurno());
    	reserva.setTurno(turno);
    	
    	//reserva.setMesa(service.getReservaByNumeroMesa(reservaDTO.getNumeroMesa()));
		return reserva;
    }

    public ReservaDTO makeReservaDTO(Reserva reserva) {
    	ReservaDTO reservaDTO = new ReservaDTO();
    	reservaDTO.setId(reserva.getId());
    	reservaDTO.setDia(reserva.getDia());
    	reservaDTO.setPersonas(reserva.getPersonas());
    	reservaDTO.setLocalizador(reserva.getLocalizador());
    	reservaDTO.setTurno(reserva.getTurno().getDescripcion());
    	reservaDTO.setNombreRestaurante(reserva.getRestaurante().getNombre());
    	reservaDTO.setNumeroMesa(reserva.getMesa().getNumero());
        return reservaDTO;
    }

    public List<ReservaDTO> makeReservaDTOList(Collection<Reserva> reservas) {
        return reservas.stream()
                .map(k -> makeReservaDTO(k))
                .collect(Collectors.toList());
    }

    public List<Reserva> makeReservaList(Collection<ReservaDTO> reservasDTO) {
        if (reservasDTO == null) {
            return Collections.emptyList();
        }
        return reservasDTO.stream()
                .map(k -> makeReserva(k))
                .collect(Collectors.toList());
    }
	
}
