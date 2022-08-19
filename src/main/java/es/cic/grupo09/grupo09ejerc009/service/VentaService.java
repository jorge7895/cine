package es.cic.grupo09.grupo09ejerc009.service;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.cic.grupo09.grupo09ejerc009.model.Entrada;
import es.cic.grupo09.grupo09ejerc009.repository.EntradaDAO;
import es.cic.grupo09.grupo09ejerc009.repository.VentaDAO;
import es.cic.grupo09.grupo09ejerc009.util.VentaUtil;

@Service
@Transactional
public class VentaService {

	private Logger LOGGER = LogManager.getLogger(VentaService.class);

	@Autowired
	private VentaDAO ventaDao;
	
	@Autowired
	private EntradaDAO entradaDao;
	
	private VentaUtil ventaUtil = new VentaUtil();

	public List<Entrada> create(List<Entrada> listaEntradas) {

		LOGGER.trace("Utilizando servicio {} {}", getClass().getName()," para intento de creacion de la venta.");

		
		ventaUtil.validarDescuentos(listaEntradas);
		
		listaEntradas.stream()
			.forEach(e -> {
				ventaUtil.actualizarImporteVenta(e);
				ventaUtil.validarProyeccion(e);
				ventaUtil.validarButacaFila(e);
			});

		return entradaDao.saveAll(listaEntradas);
	}
	
	public void devolver(long venta) {

		LOGGER.trace(
				"Utilizando servicio {}, {}",getClass().getName()," para eliminar la venta.");

		ventaDao.deleteVenta(venta);
	}
	
	public List<Entrada> modificarVenta(long ventaId, List<Entrada> entradas) {
		
		LOGGER.trace(
				"Utilizando servicio {}, {}",getClass().getName(), "para intento de modificacion de la venta.");
		
		devolver(ventaId);
		
		return create(entradas);
	}
}