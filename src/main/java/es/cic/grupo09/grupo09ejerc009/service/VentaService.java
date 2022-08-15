package es.cic.grupo09.grupo09ejerc009.service;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.cic.grupo09.grupo09ejerc009.exception.VentaException;
import es.cic.grupo09.grupo09ejerc009.model.DetalleVenta;
import es.cic.grupo09.grupo09ejerc009.model.Entrada;
import es.cic.grupo09.grupo09ejerc009.model.Venta;
import es.cic.grupo09.grupo09ejerc009.repository.VentaRepository;

@Service
@Transactional
public class VentaService {

	private Logger LOGGER = LogManager.getLogger(VentaService.class);

	@Autowired
	private VentaRepository ventaRepository;

	@Autowired
	private DetalleVentaService detalleVentaService;

	public Venta create(List<Entrada> listaEntradas) {

		LOGGER.trace("Utilizando servicio ".concat(getClass().getName()).concat(" para intento de creacion de venta."));

		Venta venta = new Venta();
		venta.setFhCreacion(LocalDateTime.now());
		ventaRepository.save(venta);

		// TODO metodo a mejorar.
		// La idea seria calcular la capacidad de todas las sesion antes de guardar los
		// detalles. Ahora tiene una ñapa que seria el if, pero es ineficiente.
		for (Entrada entrada : listaEntradas) {
			if (entrada.getSesion().getAforo() > 0) {
				DetalleVenta detalleVenta = new DetalleVenta();
				detalleVenta.setEntrada(entrada);
				detalleVenta.setVenta(venta);
				switch (entrada.getDescuento()) {
				case GRUPO:
					detalleVenta.setImporte(5.0f - (5.0f * 0.1f));
					break;
				case JOVEN:
					detalleVenta.setImporte(5.0f - (5.0f * 0.15f));
					break;
				case TERCERA_EDAD:
					detalleVenta.setImporte(5.0f - (5.0f * 0.25f));
					break;
				}
				detalleVentaService.create(detalleVenta);
			} else {
				throw new VentaException("Una sesion no esta diponible, sesion llena", venta);
			}
		}

		return ventaRepository.findById(venta.getId()).get();
	}
}
