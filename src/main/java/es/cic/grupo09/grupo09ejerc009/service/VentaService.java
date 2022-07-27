package es.cic.grupo09.grupo09ejerc009.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.cic.grupo09.grupo09ejerc009.model.Sesion;
import es.cic.grupo09.grupo09ejerc009.model.Venta;
import es.cic.grupo09.grupo09ejerc009.repository.VentaRepository;
import es.cic.grupo09.grupo09ejerc009.util.SalaUtil;
import es.cic.grupo09.grupo09ejerc009.util.SesionUtil;

@Service
@Transactional
public class VentaService {

	private static final int PRECIO_POR_ENTRADA = 5;

	@Autowired
	private VentaRepository ventaRepository;

	@Autowired
	private SesionUtil sesionUtil;
	@Autowired
	private SalaUtil salaUtil;

	public Venta create(Venta venta) {
		
		// Verificar que la sala y sesión son válidos
		if (sesionUtil.isSesionValida(venta)) {
			throw new SesionException("La sesion no es valida", venta.getSesionId());
		}

		if (salaUtil.isSalaValida(venta)) {
			throw new SalaException("La sala no es valida", venta.getSalaId());
		}

		/*
		 * Compruebo que el número de entradas es válido:
		 * 
		 * -Número de entradas POSITIVO
		 * 
		 * -Compruebo que quedan suficientes entradas para vender Aplicamos el descuento
		 * pertinente
		 */
		if (venta.getNumeroEntradas() <= 0) {
			throw new VentaException("El numero de entradas no puede ser no positivo", venta);
		}
		
		Sesion sesion = null;
		
		if (venta.getSalaId() == 1 && venta.getSesionId() == 1) {
			sesion = ventaRepository.getSesion(1);
		} else if (venta.getSalaId() == 1 && venta.getSesionId() == 2) {
			sesion = ventaRepository.getSesion(2);
		} else if (venta.getSalaId() == 1 && venta.getSesionId() == 3) {
			sesion = ventaRepository.getSesion(3);
			
		} else if (venta.getSalaId() == 2 && venta.getSesionId() == 1) {
			sesion = ventaRepository.getSesion(4);
		} else if (venta.getSalaId() == 2 && venta.getSesionId() == 2) {
			sesion = ventaRepository.getSesion(5);
		} else if (venta.getSalaId() == 2 && venta.getSesionId() == 3) {
			sesion = ventaRepository.getSesion(6);
			
		} else if (venta.getSalaId() == 3 && venta.getSesionId() == 1) {
			sesion = ventaRepository.getSesion(7);
		} else if (venta.getSalaId() == 3 && venta.getSesionId() == 2) {
			sesion = ventaRepository.getSesion(8);
		} else if (venta.getSalaId() == 3 && venta.getSesionId() == 3) {
			sesion = ventaRepository.getSesion(9);
		}
		
		
		int entradasDisponibles =ventaRepository.getEntradasDisponibles(sesion);
		
		if(entradasDisponibles<venta.getNumeroEntradas()) {
			throw new VentaException(
					String.format("El numero de entradas disponibles %d no es suficiente",entradasDisponibles),
					venta);
		}
		
		//Calculamos el precio
		venta.setPrecio(venta.getNumeroEntradas() * PRECIO_POR_ENTRADA);
		
		int entradasRestantes=this.ventaRepository.actualizaEntradasDisponibles(sesion, venta.getNumeroEntradas());
		sesion.setButacas(entradasRestantes);
		
		ventaRepository.updateSesion(sesion);
		
		return this.ventaRepository.create(venta);
	}

	public Venta read(long id) {
		
		return ventaRepository.read(id);
	}
	
	public List<Venta> read() {
		return ventaRepository.read();
	}


	public void update(Venta venta) {
		ventaRepository.update(venta);
	}

	public void delete(long ventaId) {
		ventaRepository.delete(ventaId);
	}
}
