package es.cic.grupo09.grupo09ejerc009.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.cic.grupo09.grupo09ejerc009.model.Proyeccion;
import es.cic.grupo09.grupo09ejerc009.service.ProyeccionService;

@RestController
@RequestMapping(path = "/api/v2/proyeccion")
public class ProyeccionesController {
	
	private Logger LOGGER = LogManager.getLogger(ProyeccionesController.class);

	@Autowired
	private ProyeccionService proyeccionService;

	@PostMapping
	public ResponseEntity<Proyeccion> crearProyeccion(@RequestBody Proyeccion proyeccion, BindingResult errores) {

		LOGGER.trace("Creando una proyeccion nueva para la pelicula: {}", proyeccion.getPelicula());

		if (errores.hasErrors()) {
			throw new RuntimeException("Error al crear la venta");
		}
		proyeccionService.crearProyeccion(proyeccion);

		return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON).body(proyeccion);
	}

}
