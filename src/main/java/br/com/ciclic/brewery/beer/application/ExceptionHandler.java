package br.com.ciclic.brewery.beer.application;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;

@ControllerAdvice
@RequestMapping(produces = "application/vnd.error+json")
public class ExceptionHandler {

//	private ResponseEntity<VndErrors> error(Exception exception, HttpStatus httpStatus, String logRef) {
//		String message = Optional.of(exception.getMessage()).orElse(exception.getClass().getSimpleName());
//		return new ResponseEntity<>(new VndErrors(logRef, message), httpStatus);
//	}
//
//	@ExceptionHandler(OperationNotSupportedException.class)
//	public ResponseEntity<VndErrors> operationNotSupported(OperationNotSupportedException exception) {
//		return error(exception, HttpStatus.BAD_REQUEST, "log");
//	}
//
//	@ExceptionHandler(RequiredFieldMissingException.class)
//	public ResponseEntity<VndErrors> requiredFieldMissing(RequiredFieldMissingException exception) {
//		return error(exception, HttpStatus.PRECONDITION_FAILED, "log");
//	}
}
