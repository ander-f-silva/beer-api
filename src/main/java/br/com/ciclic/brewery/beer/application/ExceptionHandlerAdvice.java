package br.com.ciclic.brewery.beer.application;

import br.com.ciclic.brewery.beer.domain.exception.EntityExistsException;
import br.com.ciclic.brewery.beer.domain.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@ControllerAdvice
@RequestMapping(produces = "application/vnd.error+json")
public class ExceptionHandlerAdvice {

	private ResponseEntity<Map> error(Exception exception, HttpStatus httpStatus) {
		String message = Optional.of(exception.getMessage()).orElse(exception.getClass().getSimpleName());

		Map<String, String> error = new HashMap<>();
		error.put("message", message);

		return new ResponseEntity<>(error, httpStatus);
	}

	@ExceptionHandler(EntityExistsException.class)
	public ResponseEntity<Map> entityExists(EntityExistsException exception) {
		return error(exception, HttpStatus.UNPROCESSABLE_ENTITY);
	}

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<Map> notFound(NotFoundException exception) {
		return error(exception, HttpStatus.NOT_FOUND);
	}

}
