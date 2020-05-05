package br.com.ruan.cursomc.resources.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.ruan.cursomc.services.exceptions.ObjetoNaoEncontradoException;

@ControllerAdvice
public class ResourcesExceptionHandler {
	
	@ExceptionHandler(ObjetoNaoEncontradoException.class)
	public ResponseEntity<StandardError> objetoNaoEncontrado(ObjetoNaoEncontradoException e, HttpServletRequest request){
		
		StandardError err = new StandardError(e.getMessage(), HttpStatus.NOT_FOUND.value(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(err);
	}
	
	

}
