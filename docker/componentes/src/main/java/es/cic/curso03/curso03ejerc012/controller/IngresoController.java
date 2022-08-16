package es.cic.curso03.curso03ejerc012.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.cic.curso03.curso03ejerc012.model.Ingreso;
import es.cic.curso03.curso03ejerc012.service.IngresoService;

@RestController
@RequestMapping(path = "/ingresos")
public class IngresoController {

	@Autowired
	private IngresoService ingresarService;

	@GetMapping("/{id}")
	public ResponseEntity<List<Ingreso>> readIngresosById(@PathVariable("id") Long id) {
		List<Ingreso> list = this.ingresarService.readIngresosById(id);
		return ResponseEntity.status(HttpStatus.FOUND).contentType(MediaType.APPLICATION_JSON).body(list);
	}

}
