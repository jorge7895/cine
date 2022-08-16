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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import es.cic.grupo09.grupo09ejerc009.exception.VentaException;
import es.cic.grupo09.grupo09ejerc009.model.Entrada;
import es.cic.grupo09.grupo09ejerc009.model.Venta;
import es.cic.grupo09.grupo09ejerc009.service.VentaService;

@RestController
@RequestMapping(path = "/api/v2/venta")
public class VentaController {
	
	private Logger LOGGER = LogManager.getLogger(VentaController.class);
	
	@Autowired
	private VentaService ventaService;
	
	@PostMapping
	public ResponseEntity<List<Entrada>> crearVenta(@Validated @RequestBody List<Entrada> entradas,  BindingResult errores) {
		
		LOGGER.trace("Creando una venta nueva cantidad: {}", entradas.size());
		
		try {
			if (errores.hasErrors()) {
				throw new RuntimeException("Error al crear la venta");
			}
			
			this.ventaService.create(entradas);
			
			return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON).body(entradas);
			
		}catch (RuntimeException re){
			
			LOGGER.error("Error al crear la venta");
			
			StringBuilder mensaje = new StringBuilder();
			mensaje.append("Error al crear la venta ");
			mensaje.append(re.getMessage());
			
			throw new ResponseStatusException(1000, mensaje.toString(), re);
			
		}
	}
	
	@SuppressWarnings("unchecked")
	@PutMapping("/devolucion")
	public ResponseEntity<Venta> updateVenta(@Validated @RequestBody Venta venta, @Validated @RequestBody List<Entrada> entradas, BindingResult errores) {
		
		LOGGER.trace("Actaualizando la venta con id: {}",venta.getId());
		
		try {
			
			if (errores.hasErrors()) {
				
				throw new VentaException("Error al actualizar la venta", venta);
			}
			
			ventaService.updateDevolver(venta, entradas);
			
			return ResponseEntity.status(HttpStatus.ACCEPTED).contentType(MediaType.APPLICATION_JSON).body(venta);
			
		}catch (RuntimeException re) {
			
			LOGGER.error("Error al actualizar la venta con id: {}", venta.getId());
			
			StringBuilder mensaje = new StringBuilder();
			mensaje.append("Error al actualizar la venta");
			mensaje.append(re.getMessage());
			
			throw new ResponseStatusException(1001,mensaje.toString(),re);
			
		}
	}
}
