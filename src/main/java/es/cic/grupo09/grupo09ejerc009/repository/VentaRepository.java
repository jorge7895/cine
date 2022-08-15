package es.cic.grupo09.grupo09ejerc009.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import es.cic.grupo09.grupo09ejerc009.model.Sala;
import es.cic.grupo09.grupo09ejerc009.model.Sesion;
import es.cic.grupo09.grupo09ejerc009.model.Venta;

@Repository
public interface VentaRepository extends CrudRepository<Venta, Long> {

	@Query("SELECT v FROM Venta v INNER JOIN DetalleVenta dv ON v.id = dv.venta INNER JOIN Entrada e ON dv.entrada = e.id INNER JOIN Sesion se ON e.sesion = se.id INNER JOIN Sala sa ON se = :sesion WHERE se.id = :sesion AND sa = :sala")
	public List<Venta> readBySesionAndSala(@Param("sesion") Sesion sesion, @Param("sala") Sala sala);

//	@Query()
//	public List<Venta> readByDay(@Param("dia") LocalDateTime dia);
//
	@Query("SELECT v FROM Venta v INNER JOIN DetalleVenta dv ON v.id = dv.venta INNER JOIN Entrada e ON dv.entrada = e.id INNER JOIN Sesion se ON e.sesion = se.id INNER JOIN Sala sa ON se = :sesion WHERE se.id = :sesion")
	public List<Venta> readBySesion(@Param("sesion") Sesion sesion);
}
