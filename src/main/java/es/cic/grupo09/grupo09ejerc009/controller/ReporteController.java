package es.cic.grupo09.grupo09ejerc009.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v2/informe")
public class ReporteController {
//
//	private Logger LOGGER = LogManager.getLogger(ReporteController.class);
//	
//	@Autowired
//	private VentaService ventaService;
//	
//	@GetMapping("/sala/sesion")
//	public ResponseEntity<List<Venta>> obtenerVentasPorSalaSesison(@Validated @RequestBody Sala sala, @Validated @RequestBody Sesion sesion, BindingResult errores){
//		
//		LOGGER.trace("Recuperando los datos de las ventas de la sala {} y la sesion {}", sala.getId(), sesion.getId());
//		
//		try {
//			
//			if (errores.hasErrors()) {
//				throw new RuntimeException("Error al recuperar las ventas de la sala y la sesion");				
//			}
//			
//			List<Venta> resultados = ventaService.obtenerVentasPorSalaSesion(sala, sesion);
//			
//			return ResponseEntity.status(HttpStatus.ACCEPTED).contentType(MediaType.APPLICATION_JSON).body(resultados);
//			
//		} catch (RuntimeException re) {
//			
//			LOGGER.error("Error al recuperar los datos de las ventas de la sala {} y la sesion {}", sala.getId(), sesion.getId());
//			
//			StringBuilder mensaje = new StringBuilder();
//			mensaje.append("Error al recuperar las ventas de la sala: ");
//			mensaje.append(sala.getId());
//			mensaje.append(" y la sesion: ");
//			mensaje.append(sesion.getId());
//			mensaje.append(re.getMessage());
//			
//			throw new ResponseStatusException(1100, mensaje.toString(), re);
//		}
//		
//	}
//	
//	@GetMapping("/sala")
//	public ResponseEntity<List<Venta>> obtenerVentasPorSala(@Validated @RequestBody Sala sala, BindingResult errores){
//		
//		LOGGER.trace("Recuperarndo los datos de las ventas de la sala: {}", sala.getId());
//		
//		try {
//			
//			if(errores.hasErrors()) {
//				throw new SalaException("Error al recuperar las ventas de la sala", sala);
//			}
//			
//			List<Venta> resultados = ventaService.obtenerVentasPorSala(sala);
//			
//			return ResponseEntity.status(HttpStatus.ACCEPTED).contentType(MediaType.APPLICATION_JSON).body(resultados);
//			
//		} catch (RuntimeException re) {
//			
//			LOGGER.error("Error al recuperar los datos de ventas de la sala: {}", sala.getId());
//			
//			StringBuilder mensaje = new StringBuilder();
//			mensaje.append("Error al recuperar la ventas de la sala");
//			mensaje.append(re.getMessage());
//			
//			throw new ResponseStatusException(1101, mensaje.toString(), re);
//		}
//		
//	}
//	
//	@GetMapping("/dia")
//	public ResponseEntity<List<Venta>> obtenerVentasPorDia(){
//		
//		LOGGER.trace("Recuperarndo los datos de las ventas del día");
//		
//		try {
//			
//			List<Venta> resultados = ventaService.obtenerVentasPorDia();
//			
//			return ResponseEntity.status(HttpStatus.ACCEPTED).contentType(MediaType.APPLICATION_JSON).body(resultados);
//			
//		} catch (RuntimeException re) {
//			
//			LOGGER.error("Error al recuperar los datos de ventas por día");
//			
//			throw new ResponseStatusException(1101, "Error al recuperar los datos de las ventas por dia", re);
//		}
//		
//	}
//	
//	@GetMapping("/venta/sesion")
//	public ResponseEntity<List<Venta>> obtenerVentasPorSesion(@Validated @RequestBody Sesion sesion,BindingResult errores){
//		
//		LOGGER.trace("Recuperarndo los datos de las ventas de la sesion: {}", sesion.getId());
//		
//		try {
//			if (errores.hasErrors()) {
//				throw new SesionException("Error en la sesión", sesion);
//			}
//			List<Venta> resultados = ventaService.obtenerVentasPorDia(sesion);
//			
//			return ResponseEntity.status(HttpStatus.ACCEPTED).contentType(MediaType.APPLICATION_JSON).body(resultados);
//			
//		} catch (RuntimeException re) {
//			
//			LOGGER.error("Error al recuperar los datos de ventas de la sesion: {}", sesion.getId());
//			
//			StringBuilder mensaje = new StringBuilder();
//			mensaje.append("Error al recuperar los datos de las ventas de la sesión");
//			mensaje.append(re.getMessage());
//			
//			throw new ResponseStatusException(1101, mensaje.toString(), re);
//		}
//		
//	}
}
