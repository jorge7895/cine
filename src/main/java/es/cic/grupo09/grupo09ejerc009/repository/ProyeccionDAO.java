package es.cic.grupo09.grupo09ejerc009.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.cic.grupo09.grupo09ejerc009.model.Proyeccion;

@Repository
public interface ProyeccionDAO extends JpaRepository<Proyeccion, Long>{

}
