package es.cic.grupo09.grupo09ejerc009.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class SesionException extends RuntimeException {

	private final int sesionId;
	
	public SesionException(String message, Throwable cause, int sesionId) {
		super(message, cause);
		this.sesionId = sesionId;
	}

	public SesionException(String message, int sesionId) {
		super(message);
		this.sesionId = sesionId;
	}

	public int getSesionId() {
		return sesionId;
	}

	@Override
	public String getMessage() {
		return String.format("%s en la sesion %d", super.getMessage(), sesionId);
	}
	
	
}
