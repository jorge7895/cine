package es.cic.grupo09.grupo09ejerc009.controller;

import java.util.NoSuchElementException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = { UnsupportedOperationException.class })
	protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
		String bodyOfResponse = "Restricción de base de datos incorrecta.";
		return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR,
				request);
	}

	@ExceptionHandler(value = { DataIntegrityViolationException.class })
	protected ResponseEntity<Object> handleConflict2(RuntimeException ex, WebRequest request) {
		String bodyOfResponse = "Violacion de base de datos al insertar campo.";
		return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR,
				request);
	}

	@ExceptionHandler(value = { NoSuchElementException.class })
	protected ResponseEntity<Object> handleConflict3(RuntimeException ex, WebRequest request) {
		String bodyOfResponse = "Valor no encontrado.";
		return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR,
				request);
	}
}