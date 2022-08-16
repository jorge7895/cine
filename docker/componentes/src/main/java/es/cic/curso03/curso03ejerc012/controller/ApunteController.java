package es.cic.curso03.curso03ejerc012.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.cic.curso03.curso03ejerc012.model.Apunte;
import es.cic.curso03.curso03ejerc012.service.ApunteService;

@RestController
@RequestMapping(path = "/apuntes")
public class ApunteController {

	@Autowired
	private ApunteService apunteService;

	@PostMapping("/ingresar")
	public ResponseEntity<Apunte> createIngreso(@RequestBody Apunte apunte) {
		this.apunteService.createIngresar(apunte);
		return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON).body(apunte);
	}

	@PostMapping("/retirar")
	public ResponseEntity<Apunte> createRetirada(@RequestBody Apunte apunte) {
		this.apunteService.createRetirar(apunte);
		return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON).body(apunte);
	}

	@PostMapping("/transferir")
	public ResponseEntity<List<Apunte>> createTransferencia(@RequestBody List<Apunte> apuntes) {
		this.apunteService.createTransferencia(apuntes);
		return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON).body(apuntes);
	}

}
