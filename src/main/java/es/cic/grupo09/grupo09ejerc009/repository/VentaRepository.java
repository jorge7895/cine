package es.cic.grupo09.grupo09ejerc009.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import es.cic.grupo09.grupo09ejerc009.model.Venta;

@Repository
public interface VentaRepository extends CrudRepository<Venta, Long> {
}
