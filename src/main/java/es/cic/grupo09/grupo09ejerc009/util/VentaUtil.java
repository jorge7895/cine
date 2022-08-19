package es.cic.grupo09.grupo09ejerc009.util;

import java.time.LocalDate;
import java.util.List;

import es.cic.grupo09.grupo09ejerc009.exception.VentaException;
import es.cic.grupo09.grupo09ejerc009.model.Entrada;

public class VentaUtil {
	
	public void validarDescuentos(List<Entrada> listaEntradas) {
		
		long normales = listaEntradas.stream()
		.filter(n -> (n.getTipoEntrada() == TipoEntrada.NORMAL))
		.count();
		
		if(normales >= 5) {
			listaEntradas.stream()
			.filter(n -> (n.getTipoEntrada() == TipoEntrada.NORMAL))
			.forEach(n -> n.setTipoEntrada(TipoEntrada.GRUPO));
		}
	}
	
	public void actualizarImporteVenta(Entrada entrada) {
		
		float importeTotalActual = entrada.getPrecioEntrada();
		
		switch (entrada.getTipoEntrada()) {
		case SENIOR:
				importeTotalActual -= (entrada.getPrecioEntrada() * 0.25f);
				entrada.getVenta().setImporteTotal(importeTotalActual);
			break;
		case JOVEN:
			importeTotalActual -= (entrada.getPrecioEntrada() * 0.15f);
			entrada.getVenta().setImporteTotal(importeTotalActual);
		break;
		case GRUPO:
			importeTotalActual -= (entrada.getPrecioEntrada() * 0.1f);
			entrada.getVenta().setImporteTotal(importeTotalActual);
		break;
		default:
			entrada.getVenta().setImporteTotal(importeTotalActual);
			break;
		}
	}
	
	public void validarProyeccion(Entrada entrada) {
		
		LocalDate fechaApertura = entrada.getProyeccion().getFechaApertura();
		LocalDate fechaCierre = entrada.getProyeccion().getFechaCierre();
		LocalDate fechaVenta = entrada.getVenta().getDiaDeVenta().toLocalDate();
		
		if (fechaVenta.isBefore(fechaApertura) | fechaVenta.isAfter(fechaCierre)) {
			throw new VentaException("La proyección todavía no esta abierta a para venta");
		}
	}
	
	public void validarButacaFila(Entrada entrada) {
		
		int butaca = entrada.getButaca();
		int fila = entrada.getFila();
		int filasDisponibles = entrada.getProyeccion().getSala().getFilas();
		int butacasFilaDisponibles = entrada.getProyeccion().getSala().getButacasFila();
		
		if (butaca > butacasFilaDisponibles | fila > filasDisponibles) {
			throw new VentaException("La selección de asiento no es correcta para la sala");
		}
		
	}
	
	public void actualizarDisponibles(Entrada entrada) {
		int entradasVendidas = entrada.getProyeccion().getEntradasVendidas();
		int aforoSala = entrada.getProyeccion().getSala().getAforo();
		
		if (entradasVendidas >= aforoSala) {
			throw new VentaException("Aforo Completo");
		}
		entrada.getProyeccion().setEntradasVendidas(entradasVendidas++);
	}
}