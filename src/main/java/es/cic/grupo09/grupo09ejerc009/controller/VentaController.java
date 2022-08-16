package es.cic.grupo09.grupo09ejerc009.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.cic.grupo09.grupo09ejerc009.exception.VentaException;
import es.cic.grupo09.grupo09ejerc009.model.Entrada;
import es.cic.grupo09.grupo09ejerc009.service.VentaService;

@RestController
@RequestMapping(path = "/api/v2/venta")
public class VentaController {

	private Logger LOGGER = LogManager.getLogger(VentaController.class);

	@Autowired
	private VentaService ventaService;

	@PostMapping
	public ResponseEntity<List<Entrada>> crearVenta(@Validated @RequestBody List<Entrada> entradas,
			BindingResult errores) {

		LOGGER.trace("Creando una venta nueva cantidad: {}", entradas.size());

		if (errores.hasErrors()) {
			throw new RuntimeException("Error al crear la venta");
		}
		this.ventaService.create(entradas);

		return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON).body(entradas);
	}

	@SuppressWarnings("unchecked")
	@PutMapping("/devolucion/{ventaId}")
	public ResponseEntity<List<Entrada>> updateVenta(@PathVariable(name = "ventaId") long ventaId,
			@Validated @RequestBody List<Entrada> entradas, BindingResult errores) {

		LOGGER.trace("Actaualizando la venta con id: {}", ventaId);

		if (errores.hasErrors()) {
			throw new VentaException("Error al actualizar la venta");
		}
		ventaService.updateDevolver(ventaId, entradas);

		return ResponseEntity.status(HttpStatus.ACCEPTED).contentType(MediaType.APPLICATION_JSON).body(entradas);

	}
}
