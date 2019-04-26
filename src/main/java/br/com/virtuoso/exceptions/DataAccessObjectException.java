package br.com.virtuoso.exceptions;

public class DataAccessObjectException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DataAccessObjectException(String message) {
		super(message);
	}

	public DataAccessObjectException(String message, Exception e) {
		super(message, e);
	}
}