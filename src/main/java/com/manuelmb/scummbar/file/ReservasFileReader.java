package com.manuelmb.scummbar.file;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.manuelmb.scummbar.domain.Reserva;
import com.manuelmb.scummbar.domain.Restaurante;
import com.manuelmb.scummbar.domain.Turno;
import com.manuelmb.scummbar.service.RestauranteService;

@Component
public class ReservasFileReader {
	
	private static final String FILENAME = "reservas.txt";
	
	@Autowired
	RestauranteService service;
	
	public ReservasFileReader() {
	}
	
	public ReservasFileReader(RestauranteService service) {
		this.service = service;
	}
	
	public List<String> processData() {
		List<String> lines = new ArrayList<String>();
		List<String> result = new ArrayList<String>();
		
		//InputStream in = this.getClass().getResourceAsStream("/reservas.txt");
		File file = new File(Thread.currentThread().getContextClassLoader().getResource("reservas.txt").getFile());
		
		try {
			LineIterator it = FileUtils.lineIterator(file, "UTF-8");
			try {
				while(it.hasNext()) {
					String line = it.nextLine();
					String s = processLine(line);
					result.add(s);
				}
			}finally {
				LineIterator.closeQuietly(it);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}
	
	public String processLine(String line) {
		
		
		String resultLine = line + " --> ";
		line = line.trim();
		String[] values = line.split(",");
		// Check the number of values
		if(values.length == 4) {
			boolean error = false;
			// Parse date
			String restauranteName = values[0].trim();
			List<Restaurante> restaurantes = service.getRestaurantes();
			if(restaurantes.stream().noneMatch(k -> k.getNombre().compareToIgnoreCase(restauranteName) == 0)) {
				resultLine += "No se ha podido reservar porque el nombre del restaurante introducido no est� en la base de datos.";
				error = true;
			}
			
			String numeroString = values[1].trim();
			int numero = Integer.parseInt(numeroString);
			if((numero < 1 || numero > 10) && !error) {
				resultLine += "No se ha podido reservar porque el numero de personas no est� entre 1 y 10.";
				error = true;
			}
			
			String dateString = values[2].trim();
			Date date = new Date();
			try {
				SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
				formatter.setLenient(false);
				date = formatter.parse(dateString);
			} catch (java.text.ParseException e) {
				// TODO Auto-generated catch block
				error = true;
				resultLine += "No se ha podido reservar porque el formato de la fecha no es correcto (dd/mm/yyyy).";
				e.printStackTrace();
			}
			
			String turnoString = values[3].trim();
			List<Turno> turnos = service.getTurnos();
			if(turnos.stream().noneMatch(k -> k.getDescripcion().compareToIgnoreCase(turnoString) == 0)) {
				if(!error) resultLine += "No se ha podido reservar porque el turno introducido no existe.";
				error = true;
			}
			
			
			if(!error) {
				// Create Reserva object and check if values are correct (The restaurant name exists...)
				Reserva reserva = new Reserva();
				reserva.setDia(date);
				reserva.setId(null);
				reserva.setPersonas(numero);
				Restaurante restaurante = service.getRestauranteByNombre(restauranteName);
				reserva.setRestaurante(restaurante);
				
				Date dateAux = new Date();
	            long aux = dateAux.getTime();
	            String localizador = aux + "";
	            reserva.setLocalizador(localizador);
	            
	            Turno turno = service.getTurnoByName(turnoString);
	            reserva.setTurno(turno);
	            
	            // Try to add the reservation to the database
				// Add line + conclusion to result, where conclusion is if it couldn't be saved or the localizador of the reservation
	            Optional<Long> response = service.checkTablesByDayAndTurn(date, numero, restaurante.getId(), turno.getId());
	    		if(response.isPresent()) {
	    			reserva.setMesa(service.getMesa(response.get()));
	    			service.addReserva(reserva);	
	    			resultLine += "Mesa reservada! Tu localizador es: " + localizador;
	    		}else {
	    			resultLine += "No se ha podido reservar porque todas las mesas estan ocupadas.";
	    		}
				
			}
			
		}else {
			resultLine += "No se ha podido reservar porque el formato de la reserva no es correcto.";
		}
		
		return resultLine;
		
	}
	
}
