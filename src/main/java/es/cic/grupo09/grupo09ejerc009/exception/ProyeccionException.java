package es.cic.grupo09.grupo09ejerc009.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import es.cic.grupo09.grupo09ejerc009.model.Proyeccion;

@SuppressWarnings("serial")
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ProyeccionException extends RuntimeException {

	private final Proyeccion proyeccion;

	public ProyeccionException(String mensaje, Proyeccion proyeccion) {
		super(mensaje);
		this.proyeccion = proyeccion;
	}

	@Override
	public String getMessage() {
		return String.format("%s en la proyeccion %s", super.getMessage(), proyeccion.toString());
	}

}
