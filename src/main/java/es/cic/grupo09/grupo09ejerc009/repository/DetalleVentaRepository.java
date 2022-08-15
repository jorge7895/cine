package es.cic.grupo09.grupo09ejerc009.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import es.cic.grupo09.grupo09ejerc009.model.DetalleVenta;
import es.cic.grupo09.grupo09ejerc009.model.Entrada;
import es.cic.grupo09.grupo09ejerc009.model.Venta;

@Repository
public interface DetalleVentaRepository extends CrudRepository<DetalleVenta, Long> {

	DetalleVenta findByEntrada(Entrada entrada);

	List<DetalleVenta> findByVenta(Venta venta);
}
