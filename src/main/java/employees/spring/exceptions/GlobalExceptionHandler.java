package employees.spring.exceptions;

import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	static Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(NotFoundException.class)
	ResponseEntity<String> notFoundHandler(NotFoundException e) {
		String errorMessage = e.getMessage();
		log.error(errorMessage);
		return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler({ IllegalArgumentException.class, HttpMessageNotReadableException.class,
			IllegalStateException.class })
	ResponseEntity<String> illegalArgumentHandler(RuntimeException e) {
		String errorMessage = e.getMessage();
		log.error(errorMessage);
		return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	ResponseEntity<String> methodArgumentHandler(MethodArgumentNotValidException e) {
		String errorMessage = e.getAllErrors().stream().map(er -> er.getCodes()[0] + ": " + er.getDefaultMessage())
				.collect(Collectors.joining(";"));
		log.error(errorMessage);
		return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(RuntimeException.class)
	ResponseEntity<String> exceptionsHandler(RuntimeException e) {
		String errorMessage = e.getMessage();
		log.error(errorMessage);
		return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
