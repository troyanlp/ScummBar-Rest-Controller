package com.manuelmb.scummbar.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.manuelmb.scummbar.domain.Reserva;
import com.manuelmb.scummbar.domain.Restaurante;

public interface ReservaDAO extends CrudRepository<Reserva, Long>{
	List<Reserva> findAll();

	Reserva findById(Long id);
	
	Reserva findByLocalizador(String localizador);

	int deleteByLocalizador(String localizador);

	List<Reserva> findByRestauranteAndDia(Restaurante restaurante, Date date);

	List<Reserva> findByDia(Date date);

}

