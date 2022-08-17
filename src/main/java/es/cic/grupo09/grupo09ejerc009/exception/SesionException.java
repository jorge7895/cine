package es.cic.grupo09.grupo09ejerc009.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import es.cic.grupo09.grupo09ejerc009.model.Proyeccion;

@SuppressWarnings("serial")
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class SesionException extends RuntimeException {

	private final long sesionId;
	private Proyeccion sesion;

	public SesionException(String message, Throwable cause, long sesionId) {
		super(message, cause);
		this.sesionId = sesionId;
	}

	public SesionException(long sesionId, String message) {
		super(message);
		this.sesionId = sesionId;
	}

	public SesionException(String mensaje, Proyeccion sesion) {
		super(mensaje);
		this.sesionId = sesion.getId();
		this.sesion = sesion;
	}

	public SesionException(String mensaje, long sesion) {
		super(mensaje);
		this.sesionId = sesion;
	}

	public long getSesionId() {
		return sesionId;
	}

	@Override
	public String getMessage() {
		return String.format("%s en la sesion %d", super.getMessage(), sesionId);
	}

	public Proyeccion getSesion() {
		return this.sesion;
	}

}
