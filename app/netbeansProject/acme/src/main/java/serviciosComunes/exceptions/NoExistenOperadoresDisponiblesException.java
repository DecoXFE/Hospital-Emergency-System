/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package serviciosComunes.exceptions;


/**
 *
 * @author diego
 *
 * */
public class NoExistenOperadoresDisponiblesException extends Exception {
    public NoExistenOperadoresDisponiblesException(String message) {
        super(message);
    }
    
    public NoExistenOperadoresDisponiblesException(String message, Throwable cause) {
        super(message, cause);
    }
}
