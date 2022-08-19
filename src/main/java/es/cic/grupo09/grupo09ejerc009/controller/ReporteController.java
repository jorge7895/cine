package es.cic.grupo09.grupo09ejerc009.controller;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.cic.grupo09.grupo09ejerc009.model.Entrada;
import es.cic.grupo09.grupo09ejerc009.service.ReporteService;

@RestController
@RequestMapping(path = "/api/v2/reporte")
public class ReporteController {

	private Logger logger = LogManager.getLogger(ReporteController.class);

	@Autowired
	private ReporteService reporteService;

	@GetMapping("/proyeccion/sala")
	public ResponseEntity<Page<Entrada>> obtenerVentasPorSesionSala(@RequestParam long idProyeccion, @RequestParam long idSala,Pageable pageable) {

		logger.trace("Recuperando los datos de las ventas de la sala {} y la sesion {}", idProyeccion, idSala);

		Page<Entrada> resultados = reporteService.reporteVentasPorSesionSala(idProyeccion, idSala, pageable);

		return ResponseEntity.status(HttpStatus.ACCEPTED).contentType(MediaType.APPLICATION_JSON).body(resultados);

	}

	@GetMapping("/total")
	public ResponseEntity<Page<Entrada>> obtenerVentasTotales(Pageable pageable) {

		logger.trace("Recuperarndo los datos de las ventas");
		
		Page<Entrada> resultados = reporteService.reporteVentasTotales(pageable);

		return ResponseEntity.status(HttpStatus.ACCEPTED).contentType(MediaType.APPLICATION_JSON).body(resultados);

	}

	@GetMapping("/dia")
	public ResponseEntity<Page<Entrada>> obtenerVentasPorDia(@RequestParam String dia, Pageable pageable) {

		logger.trace("Recuperarndo los datos de las ventas del día {}", dia);

		
		Page<Entrada> resultados = reporteService.reporteVentasPorDia(LocalDateTime.parse(dia), pageable);
		
		if(resultados.getContent().isEmpty()) {
			
			StringBuilder excepcion = new StringBuilder();
			excepcion.append("Sin resultados para el dia ");
			excepcion.append(dia);
			
			throw new NoSuchElementException(excepcion.toString());
		}

		return ResponseEntity.status(HttpStatus.ACCEPTED).contentType(MediaType.APPLICATION_JSON).body(resultados);

	}

	@GetMapping("/proyeccion")
	public ResponseEntity<Page<Entrada>> obtenerVentasPorProyeccion(@RequestParam long proyeccion, Pageable pageable) {

		logger.trace("Recuperarndo los datos de las ventas de la sesion: ", proyeccion);

		Page<Entrada> resultados = reporteService.reporteVentasPorProyeccion(proyeccion, pageable);

		return ResponseEntity.status(HttpStatus.ACCEPTED).contentType(MediaType.APPLICATION_JSON).body(resultados);

	}
}
