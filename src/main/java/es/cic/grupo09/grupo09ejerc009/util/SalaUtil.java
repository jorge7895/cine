package es.cic.grupo09.grupo09ejerc009.util;

import org.springframework.stereotype.Component;

import es.cic.grupo09.grupo09ejerc009.model.Venta;

@Component
public class SalaUtil {
	public static final int NUMERO_DE_SALAS = 3;

	public boolean isSalaValida(Venta venta) {
		return venta.getSalaId() < 0 || venta.getSalaId() > NUMERO_DE_SALAS;
	}
}
