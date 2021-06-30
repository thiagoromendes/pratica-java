package com.evoluum.java.evoluumjava.excecao;

public class RegraDeNegocioExcepetion extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public RegraDeNegocioExcepetion(String message) {
		super(message);
	}
}
