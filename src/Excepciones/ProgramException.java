/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Excepciones;

/**
 *
 * @author Juanes
 */
public class ProgramException extends Exception{
    private String message;

    public ProgramException(String message) {
        super(message);
    }
    
}
