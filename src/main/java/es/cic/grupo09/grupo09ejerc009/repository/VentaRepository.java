package es.cic.grupo09.grupo09ejerc009.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import es.cic.grupo09.grupo09ejerc009.model.Venta;

@Repository
public class VentaRepository {

	// Con esta trampa tenemos un id para cada repository
	private static long id = 0;

	private int[] [] disponiblesSesionSala= new int[] []{ 
		{100, 50, 30}, 
		{100, 50, 30}, 
		{100, 50, 30} 
	};
	
	public static List<Venta> ventas = new ArrayList<>();
	/*
	 * Es una barbaridad meter un estado en una clase logica, 
	 * estamos esperando que nos dejen hacer BBDD.
	 */

	public Venta create(Venta venta) {
		id++;
		venta.setId(id);
		ventas.add(venta);
		return venta;
	}

	public Venta read(long id) {
		throw new UnsupportedOperationException("Operacion no permitida");
	}

	public void update(Venta venta) {
		throw new UnsupportedOperationException("Operacion no permitida");
	}

	public void delete(Venta venta) {
		throw new UnsupportedOperationException("Operacion no permitida");
	}
	
	public int getEntradasDisponibles(int sesionId, int salaId) {
		return disponiblesSesionSala [sesionId] [salaId];
	}
	
	public int actualizaEntradasDisponibles(int sesionId, int salaId, int nuevasEntradas){
		return disponiblesSesionSala [sesionId] [salaId] = nuevasEntradas;
	}
}
