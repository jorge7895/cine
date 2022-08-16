package es.cic.grupo09.grupo09ejerc009.service;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.cic.grupo09.grupo09ejerc009.exception.SesionException;
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

		comprabarSesionHora(sesion);

		sesion.setAforo(sesion.getSala().getCapacidad());
		sesionRepository.save(sesion);

		return sesionRepository.findById(sesion.getId()).get();

	}

	public Sesion readById(long id) {

		LOGGER.trace(
				"Utilizando servicio ".concat(getClass().getName()).concat(" para intento de lectura de sesion."));

		return sesionRepository.findById(id).get();

	}

	public List<Sesion> readAll() {

		LOGGER.trace(
				"Utilizando servicio ".concat(getClass().getName()).concat(" para intento de lectura de sesiones."));

		return (List<Sesion>) sesionRepository.findAll();

	}

	public void update(Long id) {

		LOGGER.trace(
				"Utilizando servicio ".concat(getClass().getName()).concat(" para intento de modificacion de sesion."));

		Sesion updateSesion = sesionRepository.findById(id).get();
		updateSesion.setAforo(updateSesion.getAforo() - 1);

		sesionRepository.save(updateSesion);
	}

	private void comprabarSesionHora(Sesion sesion) {
		for (Sesion sesionAux : readAll()) {
			int horaAux = sesionAux.getHoraEmpieza().getHour();
			int minAux = sesionAux.getHoraEmpieza().getMinute();

			minAux += sesion.getDuracionMin();
			while (minAux > 59) {
				horaAux++;
				minAux -= 60;
			}

			if (sesion.getHoraEmpieza().getHour() < horaAux && sesion.getHoraEmpieza().getMinute() < minAux) {
				throw new SesionException(sesionAux.getId(),
						"Error sesion. Ya existe una sesion programada para esa hora");
			}

		}
	}
}
