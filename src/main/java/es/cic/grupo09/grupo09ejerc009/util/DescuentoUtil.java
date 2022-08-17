package es.cic.grupo09.grupo09ejerc009.util;

import java.util.List;

import es.cic.grupo09.grupo09ejerc009.model.Entrada;

public class DescuentoUtil {
	
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
}