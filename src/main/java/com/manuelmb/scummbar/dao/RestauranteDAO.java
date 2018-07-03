package com.manuelmb.scummbar.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.manuelmb.scummbar.domain.Mesa;
import com.manuelmb.scummbar.domain.Restaurante;

public interface RestauranteDAO extends CrudRepository<Restaurante, Long>{
	
	List<Restaurante> findAll();
	
	Restaurante findById(Long id);

	Restaurante findByNombre(String name);

	
}

