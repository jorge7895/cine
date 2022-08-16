package es.cic.grupo09.grupo09ejerc009.service;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.cic.grupo09.grupo09ejerc009.model.Sala;
import es.cic.grupo09.grupo09ejerc009.repository.SalaRepository;

@Service
@Transactional
public class SalaService {

	private Logger LOGGER = LogManager.getLogger(SalaService.class);

	@Autowired
	private SalaRepository salaRepository;

	public Sala create(Sala sala) {

		LOGGER.trace("Utilizando servicio ".concat(getClass().getName()).concat(" para intento de creacion de sala."));

		salaRepository.save(sala);

		return salaRepository.findById(sala.getId()).get();

	}

	public Sala readById(long id) {

		LOGGER.trace("Utilizando servicio ".concat(getClass().getName()).concat(" para intento de lectura de sala."));

		return salaRepository.findById(id).get();

	}
}
