package es.cic.curso03.curso03ejerc012.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import es.cic.curso03.curso03ejerc012.model.Ingreso;

@Repository
public interface IngresoRepository extends CrudRepository<Ingreso, Long> {

	@Query(name = "listaIngresosByCuenta")
	List<Ingreso> listaIngresoByCuenta(@Param("cuenta") Long id);
}
