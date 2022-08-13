package es.cic.grupo09.grupo09ejerc009.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import es.cic.grupo09.grupo09ejerc009.model.Entrada;

@Repository
public interface EntradaRepository extends CrudRepository<Entrada, Long> {
}
