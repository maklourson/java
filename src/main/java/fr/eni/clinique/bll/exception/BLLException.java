package fr.eni.clinique.bll.exception;

public class BLLException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5377490511744988652L;
	
	public BLLException(String message, Throwable cause) {
        super(message, cause);
    }
	
	 public BLLException(String message) {
	        super(message);
	    }
	
}
