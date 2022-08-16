package es.cic.grupo09.grupo09ejerc009.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import es.cic.grupo09.grupo09ejerc009.model.Entrada;
import es.cic.grupo09.grupo09ejerc009.model.Sesion;

@Repository
public interface EntradaRepository extends CrudRepository<Entrada, Long> {

	@Query("SELECT s.aforo FROM Sesion s WHERE s = :sesion")
	Object comprobarAforoByEntrada(@Param("sesion") Sesion sesion);

}
