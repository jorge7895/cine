package es.cic.grupo09.grupo09ejerc009.service;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.cic.grupo09.grupo09ejerc009.model.Entrada;
import es.cic.grupo09.grupo09ejerc009.repository.EntradaRepository;

@Service
@Transactional
public class EntradaService {

	private Logger LOGGER = LogManager.getLogger(DetalleVentaService.class);

	@Autowired
	private EntradaRepository entradaRepository;

	@Autowired
	private SesionService sesionService;

	public Entrada create(Entrada entrada) {
		LOGGER.trace(
				"Utilizando servicio ".concat(getClass().getName()).concat(" para intento de creacion de entrada"));

		entradaRepository.save(entrada);
		sesionService.update(entrada.getSesion().getId());

		return entrada;
	}

	public Object comprobarAforoByEntrada(Entrada entrada) {
		return entradaRepository.comprobarAforoByEntrada(entrada.getSesion());
	}
}
