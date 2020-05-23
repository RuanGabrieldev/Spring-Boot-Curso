package br.com.ruan.cursomc.resources.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.ruan.cursomc.services.exceptions.ObjetoNaoEncontradoException;
import br.com.ruan.cursomc.services.exceptions.ViolacaoIntegridadeDadosException;

@ControllerAdvice
public class ResourcesExceptionHandler {
	
	@ExceptionHandler(ObjetoNaoEncontradoException.class)
	public ResponseEntity<StandardError> objetoNaoEncontrado(ObjetoNaoEncontradoException e, HttpServletRequest request){
		
		StandardError err = new StandardError(e.getMessage(), HttpStatus.NOT_FOUND.value(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(err);
	}
	
	@ExceptionHandler(ViolacaoIntegridadeDadosException.class)
	public ResponseEntity<StandardError> dataIntegrity(ViolacaoIntegridadeDadosException e, HttpServletRequest request){
		
		StandardError err = new StandardError(e.getMessage(), HttpStatus.BAD_REQUEST.value(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus. BAD_REQUEST.value()).body(err);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> validation(MethodArgumentNotValidException e, HttpServletRequest request){
		
		ValidationError err = new ValidationError("Erro de validação", HttpStatus.BAD_REQUEST.value(), System.currentTimeMillis());
		for(FieldError x : e.getBindingResult().getFieldErrors() ) {
			err.addError(x.getField(), x.getDefaultMessage());
			
		}
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(err);			
	} 
	

}
