package es.cic.grupo09.grupo09ejerc009.service;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.cic.grupo09.grupo09ejerc009.model.Sesion;
import es.cic.grupo09.grupo09ejerc009.repository.SesionRepository;

@Service
@Transactional
public class SesionService {

	private Logger LOGGER = LogManager.getLogger(SesionService.class);

	@Autowired
	private SesionRepository sesionRepository;

	public Sesion create(Sesion sesion) {

		LOGGER.trace(
				"Utilizando servicio ".concat(getClass().getName()).concat(" para intento de creacion de sesion."));

		sesion.setAforo(sesion.getSala().getCapacidad());
		sesionRepository.save(sesion);

		return sesionRepository.findById(sesion.getId()).get();

	}
}
