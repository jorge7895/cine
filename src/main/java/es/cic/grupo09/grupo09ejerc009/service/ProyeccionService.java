package es.cic.grupo09.grupo09ejerc009.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.cic.grupo09.grupo09ejerc009.model.Proyeccion;
import es.cic.grupo09.grupo09ejerc009.repository.ProyeccionDAO;

@Service
public class ProyeccionService {

	@Autowired
	private ProyeccionDAO proyeccionDAO;
	
	public Proyeccion crearProyeccion(Proyeccion proyeccion) {
		
		return proyeccionDAO.save(proyeccion);
	}
}
