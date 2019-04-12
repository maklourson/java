package fr.eni.clinique.dal.exception;

public class DaoException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1077630058513544000L;
	public DaoException(String message) {
        super(message);
    }

    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
