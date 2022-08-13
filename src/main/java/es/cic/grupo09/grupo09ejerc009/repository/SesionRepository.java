package es.cic.grupo09.grupo09ejerc009.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import es.cic.grupo09.grupo09ejerc009.model.Sesion;

@Repository
public interface SesionRepository extends CrudRepository<Sesion, Long> {
}
