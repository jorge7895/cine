package es.cic.grupo09.grupo09ejerc009.controller;

import java.time.LocalDate;
import java.util.List;

import javax.websocket.server.PathParam;

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

import es.cic.grupo09.grupo09ejerc009.exception.SesionException;
import es.cic.grupo09.grupo09ejerc009.model.Sesion;
import es.cic.grupo09.grupo09ejerc009.model.Venta;
import es.cic.grupo09.grupo09ejerc009.service.VentaService;

@RestController
@RequestMapping(path = "/api/v2/informe")
public class ReporteController {

	private Logger LOGGER = LogManager.getLogger(ReporteController.class);

	@Autowired
	private VentaService ventaService;

	@GetMapping("/sala={id_sesion}/sesion={id_sala}")
	public ResponseEntity<List<Venta>> obtenerVentasPorSalaSesison(@PathParam("id_sesion") long id,
			@PathParam("id_sala") long id2, BindingResult errores) {

		LOGGER.trace("Recuperando los datos de las ventas de la sala {} y la sesion {}", id, id2);

		if (errores.hasErrors()) {
			throw new RuntimeException("Error al recuperar las ventas de la sala y la sesion");
		}
		List<Venta> resultados = ventaService.readBySesionAndSala(id, id2);

		return ResponseEntity.status(HttpStatus.ACCEPTED).contentType(MediaType.APPLICATION_JSON).body(resultados);

	}

	@GetMapping("/total")
	public ResponseEntity<List<Venta>> obtenerVentasTotales(@Validated @RequestBody Sesion sesion,
			BindingResult errores) {

		LOGGER.trace("Recuperarndo los datos de las ventas de la sala: {}", sesion.getId());

		if (errores.hasErrors()) {
			throw new SesionException("Error al recuperar las ventas de la sala", sesion);
		}
		List<Venta> resultados = ventaService.readAll();

		return ResponseEntity.status(HttpStatus.ACCEPTED).contentType(MediaType.APPLICATION_JSON).body(resultados);

	}

	@GetMapping("/dia")
	public ResponseEntity<List<Venta>> obtenerVentasPorDia(@RequestBody LocalDate dia) {

		LOGGER.trace("Recuperarndo los datos de las ventas del día {}", dia);

		List<Venta> resultados = ventaService.readByDay(dia);

		return ResponseEntity.status(HttpStatus.ACCEPTED).contentType(MediaType.APPLICATION_JSON).body(resultados);

	}

	@GetMapping("/sesion={id_sesion}")
	public ResponseEntity<List<Venta>> obtenerVentasPorSesion(@Validated @PathParam("id_sesion") long id,
			BindingResult errores) {

		LOGGER.trace("Recuperarndo los datos de las ventas de la sesion: ", id);

		if (errores.hasErrors()) {
			throw new SesionException("Error en la sesión", id);
		}
		List<Venta> resultados = ventaService.readBySesion(id);

		return ResponseEntity.status(HttpStatus.ACCEPTED).contentType(MediaType.APPLICATION_JSON).body(resultados);

	}
}
