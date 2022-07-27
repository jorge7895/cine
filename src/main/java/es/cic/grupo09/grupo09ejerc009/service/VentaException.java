package es.cic.grupo09.grupo09ejerc009.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import es.cic.grupo09.grupo09ejerc009.model.Venta;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class VentaException extends RuntimeException {

	private Venta venta;

	public VentaException(String message, Throwable cause, Venta venta) {
		super(message, cause);
		this.venta = venta;
	}

	public VentaException(String message, Venta venta) {
		super(message);
		this.venta = venta;
	}

	public Venta getVenta() {
		return this.venta;
	}
}
