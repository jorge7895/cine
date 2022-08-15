package es.cic.grupo09.grupo09ejerc009.service;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.cic.grupo09.grupo09ejerc009.model.DetalleVenta;
import es.cic.grupo09.grupo09ejerc009.model.Entrada;
import es.cic.grupo09.grupo09ejerc009.model.Venta;
import es.cic.grupo09.grupo09ejerc009.repository.DetalleVentaRepository;

@Service
@Transactional
public class DetalleVentaService {

	private Logger LOGGER = LogManager.getLogger(DetalleVentaService.class);

	@Autowired
	private DetalleVentaRepository detalleVentaRepository;

	@Autowired
	private EntradaService entradaService;

	public void create(DetalleVenta detalleVenta) {

		LOGGER.trace("Utilizando servicio ".concat(getClass().getName())
				.concat(" para intento de creacion de detalleVenta."));

		entradaService.create(detalleVenta.getEntrada());
		detalleVentaRepository.save(detalleVenta);

	}

	public DetalleVenta localizarEntrada(Entrada entrada) {
		return detalleVentaRepository.findByEntrada(entrada);
	}

	public List<DetalleVenta> localizarEntradas(Venta venta) {
		return detalleVentaRepository.findByVenta(venta);
	}
}
