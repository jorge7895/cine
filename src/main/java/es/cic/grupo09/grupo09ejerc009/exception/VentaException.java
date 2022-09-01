package es.cic.grupo09.grupo09ejerc009.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import es.cic.grupo09.grupo09ejerc009.model.Venta;

@SuppressWarnings("serial")
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class VentaException extends RuntimeException {

	private final Venta venta;

	public VentaException(String message, Venta venta) {
		super(message);
		this.venta = venta;
	}
	
	@Override
	public String getMessage() {
		return String.format("%s en la venta %s", super.getMessage(), venta.toString());
	}
}
