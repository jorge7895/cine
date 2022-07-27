package es.cic.grupo09.grupo09ejerc009.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.cic.grupo09.grupo09ejerc009.model.Venta;
import es.cic.grupo09.grupo09ejerc009.repository.VentaRepository;
import es.cic.grupo09.grupo09ejerc009.util.SalaUtil;
import es.cic.grupo09.grupo09ejerc009.util.SesionUtil;

@Service
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
		
		int entradasDisponibles =ventaRepository.getEntradasDisponibles(venta.getSesionId(), venta.getSalaId());
		
		if(entradasDisponibles<venta.getNumeroEntradas()) {
			throw new VentaException(
					String.format("El numero de entradas disponibles %d no es suficiente",entradasDisponibles),
					venta);
		}
		
		//Calculamos el precio
		venta.setPrecio(venta.getNumeroEntradas() * PRECIO_POR_ENTRADA);
		
		this.ventaRepository.actualizaEntradasDisponibles(venta.getSalaId(),venta.getSesionId(), entradasDisponibles-venta.getNumeroEntradas());
		return this.ventaRepository.create(venta);
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
}
