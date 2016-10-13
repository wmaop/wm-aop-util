package org.wmaop.util.jexl.functions;

public class JexlFunctionException extends RuntimeException {

	public JexlFunctionException() {
	}

	public JexlFunctionException(String message) {
		super(message);
	}

	public JexlFunctionException(Throwable cause) {
		super(cause);
	}

	public JexlFunctionException(String message, Throwable cause) {
		super(message, cause);
	}
}
