package es.cic.grupo09.grupo09ejerc009.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import es.cic.grupo09.grupo09ejerc009.model.Proyeccion;

@SuppressWarnings("serial")
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class ProyeccionException extends RuntimeException {

	private final long proyeccionId;
	private final Proyeccion proyeccion;

	public ProyeccionException(String message, Throwable cause, long proyeccionId) {
		super(message, cause);
		this.proyeccionId = proyeccionId;
		this.proyeccion = new Proyeccion();
	}

	public ProyeccionException(long proyeccionId, String message) {
		super(message);
		this.proyeccionId = proyeccionId;
		this.proyeccion = new Proyeccion();
	}

	public ProyeccionException(String mensaje, Proyeccion proyeccion) {
		super(mensaje);
		this.proyeccionId = proyeccion.getId();
		this.proyeccion = proyeccion;
	}

	public long getSesionId() {
		return proyeccionId;
	}

	@Override
	public String getMessage() {
		return String.format("%s en la sesion %d", super.getMessage(), proyeccionId);
	}

	public Proyeccion getSesion() {
		return this.proyeccion;
	}

}
