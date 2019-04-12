package fr.eni.clinique.common.exception;

/**
 * Exception For technical problems, don't catch this !
 * 
 * @author externe
 *
 */
public class TechnicalException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 8960908000805917287L;

    public TechnicalException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
