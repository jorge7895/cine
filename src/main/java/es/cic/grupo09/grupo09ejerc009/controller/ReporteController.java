package es.cic.grupo09.grupo09ejerc009.controller;

import java.time.LocalDate;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import es.cic.grupo09.grupo09ejerc009.exception.SesionException;
import es.cic.grupo09.grupo09ejerc009.model.Sala;
import es.cic.grupo09.grupo09ejerc009.model.Sesion;
import es.cic.grupo09.grupo09ejerc009.model.Venta;
import es.cic.grupo09.grupo09ejerc009.service.VentaService;

@RestController
@RequestMapping(path = "/api/v2/informe")
public class ReporteController {

	private Logger LOGGER = LogManager.getLogger(ReporteController.class);
	
	@Autowired
	private VentaService ventaService;
	
	@GetMapping("/sala/sesion")
	public ResponseEntity<List<Venta>> obtenerVentasPorSalaSesison(@Validated @RequestBody Sala sala, @Validated @RequestBody Sesion sesion, BindingResult errores){
		
		LOGGER.trace("Recuperando los datos de las ventas de la sala {} y la sesion {}", sala.getId(), sesion.getId());
		
		try {
			
			if (errores.hasErrors()) {
				throw new RuntimeException("Error al recuperar las ventas de la sala y la sesion");				
			}
			
			List<Venta> resultados = ventaService.readBySesionAndSala(sesion, sala);
			
			return ResponseEntity.status(HttpStatus.ACCEPTED).contentType(MediaType.APPLICATION_JSON).body(resultados);
			
		} catch (RuntimeException re) {
			
			LOGGER.error("Error al recuperar los datos de las ventas de la sala {} y la sesion {}", sala.getId(), sesion.getId());
			
			StringBuilder mensaje = new StringBuilder();
			mensaje.append("Error al recuperar las ventas de la sala: ");
			mensaje.append(sala.getId());
			mensaje.append(" y la sesion: ");
			mensaje.append(sesion.getId());
			mensaje.append(re.getMessage());
			
			throw new ResponseStatusException(1100, mensaje.toString(), re);
		}
		
	}
	
	@GetMapping("/total")
	public ResponseEntity<List<Venta>> obtenerVentasTotales(@Validated @RequestBody Sesion sesion, BindingResult errores){
		
		LOGGER.trace("Recuperarndo los datos de las ventas de la sala: {}", sesion.getId());
		
		try {
			
			if(errores.hasErrors()) {
				throw new SesionException("Error al recuperar las ventas de la sala", sesion);
			}
			
			List<Venta> resultados = ventaService.readAll();
			
			return ResponseEntity.status(HttpStatus.ACCEPTED).contentType(MediaType.APPLICATION_JSON).body(resultados);
			
		} catch (RuntimeException re) {
			
			LOGGER.error("Error al recuperar los datos de ventas de la sala: {}", sesion.getId());
			
			StringBuilder mensaje = new StringBuilder();
			mensaje.append("Error al recuperar la ventas de la sala");
			mensaje.append(re.getMessage());
			
			throw new ResponseStatusException(1101, mensaje.toString(), re);
		}
		
	}
	
	@GetMapping("/dia")
	public ResponseEntity<List<Venta>> obtenerVentasPorDia(@RequestBody LocalDate dia){
		
		LOGGER.trace("Recuperarndo los datos de las ventas del día {}", dia);
		
		try {
			
			List<Venta> resultados = ventaService.readByDay(dia);
			
			return ResponseEntity.status(HttpStatus.ACCEPTED).contentType(MediaType.APPLICATION_JSON).body(resultados);
			
		} catch (RuntimeException re) {
			
			LOGGER.error("Error al recuperar los datos de ventas por día");
			
			throw new ResponseStatusException(1101, "Error al recuperar los datos de las ventas por dia", re);
		}
		
	}
	
	@GetMapping("/sesion")
	public ResponseEntity<List<Venta>> obtenerVentasPorSesion(@Validated @RequestBody Sesion sesion,BindingResult errores){
		
		LOGGER.trace("Recuperarndo los datos de las ventas de la sesion: {}", sesion.getId());
		
		try {
			if (errores.hasErrors()) {
				throw new SesionException("Error en la sesión", sesion);
			}
			List<Venta> resultados = ventaService.readBySesion(sesion);
			
			return ResponseEntity.status(HttpStatus.ACCEPTED).contentType(MediaType.APPLICATION_JSON).body(resultados);
			
		} catch (RuntimeException re) {
			
			LOGGER.error("Error al recuperar los datos de ventas de la sesion: {}", sesion.getId());
			
			StringBuilder mensaje = new StringBuilder();
			mensaje.append("Error al recuperar los datos de las ventas de la sesión");
			mensaje.append(re.getMessage());
			
			throw new ResponseStatusException(1101, mensaje.toString(), re);
		}
		
	}
}
