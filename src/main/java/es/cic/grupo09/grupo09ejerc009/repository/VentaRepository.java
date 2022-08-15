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

	@Query(name = "listaFilterSesionAndSala")
	public List<Venta> readBySesionAndSala(@Param("sesion") Sesion sesion, @Param("sala") Sala sala);

	@Query(name = "listaFilterSesion")
	public List<Venta> readBySesion(@Param("sesion") Sesion sesion);
}
