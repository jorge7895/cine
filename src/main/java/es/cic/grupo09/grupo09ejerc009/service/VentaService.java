package es.cic.grupo09.grupo09ejerc009.service;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.cic.grupo09.grupo09ejerc009.model.Entrada;
import es.cic.grupo09.grupo09ejerc009.model.Venta;
import es.cic.grupo09.grupo09ejerc009.repository.EntradaDAO;
import es.cic.grupo09.grupo09ejerc009.repository.VentaDAO;

@Service
@Transactional
public class VentaService {

	private Logger LOGGER = LogManager.getLogger(VentaService.class);

	@Autowired
	private VentaDAO ventaDao;
	
	@Autowired
	private EntradaDAO entradaDao;

	public List<Entrada> create(List<Entrada> listaEntradas) {

		LOGGER.trace("Utilizando servicio {} {}", getClass().getName()," para intento de creacion de venta.");

		listaEntradas.stream().forEach(e -> actualizarImporteVenta(e));

		return entradaDao.saveAll(listaEntradas);
	}

	public void actualizarImporteVenta(Entrada entrada) {
		
		float importeTotalActual = entrada.getVenta().getImporteTotal();
		
		switch (entrada.getTipoEntrada()) {
		case SENIOR:
				importeTotalActual -= (entrada.getPrecioEntrada() * 0.25f);
				entrada.getVenta().setImporteTotal(importeTotalActual);
			break;
		case JOVEN:
			importeTotalActual -= (entrada.getPrecioEntrada() * 0.15f);
			entrada.getVenta().setImporteTotal(importeTotalActual);
		break;
		case GRUPO:
			importeTotalActual -= (entrada.getPrecioEntrada() * 0.1f);
			entrada.getVenta().setImporteTotal(importeTotalActual);
		break;
		default:
			entrada.getVenta().setImporteTotal(importeTotalActual);
			break;
		}
	}
	
	@SuppressWarnings("unchecked")
	public Venta updateDevolver(Long id, List<Entrada>... entradas) {

		LOGGER.trace(
				"Utilizando servicio ".concat(getClass().getName()).concat(" para intento de modificacion de ventas."));

		Venta ventaAux = ventaDao.findById(id).get();

		switch (entradas.length) {
		case 1:
//			devolverEntradas(ventaAux, entradas[0]);
			break;
		case 2:
//			devolverEntradas(ventaAux, entradas[0]);
			
			break;
		}

		ventaAux.setFhModificado(LocalDateTime.now());
		ventaDao.save(ventaAux);

		return ventaAux;

	}
}