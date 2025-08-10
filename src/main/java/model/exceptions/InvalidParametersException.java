package model.exceptions;


 //Excepción lanzada cuando los parámetros de entrada son inválidos o están mal formados

public class InvalidParametersException extends Exception {
   
     //Constructor con mensaje de error
   
    public InvalidParametersException(String message) {
        super(message);
    }
    
  
    //Constructor con mensaje y causa
   
    public InvalidParametersException(String message, Throwable cause) {
        super(message, cause);
    }
} 