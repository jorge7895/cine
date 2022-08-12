package es.cic.grupo09.grupo09ejerc009.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import es.cic.grupo09.grupo09ejerc009.model.Sesion;

@SuppressWarnings("serial")
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class SesionException extends RuntimeException {

	private final long sesionId;
	private Sesion sesion;
	
	public SesionException(String message, Throwable cause, long sesionId) {
		super(message, cause);
		this.sesionId = sesionId;
	}

	public SesionException( long sesionId, String message) {
		super(message);
		this.sesionId = sesionId;
	}
	
	public SesionException(String mensaje, Sesion sesion) {
		super(mensaje);
		this.sesionId = sesion.getId();
		this.sesion = sesion;
	}

	public long getSesionId() {
		return sesionId;
	}

	@Override
	public String getMessage() {
		return String.format("%s en la sesion %d", super.getMessage(), sesionId);
	}
	
	public Sesion getSesion() {
		return this.sesion;
	}
	
}
