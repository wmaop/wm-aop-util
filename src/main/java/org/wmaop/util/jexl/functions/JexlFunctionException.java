package org.wmaop.util.jexl.functions;

@SuppressWarnings("serial")
public class JexlFunctionException extends RuntimeException {

	public JexlFunctionException() {
		super();
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
