package es.cic.grupo09.grupo09ejerc009.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.cic.grupo09.grupo09ejerc009.model.Entrada;

@Repository
public interface EntradaDAO extends JpaRepository<Entrada, Long>{
//	public List<Venta> readByProyeccionAndSala(Proyeccion proyeccion, Sala sala);
//
//	public List<Venta> readByProyeccion(Proyeccion proyeccion);
//	
//	public List<Venta> readByDiaDeVenta(LocalDateTime diaDeVenta);
}
