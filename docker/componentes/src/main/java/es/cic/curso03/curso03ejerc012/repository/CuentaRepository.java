package es.cic.curso03.curso03ejerc012.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import es.cic.curso03.curso03ejerc012.model.Cuenta;

@Repository
public interface CuentaRepository extends CrudRepository<Cuenta, Long> {

	Cuenta findByIban(String iban);

	@Query(name = "buscarClientesPotenciales")
	List<Cuenta> buscarClientesPotenciales();
}
