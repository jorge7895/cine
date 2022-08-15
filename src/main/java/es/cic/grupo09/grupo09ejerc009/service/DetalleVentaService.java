package es.cic.grupo09.grupo09ejerc009.service;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.cic.grupo09.grupo09ejerc009.model.DetalleVenta;
import es.cic.grupo09.grupo09ejerc009.repository.DetalleVentaRepository;

@Service
@Transactional
public class DetalleVentaService {
	
	private Logger LOGGER = LogManager.getLogger(DetalleVentaService.class);

	@Autowired
	private DetalleVentaRepository detalleVentaRepository;

	public void create(DetalleVenta detalleVenta) {
		
		LOGGER.trace("Utilizando servicio ".concat(getClass().getName()).concat(" para intento de creacion de detalleVenta."));
		
		detalleVentaRepository.save(detalleVenta);
	}
}
