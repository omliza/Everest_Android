package com.sxm.framework.exception;

public class AndriodException extends RuntimeException {

	/**
	 * Serializing the class
	 */
	private static final long serialVersionUID = 1L;
	private String errorCode;
	private String error;
	private transient Object[] params;
	
	private String message = null;

	/**
	 * <p>
	 * Get errorCode.
	 * <p>
	 */
	public String getErrorCode() {
		return errorCode;
	}

	/**
	 * <p>
	 * Get error.
	 * <p>
	 */
	public String getError() {
		return error;
	}


	/**
	 * @return
	 */
	public Object[] getParams(){
		return params;
	}

	
	public AndriodException(String errorCode, String error) {
		this.errorCode = errorCode;
		this.error = error;
	}

	public AndriodException(String errorCode, String error, Object... params){
		this.errorCode = errorCode;
		this.error = error;
		this.params = params;
	}


	public AndriodException(Throwable cause) {
		super(cause);
	}

	
	public AndriodException(String message,String errorCode, Throwable cause) {
		super(message, cause);
		this.errorCode=errorCode;
		this.error=message;
	}
	
	public AndriodException(String message) {
        super(message);
        this.message = message;
    }

	@Override
    public String toString() {
        return message;
    }
 
    @Override
    public String getMessage() {
        return message;
    }
    
    public AndriodException(String className, String methodName, String message) {
    	super(className+" : "+methodName+ " : "  +message);
    	//String errorMessage = className+" : "+methodName+ " : "  +message;
        this.message = className+" : "+methodName+ " : "  +message;
    }


}
 