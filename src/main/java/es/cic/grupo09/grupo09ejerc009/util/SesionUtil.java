package es.cic.grupo09.grupo09ejerc009.util;

import org.springframework.stereotype.Component;

import es.cic.grupo09.grupo09ejerc009.model.Venta;

@Component
public class SesionUtil {
	public static final int NUMERO_DE_SALAS = 3;

	public boolean isSesionValida(Venta venta) {
		return venta.getSesionId() < 0 || venta.getSesionId() >= NUMERO_DE_SALAS;
	}
}
