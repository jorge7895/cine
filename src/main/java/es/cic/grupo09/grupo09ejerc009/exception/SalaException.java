package es.cic.grupo09.grupo09ejerc009.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import es.cic.grupo09.grupo09ejerc009.model.Sala;

@SuppressWarnings("serial")
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class SalaException extends RuntimeException {

	private final Sala sala;

	public SalaException(String message, Sala sala) {
		super(message);
		this.sala = sala;
	}

	@Override
	public String getMessage() {
		return String.format("%s en la sala %s", super.getMessage(), sala.toString());
	}
	
	
}
