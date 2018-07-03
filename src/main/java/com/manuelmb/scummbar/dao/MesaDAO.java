package com.manuelmb.scummbar.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.manuelmb.scummbar.domain.Mesa;

public interface MesaDAO extends CrudRepository<Mesa, Long>{
	List<Mesa> findAll();

	Mesa findById(Long id);
}
