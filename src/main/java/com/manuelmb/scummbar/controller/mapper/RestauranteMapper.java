package com.manuelmb.scummbar.controller.mapper;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.manuelmb.scummbar.controller.dto.RestauranteDTO;
import com.manuelmb.scummbar.domain.Restaurante;
import com.manuelmb.scummbar.service.RestauranteService;

@Component
public class RestauranteMapper {

	@Autowired
	private RestauranteService service;
	
	public Restaurante makeRestaurante(RestauranteDTO restauranteDTO) {
        Restaurante restaurante = service.getRestaurante(restauranteDTO.getId());
		return new Restaurante();
    }

    public RestauranteDTO makeRestauranteDTO(Restaurante restaurante) {
    	RestauranteDTO restauranteDTO = new RestauranteDTO();
    	restauranteDTO.setId(restaurante.getId());
    	restauranteDTO.setNombre(restaurante.getNombre());
    	restauranteDTO.setDescripcion(restaurante.getDescripcion());
    	restauranteDTO.setDireccion(restaurante.getDireccion());
        return restauranteDTO;
    }

    public List<RestauranteDTO> makeRestauranteDTOList(Collection<Restaurante> restaurantes) {
        return restaurantes.stream()
                .map(k -> makeRestauranteDTO(k))
                .collect(Collectors.toList());
    }

    public List<Restaurante> makeRestauranteList(Collection<RestauranteDTO> restaurantesDTO) {
        if (restaurantesDTO == null) {
            return Collections.emptyList();
        }
        return restaurantesDTO.stream()
                .map(k -> makeRestaurante(k))
                .collect(Collectors.toList());
    }
    
}
