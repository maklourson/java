package fr.eni.clinique.common.util;

/**
 * Object Utilities.
 * 
 * @author externe
 *
 */
public class ObjectUtil {

    private ObjectUtil() {
        
    }
    
    /**
     * Check If Not blank.
     * 
     * @param element
     */
    public static void checkNotBlank(String element) {
        if(element == null || element.length() == 0) {
            throw new IllegalArgumentException(String.format("%s must not be blank !", element));
        }
    }
    
    /**
     * Check If Not null.
     * 
     * @param element
     */
    public static void checkNotNull(Object element) {
        if(element == null) {
            throw new IllegalArgumentException(String.format("Are you kidding me ? NULL is not Permitted in this method"));
        }
    }
}
