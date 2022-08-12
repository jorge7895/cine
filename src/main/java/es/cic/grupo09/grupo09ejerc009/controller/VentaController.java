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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import es.cic.grupo09.grupo09ejerc009.model.Venta;
import es.cic.grupo09.grupo09ejerc009.service.VentaException;
import es.cic.grupo09.grupo09ejerc009.service.VentaService;

@RestController
@RequestMapping(path = "/api/v2/venta")
public class VentaController {
	
	private Logger LOGGER = LogManager.getLogger(VentaController.class);
	
	@Autowired
	private VentaService ventaService;
	
	@PostMapping
	public ResponseEntity<Venta> crearVenta(@Validated @RequestBody Venta venta,  BindingResult errores) {
		
		LOGGER.trace("Creando una venta nueva cantidad: {}, sala: {}, sesion: {}", 
				venta.getNumeroEntradas(), venta.getSalaId(), venta.getSesionId());
		
		try {
			if (errores.hasErrors()) {
				throw new VentaException("Error al crear la venta", venta);
			}
			
			this.ventaService.create(venta);
			
			return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON).body(venta);
			
		}catch (RuntimeException re){
			
			LOGGER.error("Error al crear la venta");
			
			StringBuilder mensaje = new StringBuilder();
			mensaje.append("Error al crear la venta ");
			mensaje.append(re.getMessage());
			
			throw new ResponseStatusException(1000, mensaje.toString(), re);
			
		}
	}
	
	@PutMapping("/devolucion")
	public ResponseEntity<Venta> updateVenta(@Validated @RequestBody Venta venta, @Validated @RequestBody Entrada...entrada, BindingResult errores) {
		
		LOGGER.trace("Actaualizando la venta con id: {}, para la sala {} de la sesion {}", 
				venta.getId(), venta.getSalaId(), venta.getSesionId());
		
		try {
			
			if (errores.hasErrors()) {
				
				throw new VentaException("Error al actualizar la venta", venta);
			}
			
			ventaService.update(venta, entrada);
			
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
