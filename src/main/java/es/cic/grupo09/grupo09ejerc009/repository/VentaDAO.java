package es.cic.grupo09.grupo09ejerc009.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.cic.grupo09.grupo09ejerc009.model.Venta;

@Repository
public interface VentaDAO extends JpaRepository<Venta, Long> {


}
