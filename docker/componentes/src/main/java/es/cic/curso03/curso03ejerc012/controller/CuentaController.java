package es.cic.curso03.curso03ejerc012.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.cic.curso03.curso03ejerc012.model.Cuenta;
import es.cic.curso03.curso03ejerc012.service.CuentaService;

@RestController
@RequestMapping(path = "/cuentas")
public class CuentaController {

	@Autowired
	private CuentaService cuentaService;

	@PostMapping
	public ResponseEntity<Cuenta> create(@RequestBody Cuenta cuenta) {
		this.cuentaService.create(cuenta);
		return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON).body(cuenta);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Cuenta> read(@PathVariable("id") Long id) {
		Cuenta cuenta = this.cuentaService.readById(id);
		return ResponseEntity.status(HttpStatus.FOUND).contentType(MediaType.APPLICATION_JSON).body(cuenta);
	}

	@GetMapping("/iban={iban}")
	public ResponseEntity<Cuenta> read(@PathVariable("iban") String iban) {
		Cuenta cuenta = this.cuentaService.readByIban(iban);
		return ResponseEntity.status(HttpStatus.FOUND).contentType(MediaType.APPLICATION_JSON).body(cuenta);
	}

	@GetMapping("/buscarClientesPotenciales")
	public ResponseEntity<List<Cuenta>> readClientesPotenciales() {
		List<Cuenta> cuentas = this.cuentaService.buscarClientesPotenciales();
		return ResponseEntity.status(HttpStatus.FOUND).contentType(MediaType.APPLICATION_JSON).body(cuentas);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Cuenta> delete(@PathVariable("id") Long id) {
		this.cuentaService.delete(id);
		Cuenta cuenta = this.cuentaService.readById(id);
		return ResponseEntity.status(HttpStatus.ACCEPTED).contentType(MediaType.APPLICATION_JSON).body(cuenta);
	}
}
