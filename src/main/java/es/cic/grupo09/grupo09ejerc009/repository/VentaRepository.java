package es.cic.grupo09.grupo09ejerc009.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import es.cic.grupo09.grupo09ejerc009.model.Sesion;
import es.cic.grupo09.grupo09ejerc009.model.Venta;

@Repository
public class VentaRepository {


	@PersistenceContext
	private EntityManager em;

	private int[] [] disponiblesSesionSala= new int[] []{ 
		{100, 50, 30}, 
		{100, 50, 30}, 
		{100, 50, 30} 
	};
	
		
	public Venta create(Venta venta) {
		em.persist(venta);
		return venta;
	}

	public Venta read(long id) {
		return em.find(Venta.class, id);
	}
	
	public List<Venta> read() {
		return em.createQuery("select v from Venta v", Venta.class).getResultList();
	}

	public Venta update(Venta venta) {
		return em.merge(venta);
	}

	public void delete(long ventaId) {
		Venta venta = read(ventaId);
		em.remove(venta);
		em.flush();
	}
	
	public Sesion getSesion(long sesionId) {
		return em.find(Sesion.class, sesionId);
	}
	
	public Sesion updateSesion(Sesion sesion) {
		return em.merge(sesion);
	}
	
	public int getEntradasDisponibles(Sesion sesion) {
		return sesion.getButacas();
	}
	
	public int actualizaEntradasDisponibles(Sesion sesion, int nuevasEntradas){
		return sesion.getButacas()-nuevasEntradas;
	}
}
