package es.cic.grupo09.grupo09ejerc009.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v2/venta")
public class VentaController {
//	
//	private Logger LOGGER = LogManager.getLogger(VentaController.class);
//	
//	@Autowired
//	private VentaService ventaService;
//	
//	@PostMapping
//	public ResponseEntity<Venta> crearVenta(@Validated @RequestBody Venta venta,  BindingResult errores) {
//		
//		LOGGER.trace("Creando una venta nueva cantidad: {}, sala: {}, sesion: {}", 
//				venta.getNumeroEntradas(), venta.getSalaId(), venta.getSesionId());
//		
//		try {
//			if (errores.hasErrors()) {
//				throw new VentaException("Error al crear la venta", venta);
//			}
//			
//			this.ventaService.create(venta);
//			
//			return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON).body(venta);
//			
//		}catch (RuntimeException re){
//			
//			LOGGER.error("Error al crear la venta");
//			
//			StringBuilder mensaje = new StringBuilder();
//			mensaje.append("Error al crear la venta ");
//			mensaje.append(re.getMessage());
//			
//			throw new ResponseStatusException(1000, mensaje.toString(), re);
//			
//		}
//	}
//	
//	@PutMapping("/devolucion")
//	public ResponseEntity<Venta> updateVenta(@Validated @RequestBody Venta venta, @Validated @RequestBody Entrada...entrada, BindingResult errores) {
//		
//		LOGGER.trace("Actaualizando la venta con id: {}, para la sala {} de la sesion {}", 
//				venta.getId(), venta.getSalaId(), venta.getSesionId());
//		
//		try {
//			
//			if (errores.hasErrors()) {
//				
//				throw new VentaException("Error al actualizar la venta", venta);
//			}
//			
//			ventaService.update(venta, entrada);
//			
//			return ResponseEntity.status(HttpStatus.ACCEPTED).contentType(MediaType.APPLICATION_JSON).body(venta);
//			
//		}catch (RuntimeException re) {
//			
//			LOGGER.error("Error al actualizar la venta con id: {}", venta.getId());
//			
//			StringBuilder mensaje = new StringBuilder();
//			mensaje.append("Error al actualizar la venta");
//			mensaje.append(re.getMessage());
//			
//			throw new ResponseStatusException(1001,mensaje.toString(),re);
//			
//		}
//	}
}
