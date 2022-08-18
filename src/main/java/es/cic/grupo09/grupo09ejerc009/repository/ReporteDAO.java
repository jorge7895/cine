package es.cic.grupo09.grupo09ejerc009.repository;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import es.cic.grupo09.grupo09ejerc009.model.Entrada;

@Repository
public interface ReporteDAO extends JpaRepository<Entrada, Long>{
		
	@Query(value = "SELECT e FROM Entrada e INNER JOIN Proyeccion p ON p.id = e.proyeccion INNER JOIN Sala s ON s.id = p.sala WHERE p.id = :idProyeccion AND s.id = :idSala")
	public Page<Entrada> readBySesionSala(long idProyeccion, long idSala, Pageable pageable);

	@Query(value = "SELECT e FROM Entrada e INNER JOIN Proyeccion p ON p.id = e.proyeccion WHERE p.id = :idProyeccion")
	public Page<Entrada> readByProyeccion(long idProyeccion, Pageable pageable);
	
	@Query(value = "SELECT e FROM Entrada e INNER JOIN Venta v ON v.id = e.venta WHERE v.diaDeVenta = :dia AND v.activa = true")
	public Page<Entrada> readByDiaDeVenta(LocalDateTime dia, Pageable pageable);

}
