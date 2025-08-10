package model.exceptions;


 //Excepción lanzada cuando el número de tropas excede la capacidad del campo de batalla

public class TooManyTroopsException extends Exception {
    
    //Constructor con mensaje de error
     
    public TooManyTroopsException(String message) {
        super(message);
    }
    
    
    //Constructor con mensaje y causa

    public TooManyTroopsException(String message, Throwable cause) {
        super(message, cause);
    }
} 