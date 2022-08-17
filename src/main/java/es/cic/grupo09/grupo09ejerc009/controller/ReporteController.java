package es.cic.grupo09.grupo09ejerc009.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.cic.grupo09.grupo09ejerc009.model.Entrada;
import es.cic.grupo09.grupo09ejerc009.service.ReporteService;

@RestController
@RequestMapping(path = "/api/v2/reporte")
public class ReporteController {

	private Logger LOGGER = LogManager.getLogger(ReporteController.class);

	@Autowired
	private ReporteService reporteService;

//	@GetMapping("/{id_sesion}/{id_sala}")
//	public ResponseEntity<List<Venta>> obtenerVentasPorSesionSala(@PathVariable("id_sesion") Long id_sesion,
//			@PathVariable("id_sala") Long id_sala) {
//
//		LOGGER.trace("Recuperando los datos de las ventas de la sala {} y la sesion {}", id_sesion, id_sala);
//
//		List<Venta> resultados = ventaService.readBySesionAndSala(id_sesion, id_sala);
//
//		return ResponseEntity.status(HttpStatus.ACCEPTED).contentType(MediaType.APPLICATION_JSON).body(resultados);
//
//	}

	@GetMapping("/total")
	public ResponseEntity<Page<Entrada>> obtenerVentasTotales(Pageable pageable) {

		LOGGER.trace("Recuperarndo los datos de las ventas");
		
		Page<Entrada> resultados = reporteService.reporteVentasTotales(pageable);

		return ResponseEntity.status(HttpStatus.ACCEPTED).contentType(MediaType.APPLICATION_JSON).body(resultados);

	}

//	@GetMapping("/dia")
//	public ResponseEntity<List<Venta>> obtenerVentasPorDia(@RequestBody LocalDate dia) {
//
//		LOGGER.trace("Recuperarndo los datos de las ventas del día {}", dia);
//
//		List<Venta> resultados = ventaService.readByDay(dia);
//
//		return ResponseEntity.status(HttpStatus.ACCEPTED).contentType(MediaType.APPLICATION_JSON).body(resultados);
//
//	}
//
//	@GetMapping("/{id_sesion}")
//	public ResponseEntity<List<Venta>> obtenerVentasPorSesion(@PathVariable("id_sesion") Long id) {
//
//		LOGGER.trace("Recuperarndo los datos de las ventas de la sesion: ", id);
//
//		List<Venta> resultados = reporteService.readBySesion(id);
//
//		return ResponseEntity.status(HttpStatus.ACCEPTED).contentType(MediaType.APPLICATION_JSON).body(resultados);
//
//	}
}
