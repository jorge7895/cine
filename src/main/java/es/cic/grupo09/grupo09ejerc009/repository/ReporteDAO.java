package es.cic.grupo09.grupo09ejerc009.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.cic.grupo09.grupo09ejerc009.model.Entrada;
import es.cic.grupo09.grupo09ejerc009.model.Proyeccion;
import es.cic.grupo09.grupo09ejerc009.model.Venta;

@Repository
public interface ReporteDAO extends JpaRepository<Entrada, Long>{
		
//	public List<Venta> readByProyeccionAndSala(Proyeccion proyeccion, Sala sala);

	public List<Venta> readByProyeccion(Proyeccion proyeccion);
	
//	public List<Venta> readByDiaDeVenta(LocalDateTime diaDeVenta);

}
