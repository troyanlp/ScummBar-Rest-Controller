package com.manuelmb.scummbar.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.manuelmb.scummbar.domain.Mesa;
import com.manuelmb.scummbar.domain.Turno;

public interface TurnoDAO extends CrudRepository<Turno, Long>{
	List<Turno> findAll();

	Turno findById(Long id);

	Turno findByDescripcion(String name);
}
