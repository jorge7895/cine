package es.cic.curso03.curso03ejerc012.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import es.cic.curso03.curso03ejerc012.model.Retiro;

@Repository
public interface RetiroRepository extends CrudRepository<Retiro, Long> {

	@Query(name = "listaRetirosByCuenta")
	List<Retiro> readRetirosById(@Param("cuenta") Long id);

}
