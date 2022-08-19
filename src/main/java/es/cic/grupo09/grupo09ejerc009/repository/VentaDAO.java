package es.cic.grupo09.grupo09ejerc009.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import es.cic.grupo09.grupo09ejerc009.model.Venta;

@Repository
public interface VentaDAO extends JpaRepository<Venta, Long> {
	
	@Modifying
	@Query(value = "UPDATE Venta v set v.activa = false WHERE v.id = :id")
	public void deleteVenta(long id);
}
