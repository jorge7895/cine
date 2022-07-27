package es.cic.grupo09.grupo09ejerc009.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class SalaException extends RuntimeException {

	private final int salaId;
	
	public SalaException(String message, Throwable cause, int salaId) {
		super(message, cause);
		this.salaId = salaId;
	}

	public SalaException(String message, int salaId) {
		super(message);
		this.salaId = salaId;
	}

	public int getSalaId() {
		return salaId;
	}

	@Override
	public String getMessage() {
		return String.format("%s en la sala %d", super.getMessage(), salaId);
	}
	
	
}
