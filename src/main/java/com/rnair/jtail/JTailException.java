package com.rnair.jtail;

public class JTailException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private JTailErrorCode errorCode;

	public JTailException(String message) {
		super(message);
	}

	public JTailException(String message, Throwable e) {
		this(JTailErrorCode.DEFAULT_ERROR,message,e);
	}

	public JTailException(JTailErrorCode errorCode, String message,
			Throwable e) {
		super(message, e);
	}

	public JTailErrorCode getErrorCode() {
		return errorCode;
	}

	@Override
	public String toString() {
		return "JTailException [errorCode=" + errorCode + "]"+this.getLocalizedMessage();
	}
	
	
	
}
