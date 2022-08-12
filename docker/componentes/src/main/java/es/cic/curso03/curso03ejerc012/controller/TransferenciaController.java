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

import es.cic.curso03.curso03ejerc012.model.Transferencia;
import es.cic.curso03.curso03ejerc012.service.TransferenciaService;

@RestController
@RequestMapping(path = "/transferencias")
public class TransferenciaController {

	@Autowired
	private TransferenciaService transferenciaService;

	@GetMapping("/{id}")
	public ResponseEntity<List<Transferencia>> readIngresosById(@PathVariable("id") Long id) {
		List<Transferencia> list = this.transferenciaService.readTransferenciasById(id);
		return ResponseEntity.status(HttpStatus.FOUND).contentType(MediaType.APPLICATION_JSON).body(list);
	}

}
