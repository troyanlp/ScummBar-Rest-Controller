package com.manuelmb.scummbar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manuelmb.scummbar.controller.dto.RestauranteDTO;
import com.manuelmb.scummbar.controller.mapper.RestauranteMapper;
import com.manuelmb.scummbar.domain.Restaurante;
import com.manuelmb.scummbar.service.RestauranteService;

@RestController
@RequestMapping(value = "restaurantes")
public class RestaurantesRestController {

	@Autowired
	private RestauranteService service;
	
	@Autowired
	private RestauranteMapper mapper;
	
	public RestaurantesRestController() {
		super();
	}
	
	public RestaurantesRestController(RestauranteService service) {
		this.service = service;
	}
	
	@GetMapping
	public List<RestauranteDTO> allRestaurantes() {
		service.initialData();
		List<RestauranteDTO> listaReservas = mapper.makeRestauranteDTOList(service.getRestaurantes());
		return listaReservas;
	}
	
	@GetMapping("/{nombre}")
	public RestauranteDTO restaurante(@PathVariable String nombre) {
		service.initialData();
		return mapper.makeRestauranteDTO(service.getRestauranteByNombre(nombre));
	}
}
