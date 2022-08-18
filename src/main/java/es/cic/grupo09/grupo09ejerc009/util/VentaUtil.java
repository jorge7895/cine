package es.cic.grupo09.grupo09ejerc009.util;

import java.util.List;

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
}