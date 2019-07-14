package com.demo.app.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.demo.app.response.model.ItemResponse;
import com.demo.app.util.ItemConstants;

@ControllerAdvice
@RestController
public class ItemResponseExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ItemResponse itemResponse = new ItemResponse("Validation Failed : " + ex.getBindingResult().toString(),
				ItemConstants.STATUS_FAILURE);
		return ResponseEntity.ok(itemResponse);
	}

	@ExceptionHandler(ItemNotFoundException.class)
	public final ResponseEntity<ItemResponse> handleItemNotFoundException(ItemNotFoundException ex,
			WebRequest request) {
		ItemResponse itemResponse = new ItemResponse(ex.getMessage(), ItemConstants.STATUS_FAILURE);
		return new ResponseEntity<>(itemResponse, HttpStatus.NOT_FOUND);
	}
}