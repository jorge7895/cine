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

import es.cic.grupo09.grupo09ejerc009.exception.SalaException;
import es.cic.grupo09.grupo09ejerc009.exception.ProyeccionException;
import es.cic.grupo09.grupo09ejerc009.exception.VentaException;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = { DataIntegrityViolationException.class })
	protected ResponseEntity<Object> handleConflict2(RuntimeException ex, WebRequest request) {
		String bodyOfResponse = ex.getMessage();
		return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST,
				request);
	}

	@ExceptionHandler(value = { NoSuchElementException.class })
	protected ResponseEntity<Object> handleConflict3(RuntimeException ex, WebRequest request) {
		String bodyOfResponse = ex.getMessage();
		return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST,
				request);
	}
	
	@ExceptionHandler(value = { VentaException.class })
	protected ResponseEntity<Object> handleConflict4(RuntimeException ex, WebRequest request) {
		String bodyOfResponse = ex.getMessage();
		return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST,
				request);
	}
	
	@ExceptionHandler(value = { SalaException.class })
	protected ResponseEntity<Object> handleConflict5(RuntimeException ex, WebRequest request) {
		String bodyOfResponse = ex.getMessage();
		return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST,
				request);
	}
	
	@ExceptionHandler(value = { ProyeccionException.class })
	protected ResponseEntity<Object> handleConflict6(RuntimeException ex, WebRequest request) {
		String bodyOfResponse = ex.getMessage();
		return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST,
				request);
	}
}