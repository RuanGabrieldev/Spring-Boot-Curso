package br.com.ruan.cursomc.services.exceptions;

public class ViolacaoIntegridadeDadosException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	
	
	public ViolacaoIntegridadeDadosException(String msg) {
		super(msg);
	}
	
	public ViolacaoIntegridadeDadosException(String msg, Throwable cause) {
		super(msg, cause);
	}
	
}
