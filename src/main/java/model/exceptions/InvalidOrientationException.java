package model.exceptions;


//Excepción lanzada cuando la orientación del ordenamiento es inválida

public class InvalidOrientationException extends Exception {
    
  
    //Constructor con mensaje de error

    public InvalidOrientationException(String message) {
        super(message);
    }
    
    
    //Constructor con mensaje y causa
    
    public InvalidOrientationException(String message, Throwable cause) {
        super(message, cause);
    }
} 