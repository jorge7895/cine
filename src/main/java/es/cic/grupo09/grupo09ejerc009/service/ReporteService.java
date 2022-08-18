package es.cic.grupo09.grupo09ejerc009.service;

import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.cic.grupo09.grupo09ejerc009.model.Entrada;
import es.cic.grupo09.grupo09ejerc009.repository.ReporteDAO;

@Service
@Transactional
public class ReporteService {

	private Logger LOGGER = LogManager.getLogger(ReporteService.class);
	
	@Autowired
	private ReporteDAO reporteDao;
	
	public Page<Entrada> reporteVentasTotales(Pageable pageable) {

		LOGGER.trace(
				"Utilizando servicio {}, {}",getClass().getName()," para intento de lecturas de ventas.");

		return reporteDao.findAll(pageable);

	}
	
	public Page<Entrada> reporteVentasPorDia(LocalDateTime dia, Pageable pageable) {

		LOGGER.trace(
				"Utilizando servicio {}, {}",getClass().getName()," para intento de lecturas de ventas.");

		return reporteDao.readByDiaDeVenta(dia, pageable);

	}
	
	public Page<Entrada> reporteVentasPorProyeccion(long idSesion, Pageable pageable) {

		LOGGER.trace(
				"Utilizando servicio {}, {}",getClass().getName()," para intento de lecturas de ventas.");

		return reporteDao.readByProyeccion(idSesion, pageable);

	}
	
	public Page<Entrada> reporteVentasPorSesionSala(long idProyeccion, long idSala, Pageable pageable) {

		LOGGER.trace(
				"Utilizando servicio {}, {}",getClass().getName()," para intento de lecturas de ventas.");

		return reporteDao.readBySesionSala(idProyeccion, idSala, pageable);

	}
}
