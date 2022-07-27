package es.cic.grupo09.grupo09ejerc009.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.cic.grupo09.grupo09ejerc009.model.Venta;
import es.cic.grupo09.grupo09ejerc009.service.VentaService;

@RestController
@RequestMapping(path = "/ventas")
public class VentaController {
	
	@Autowired
	private VentaService ventaService;
	
	@PostMapping
	public Venta create(@RequestBody Venta venta) {
		return this.ventaService.create(venta);
	}
	
	@GetMapping("/{id}")
	public Venta read(@PathVariable(name = "id")long id) {
		return ventaService.read(id);
	}
	
	@GetMapping
	public List<Venta> read() {
		return ventaService.read();

	}
	
	@PutMapping
	public void update(@RequestBody Venta venta) {
		ventaService.update(venta);
	}
	
	@DeleteMapping("/{ventaId}")
	public void delete(@PathVariable("ventaId")long ventaId) {
		ventaService.delete(ventaId);
	}

}
