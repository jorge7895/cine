package es.cic.grupo09.grupo09ejerc009.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import es.cic.grupo09.grupo09ejerc009.model.Entrada;
import es.cic.grupo09.grupo09ejerc009.service.VentaService;

@RestController
@RequestMapping(path = "/api/v2/venta")
public class VentaController {

	private Logger logger = LogManager.getLogger(VentaController.class);

	@Autowired
	private VentaService ventaService;

	@PostMapping
	public ResponseEntity<List<Entrada>> crearVenta(@Validated @RequestBody List<Entrada> entradas) {

		logger.trace("Creando una venta nueva cantidad: {}", entradas.size());
		
		this.ventaService.create(entradas);

		return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON).body(entradas);
	}

	@DeleteMapping("/devolucion")
	@ResponseStatus(code = HttpStatus.OK)
	public void deleteVenta(@RequestParam long idVenta) {

		logger.trace("Actaualizando la venta con id: {}", idVenta);

		ventaService.devolver(idVenta);
	}
	
	@PutMapping("/modificacion")
	public ResponseEntity<List<Entrada>> updateVenta(@RequestParam long ventaId, @Validated @RequestBody List<Entrada> entradas) {

		logger.trace("Actaualizando la venta con id: {}", ventaId);

		
		ventaService.modificarVenta(ventaId, entradas);

		return ResponseEntity.status(HttpStatus.ACCEPTED).contentType(MediaType.APPLICATION_JSON).body(entradas);

	}
}
