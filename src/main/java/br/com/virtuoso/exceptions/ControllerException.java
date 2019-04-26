package br.com.virtuoso.exceptions;

public class ControllerException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ControllerException(String message) {
		super(message);
	}

	public ControllerException(String message, Exception e) {
		super(message, e);
	}
}