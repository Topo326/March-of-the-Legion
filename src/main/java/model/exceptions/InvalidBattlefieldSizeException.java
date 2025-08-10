package model.exceptions;


 //Excepción lanzada cuando el tamaño del campo de batalla es inválido

public class InvalidBattlefieldSizeException extends Exception {
    

    //Constructor con mensaje de error
    
    public InvalidBattlefieldSizeException(String message) {
        super(message);
    }
    
     //Constructor con mensaje y causa
     
    public InvalidBattlefieldSizeException(String message, Throwable cause) {
        super(message, cause);
    }
} 